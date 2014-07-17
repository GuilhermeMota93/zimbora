package com.example.itlog.activities;

import java.util.ArrayList;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.itlog.R;
import com.example.itlog.adapters.MeusProj_ListView_Adapter;
import com.example.itlog.adapters.MeusProj_Spinner_Adapter;
import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.objects.Company;
import com.example.itlog.objects.Project;
import com.example.itlog.responseobjects.ListProjectsUserResponse;

public class MeusProj_Activity2 extends GeneralButtons_Activity implements
		CallbackInterface<ListProjectsUserResponse> {

	ArrayList<Project> projects = Project.generateFakeProjects();
	ArrayList<Project> arrayEspecifico = new ArrayList<Project>();
	ArrayList<Company> company = Company.generateFakeCompany();

	MeusProj_ListView_Adapter adapterList;
	MeusProj_Spinner_Adapter adapterSpinner;

	ListView listView;
	Spinner spinner;

	Typeface font;
	// o user vem em forma de string desde o log in
	String info;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meusprojs_layout);

		// para o tipo de letra
		font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");

		listView = (ListView) findViewById(R.id.list);
		spinner = (Spinner) findViewById(R.id.spinnerMeusProj);

		adapterSpinner = new MeusProj_Spinner_Adapter(MeusProj_Activity2.this,
				R.layout.spinner_item, company, font);
		spinner.setAdapter(adapterSpinner);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				arrayEspecifico.clear();
				// buscar info de user de tras, que vem do log in
				info = getIntent().getExtras().getString("USERNAME");
				Company valor = adapterSpinner.getItem(position);
				for (Project auxProject : projects) {
					if (auxProject.getUserid() != null
							&& auxProject.getCompanyid() == valor.getId()) {
						arrayEspecifico.add(auxProject);
					}
				}
				adapterList = new MeusProj_ListView_Adapter(
						MeusProj_Activity2.this,
						R.layout.single_row_listview_meusproj, arrayEspecifico,
						font);
				listView.setAdapter(adapterList);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	public void callbackCall(ListProjectsUserResponse t) {
		// TODO Auto-generated method stub

	}

}