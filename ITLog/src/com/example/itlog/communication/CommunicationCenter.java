package com.example.itlog.communication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;

import android.util.Log;

import com.example.itlog.requestobjects.Funcionario;
import com.google.gson.Gson;

public class CommunicationCenter {

	private static final String TAG = "CommunicationCenter";
	private static String BaseUrl = "";

	// String de GETs
	public static final String GetSessionInformationService = "getsession.php?";
	public static final String ListProjectsOfTheUser = "listprojectsuser.php?";
	public static final String ListAllProjects = "listallprojects.php?";
	public static final String GetCalendar = "getcalendar.php?";
	public static final String ListTotalHoursPerCompany = "listtotalhourscompany.php?";
	public static final String ListTotalHoursPerProject = "listtotalhoursproject.php?";
	

	// String de POSTs
	public static final String LoginService = "login.php?";
	public static final String AddProject = "addproject.php?";
	public static final String AllocateHours = "allocatehours.php?";

	private static int timeoutConnection = 10000;

	public static <T> T callGetService(String nomeServico, String[] info,
			Class<T> resposta) {

		// GET
		boolean isGetSession = nomeServico.equals(GetSessionInformationService);
		boolean isListProjectUser = nomeServico.equals(ListProjectsOfTheUser);
		boolean isListAllProjects = nomeServico.equals(ListAllProjects);
		boolean isGetCalendar = nomeServico.equals(GetCalendar);
		boolean isListTotalHoursCompany = nomeServico
				.equals(ListTotalHoursPerCompany);
		boolean isListTotalHoursProject = nomeServico
				.equals(ListTotalHoursPerProject);

		BufferedReader readerBuffer = null;
		HttpURLConnection connection = null;

		Gson gson = new Gson();

		int infoSize = 0;
		if (info != null) {
			infoSize = info.length;
		}

		// falta codigo aqui? ver CommunicationCenter ajuda

		StringBuilder builder = new StringBuilder(BaseUrl).append("/").append(
				nomeServico);

		if (info != null && info.length > 0) {
			if (isGetSession) {
				builder.append("username=" + info[0]).append('&')
						.append("projects=" + info[1]).append('&')
						.append("userid=" + info[2]).append('&')
						.append("email=" + info[3]);
			} else if (isListProjectUser) {
				builder.append("username=" + info[0]).append('&')
						.append("projects=" + info[1]).append('&')
						.append("fullname=" + info[2]).append('&')
						.append("projectid=" + info[3]).append('&')
						.append("company=" + info[4])
						.append("manager=" + info[5])
						.append("descritption=" + info[6]);
			} else if (isListAllProjects) {
				builder.append("projects=" + info[0]).append('&')
						.append("fullname=" + info[1]).append('&')
						.append("projectid=" + info[2]).append('&')
						.append("company=" + info[3])
						.append("manager=" + info[4])
						.append("descritption=" + info[5]);
			} else if (isGetCalendar) {
				builder.append("month=" + info[0]).append('&')
						.append("business days=" + info[1]).append('&')
						.append("holidays=" + info[2]).append('&');
			} else if (isListTotalHoursCompany) {
				builder.append("username=" + info[0]).append('&')
						.append("companies=" + info[1]).append('&')
						.append("company=" + info[2]).append('&')
						.append("hours=" + info[2]).append('&');
			} else if (isListTotalHoursProject) {
				builder.append("username=" + info[0]).append('&')
						.append("projects=" + info[1]).append('&')
						.append("project=" + info[2]).append('&')
						.append("hours=" + info[2]).append('&');
			}
		}

		if (isGetSession || isListProjectUser || isListAllProjects || isGetCalendar || isListTotalHoursCompany || isListTotalHoursProject) {
			try {

				URL url = new URL(builder.toString());
				connection = (HttpURLConnection) url.openConnection();
				//connection = setConnectTimeout (timeoutConnection);
				connection.setRequestMethod("GET");
				connection.setDoInput(true);

				InputStream inputStream = null;

				if (connection instanceof HttpURLConnection) {
					HttpURLConnection httpConnection = (HttpURLConnection) connection;
					int statusCode = httpConnection.getResponseCode();
					if (statusCode != 200)
						return null;
					else
						inputStream = httpConnection.getInputStream();
				}

				readerBuffer = new BufferedReader(new InputStreamReader(
						inputStream));

				StringWriter stringWriter = new StringWriter();
				char[] buffer = new char[1024 * 4];
				int n = 0;
				while (-1 != (n = readerBuffer.read(buffer))) {
					stringWriter.write(buffer, 0, n);
				}

				String resultString = stringWriter.toString();

				return (T) gson.fromJson(resultString, resposta);

			} catch (ClientProtocolException e) {
				Log.e(TAG, "Got an ClientProtocolException: " + e.getMessage());
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				Log.e(TAG, "Got an IOException: " + e.getMessage());
				e.printStackTrace();
				return null;
			}
		} else
			return null;
	}

	// url do servi�o, objecto, class resposta
	public static <T> T callPostService(String nomeServico, Object object,
			Class<T> resposta) {
		
				// POST
			boolean isLogin = nomeServico.equals(LoginService);
			boolean isAddProject = nomeServico.equals(AddProject);
			boolean isAllocateHours = nomeServico.equals(AllocateHours);
			
			URL url;
			HttpURLConnection connection2 = null;
			Gson gson2 = new Gson();
			if(isLogin || isAddProject || isAllocateHours){
				try {
						// Create connection
					url = new URL(nomeServico);
					connection2 = (HttpURLConnection) url.openConnection();
					connection2.setRequestMethod("POST");
					// add the content type of the request, most post data is of this type
					connection2.addRequestProperty("Content-Type",
							"application/x-www-form-urlencoded"); 
					connection2.setUseCaches(false);
					connection2.setDoInput(true);
					connection2.setDoOutput(true);
		
					// Send request -> de JAVA para JSON
					DataOutputStream wr = new DataOutputStream(
							connection2.getOutputStream());
					wr.writeBytes(gson2.toJson(object));
					wr.flush();
					wr.close();
		
					// Get Response -> de JSON para JAVA
					InputStream is = connection2.getInputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(is));
					String line;
					StringBuffer response = new StringBuffer();
					while ((line = br.readLine()) != null) {
						response.append(line);
						response.append('\r');
					}
					br.close();
		
					// ???? ta certo ????
					return (T) gson2.fromJson(line, Object.class);
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					if (connection2 != null) {
						connection2.disconnect();
					}
				}
		}else return null;			
			}
}
