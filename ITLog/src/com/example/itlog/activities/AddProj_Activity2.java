package com.example.itlog.activities;

import java.util.ArrayList;
import java.util.zip.Inflater;

import android.app.AlertDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itlog.R;
import com.example.itlog.adapters.AddProject_ListView_Adapter;
import com.example.itlog.adapters.AddProject_Spinner_Adapter;
import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.objects.Company;
import com.example.itlog.objects.Project;
import com.example.itlog.responseobjects.ListAllProjectsResponse;

public class AddProj_Activity2 extends GeneralButtons_Activity implements
		CallbackInterface<ListAllProjectsResponse> {

	ArrayList<Project> projects = Project.generateFakeProjects();
	ArrayList<Project> arrayEspecifico = new ArrayList<Project>();
	ArrayList<Company> company = Company.generateFakeCompany();

	AddProject_ListView_Adapter adapterList;
	AddProject_Spinner_Adapter adapterSpinner;

	ListView listView;
	Spinner spinner;

	Typeface font;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addproj_layout);

		// para o tipo de letra
		font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");

		listView = (ListView) findViewById(R.id.list);
		spinner = (Spinner) findViewById(R.id.spinnerAddProj);

		adapterSpinner = new AddProject_Spinner_Adapter(AddProj_Activity2.this,
				R.layout.spinner_item, company, font);
		spinner.setAdapter(adapterSpinner);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				arrayEspecifico.clear();
				Company valor = adapterSpinner.getItem(position);
				for (Project auxProject : projects) {
					if (auxProject.getCompanyid() == valor.getId()
							&& auxProject.getUserid() == null) {
						arrayEspecifico.add(auxProject);
					}
				}
				adapterList = new AddProject_ListView_Adapter(
						AddProj_Activity2.this,
						R.layout.single_row_listview_addproj, arrayEspecifico,
						font);
				listView.setAdapter(adapterList);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				LayoutInflater inflate = LayoutInflater
						.from(AddProj_Activity2.this);
				View layout = inflate
						.inflate(R.layout.alertdialog_layout, null);

				TextView tv1 = (TextView) layout.findViewById(R.id.titulo);
				tv1.setText("Adicionar Projeto");
				TextView tv2 = (TextView) layout.findViewById(R.id.pergunta);
				tv2.setText("Pretende adicionar este projeto � sua lista de projetos?");
				Button b1 = (Button) layout.findViewById(R.id.botaoCancela);
				Button b2 = (Button) layout.findViewById(R.id.botaoConfirma);

				final AlertDialog.Builder builder = new AlertDialog.Builder(
						AddProj_Activity2.this);
				builder.setView(layout);
				final AlertDialog dialog = builder.create();
				dialog.show();
				b1.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						dialog.dismiss();
					}
				});

				b2.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Toast.makeText(AddProj_Activity2.this,
								"Projeto adicionado com sucesso! ",
								Toast.LENGTH_LONG).show();
						dialog.dismiss();
					}
				});
			}

		});
	}

	@Override
	public void callbackCall(ListAllProjectsResponse t) {
		// TODO Auto-generated method stub

	}

}