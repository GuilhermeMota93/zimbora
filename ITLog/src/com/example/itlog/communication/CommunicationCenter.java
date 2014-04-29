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

	// Login � POST
	public static final String LoginService = "login.php?";

	// GetSessionInformation � GET
	public static final String GetSessionInformationService = "getsession.php?";
	
	private static int timeoutConnection = 10000;

	
	public static <T> T callGetService(String nomeServico, String[] info,
			Class<T> resposta) {

		boolean isGetSession = nomeServico.equals(GetSessionInformationService);
		boolean isLogin = nomeServico.equals(LoginService);

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
						.append("fullname=" + info[1]).append('&')
						.append("userid=" + info[2]).append('&')
						.append("email=" + info[3]);
			}
		}

		if (isGetSession) {
			try {

				URL url = new URL(builder.toString());
				connection = (HttpURLConnection) url.openConnection();
				// connection = setConnectTimeout (timeoutConnection);
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

	//url do servi�o, objecto, class resposta
	public static <T> T callPostService(String targetURL, Object object,
			Class<T> resposta) {
		URL url;
		HttpURLConnection connection2 = null;
		Gson gson2 = new Gson();
		try {
			// Create connection
			url = new URL(targetURL);
			connection2 = (HttpURLConnection) url.openConnection();
			connection2.setRequestMethod("POST");
		    connection2.addRequestProperty("Content-Type", "application/x-www-form-urlencoded"); //add the content type of the request, most post data is of this type
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
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return (T) gson2.toJson(object);

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {

			if (connection2 != null) {
				connection2.disconnect();
			}
		}
	}
}
