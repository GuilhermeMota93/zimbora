package com.example.itlog.activities;

import com.example.itlog.GetDeviceActivity;
import com.example.itlog.GetDevice_RequestObj;
import com.example.itlog.GetDevice_Service;
import com.example.itlog.R;
import com.example.itlog.TesteAosServicos;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.requestobjects.GetSessionInformationRequest;
import com.example.itlog.responseobjects.GetSessionInformationResponse;
import com.example.itlog.services.GetSessionInformationService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class InfoActivity extends Activity {

	TextView nrCred, nomeP, mail;
	Button meuProj, maisProj, addHoras, testarServicos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_activity_layout);

		nrCred = (TextView) findViewById(R.id.credNum);
		nomeP = (TextView) findViewById(R.id.nomePess);
		mail = (TextView) findViewById(R.id.emailIT);
		meuProj = (Button) findViewById(R.id.meusProj);
		maisProj = (Button) findViewById(R.id.addProj);
		addHoras = (Button) findViewById(R.id.inputHoras);
		testarServicos = (Button) findViewById(R.id.testarServicos);
		
		//falta o servi�o aqui?!
//		new GetSessionInformationService(InfoActivity.this,
//				CommunicationCenter.GetDevices,
//				new GetSessionInformationRequest(t.getUsername()).execute(new String[0]);

		meuProj.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {// accao ao clicar ao ADICIONAR
											// PROJECTO
				// TODO Auto-generated method stub
				Intent intencao = new Intent(InfoActivity.this,
						MeusProj_Activity.class);// ao carregar no botao MEUS
													// PROJECTOS vai para
													// MeusProj
				InfoActivity.this.startActivity(intencao);
			}
		});

		maisProj.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// accao ao clicar ao ADICIONAR PROJECTO
				// TODO Auto-generated method stub
				Intent intencao = new Intent(InfoActivity.this,
						AddProj_Activity.class);// ao carregar no botao
												// ADICIONAR PROJECTOS vai para
												// AddProj_Activity
				InfoActivity.this.startActivity(intencao);
			}
		});

		addHoras.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {// accao ao clicar ao ADICIONAR
											// PROJECTO
				// TODO Auto-generated method stub
				Intent intencao = new Intent(InfoActivity.this,
						InputHoras_Activity.class);// ao carregar no botao
													// IMPUTAR HORAS vai para
													// InputHoras
				InfoActivity.this.startActivity(intencao);
			}
		});

		testarServicos.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {// accao ao clicar ao ADICIONAR
											// PROJECTO
				// TODO Auto-generated method stub
				Intent intencao = new Intent(InfoActivity.this,
						TesteAosServicos.class);// ao carregar no botao IMPUTAR
												// HORAS vai para InputHoras
				InfoActivity.this.startActivity(intencao);
			}
		});

	}

	public void callbackCall(GetSessionInformationResponse t) {
		// nr funcionario
		nrCred.setText(t.getUserid());
		// nome funcionario
		nomeP.setText(t.getFullname());
		// mail funcionario
		mail.setText(t.getEmail());

	}

}
