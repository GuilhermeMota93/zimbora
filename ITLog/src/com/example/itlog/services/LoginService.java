package com.example.itlog.services;

import android.os.AsyncTask;

import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.requestobjects.LoginRequest;
import com.example.itlog.responseobjects.LoginResponse;

public class LoginService extends AsyncTask<String, String, LoginResponse> {
	
	private CallbackInterface<String> callback;
	
	String nomeServico;
	LoginRequest lr;
	
	public LoginService(String nomeServico, LoginRequest lr){
		this.nomeServico = nomeServico;
		this.lr = lr;
	}
	
	@Override
	protected LoginResponse doInBackground(String... params) {
		// TODO Auto-generated method stub
		CommunicationCenter.callPostService(nomeServico, lr, LoginResponse.class);
		return null ;
	}
	
	@Override
	protected void onPostExecute(LoginResponse result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

	
}
