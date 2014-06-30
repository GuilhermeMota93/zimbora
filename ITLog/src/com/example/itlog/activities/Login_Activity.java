package com.example.itlog.activities;

import com.example.itlog.R;
import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.responseobjects.LoginResponse;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Login_Activity extends Activity implements
		CallbackInterface<LoginResponse> {

	Button login; // butao de log in
	EditText password, credencial;// texto de inser�ao de credencial/password
	ImageView imgV; // imagem no topo
	TextView bemvindo;
	Typeface font;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.login_layout);
		login = (Button) findViewById(R.id.loginBut);
		password = (EditText) findViewById(R.id.password);
		credencial = (EditText) findViewById(R.id.credencial);
		imgV = (ImageView) findViewById(R.id.imgV);
		bemvindo = (TextView) findViewById(R.id.textView1);
		
		//define a custom font
		font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");		
		login.setTypeface(font);
		password.setTypeface(font);
		credencial.setTypeface(font);
		bemvindo.setTypeface(font);
		
		
		login.setOnClickListener(new OnClickListener() {

			/*
			 * NOTA "COMO PASSAR OBJECTS DO GSON NO BUNDLE": 
			 * 
			 * Initial Activity
			 * Intent activity = new Intent(MyActivity.this,NextActivity.class);
			 * activity.putExtra("myObject", new Gson().toJson(myobject);
			 * startActivity(activity); 
			 * 
			 * Next Activity
			 * 
			 * Sting jsonMyObject; Bundle extras = getIntent().getExtras(); if
			 * (extras != null) { jsonMyObject = extras.getString("myObject"); }
			 * MyObject myObject = new Gson().fromJson(jsonMyObject,
			 * MyObject.class);
			 */

			// ao click no botao LOG IN, fazer o servico para ir buscar as info
			// depois passar Intents com "Extras" para a prox atividade
			// fazer o set.TexView() ???

			@Override
			public void onClick(View v) {// accao ao clicar
				// TODO Auto-generated method stub
				Intent intencao = new Intent(Login_Activity.this,
						Info_Activity.class);// ao carregar no botao LOGIN vai
												// para a actividade Info
				Login_Activity.this.startActivity(intencao);
			}
		});

	}

	@Override
	public void callbackCall(LoginResponse t) {
		// TODO Auto-generated method stub

	}

}
