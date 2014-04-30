package com.example.itlog.services;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.requestobjects.AddProjectRequest;
import com.example.itlog.responseobjects.AddProjectResponse;

import android.os.AsyncTask;

public class AddProjectService extends AsyncTask<String, String, AddProjectResponse>{
	
	private CallbackInterface<String> callback;
	
	String nomeServico;
	AddProjectRequest apr;

	public AddProjectService(CallbackInterface<String> callback,
			String nomeServico, AddProjectRequest apr) {
		super();
		this.callback = callback;
		this.nomeServico = nomeServico;
		this.apr = apr;
	}

	@Override
	protected AddProjectResponse doInBackground(String... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onPostExecute(AddProjectResponse result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

}
