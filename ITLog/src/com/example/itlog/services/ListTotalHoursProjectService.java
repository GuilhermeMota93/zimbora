package com.example.itlog.services;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.requestobjects.ListTotalHoursProjectRequest;
import com.example.itlog.responseobjects.ListTotalHoursProjectResponse;

import android.os.AsyncTask;

public class ListTotalHoursProjectService extends
		AsyncTask<String, String, ListTotalHoursProjectResponse> {

	private CallbackInterface<String> callback;

	String nomeServico;
	ListTotalHoursProjectRequest lthpr;
	
	
	public ListTotalHoursProjectService(CallbackInterface<String> callback,
			String nomeServico, ListTotalHoursProjectRequest lthpr) {
		super();
		this.callback = callback;
		this.nomeServico = nomeServico;
		this.lthpr = lthpr;
	}

	@Override
	protected ListTotalHoursProjectResponse doInBackground(String... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onPostExecute(ListTotalHoursProjectResponse result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

}
