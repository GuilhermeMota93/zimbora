package com.example.itlog.services;

import android.os.AsyncTask;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.requestobjects.ListTotalHoursCompanyRequest;
import com.example.itlog.responseobjects.ListTotalHoursCompanyResponse;

public class ListTotalHoursCompanyService extends
		AsyncTask<String, String, ListTotalHoursCompanyResponse> {

	private CallbackInterface<String> callback;

	String nomeServico;
	ListTotalHoursCompanyRequest lthcr;

	@Override
	protected void onPostExecute(ListTotalHoursCompanyResponse result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

	@Override
	protected ListTotalHoursCompanyResponse doInBackground(String... params) {
		// TODO Auto-generated method stub
		return null;
	}

}
