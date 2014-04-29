package com.example.itlog.activities;

import com.example.itlog.R;
import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.responseobjects.LoginResponse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginActivity extends Activity implements CallbackInterface<LoginResponse> {

	Button login; // butao de log in
	EditText password, credencial;// texto de inser�ao de credencial/password
	ImageView imgV; // imagem no topo

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//	        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.login_layout);
		login = (Button) findViewById(R.id.loginBut);
		password = (EditText) findViewById(R.id.password);
		credencial = (EditText) findViewById(R.id.credencial);
		imgV = (ImageView) findViewById(R.id.imgV);
		
		
		
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {//accao ao clicar 
				// TODO Auto-generated method stub
				Intent intencao = new Intent(LoginActivity.this, InfoActivity.class);//ao carregar no botao LOGIN vai para a actividade Info
				LoginActivity.this.startActivity(intencao);
			}
		});

	}

	@Override
	public void callbackCall(LoginResponse t) {
		// TODO Auto-generated method stub
		
	}


}
