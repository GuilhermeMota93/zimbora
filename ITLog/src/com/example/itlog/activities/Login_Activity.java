package com.example.itlog.activities;

import java.util.ArrayList;

import com.example.itlog.R;
import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.objects.Users;
import com.example.itlog.responseobjects.LoginResponse;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Activity extends Activity implements
		CallbackInterface<LoginResponse> {

	private Users user;
	String username, pass;

	Button login; // butao de log in
	EditText password, credencial;// texto de inser�ao de credencial/password
	ImageView imgV; // imagem no topo
	TextView bemvindo;
	Typeface font;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		user = new Users();

		setContentView(R.layout.login_layout);
		login = (Button) findViewById(R.id.loginBut);
		password = (EditText) findViewById(R.id.password);
		credencial = (EditText) findViewById(R.id.credencial);
		imgV = (ImageView) findViewById(R.id.imgV);
		bemvindo = (TextView) findViewById(R.id.textView1);

		// define a custom font
		font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
		login.setTypeface(font);
		password.setTypeface(font);
		credencial.setTypeface(font);
		bemvindo.setTypeface(font);

		login.setOnClickListener(new OnClickListener() {

			/*
			 * NOTA "COMO PASSAR OBJECTS DO GSON NO BUNDLE":
			 * 
			 * Initial Activity Intent activity = new
			 * Intent(MyActivity.this,NextActivity.class);
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
				if (credencial.getText().toString().equals("") && password.getText().toString().equals("")) {
					Toast.makeText(Login_Activity.this, "Campos vazios! ",
							Toast.LENGTH_LONG).show();
				} else {

					if (login() == true) {
						Intent intencao = new Intent(Login_Activity.this,
								Info_Activity.class);
						// para passar info para prox activity
						intencao.putExtra("USERNAME", username);
						Login_Activity.this.startActivity(intencao);

					} else if (login() == false) {
						Toast.makeText(Login_Activity.this,
								"ERRO! Username ou Password incorrectos ",
								Toast.LENGTH_LONG).show();
						credencial.setText("");
						password.setText("");
					}
				}
			}
		});

	}

	@Override
	public void callbackCall(LoginResponse t) {
		// TODO Auto-generated method stub

	}

	public boolean login() {
		ArrayList<Users> arrayUsers = Users.generateFakeUsers();
		username = credencial.getText().toString();
		pass = password.getText().toString();
		for (Users user : arrayUsers) {
			if (user.getUser().equals(username) && user.getPass().equals(pass)) {
				return true;
			} 
		}
		return false;
	}

}