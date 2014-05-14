package com.example.itlog.services;

import android.os.AsyncTask;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.requestobjects.ListProjectUserRequest;
import com.example.itlog.responseobjects.ListProjectsUserResponse;

public class ListProjectsUserService extends
		AsyncTask<String, Void, ListProjectsUserResponse> {

	private CallbackInterface<String> callback;

	String nomeServico;
	ListProjectUserRequest lpur;

	public ListProjectsUserService(String nomeServico,
			ListProjectUserRequest lpur) {
		super();
		this.nomeServico = nomeServico;
		this.lpur = lpur;
	}

	@Override
	protected ListProjectsUserResponse doInBackground(String... params) {

		//que fazer aqui? GET receber um String [] info ?!
//		ListProjectsUserResponse listProjectUser = CommunicationCenter.callGetService(
//				nomeServico, CommunicationCenter.info, ListProjectsUserResponse.class);
//		
//		return listProjectUser;
		return null;
	}

	@Override
	protected void onPostExecute(ListProjectsUserResponse result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

}
