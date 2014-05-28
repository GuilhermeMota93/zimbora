package com.example.itlog.activities;

import java.lang.reflect.Array;
import java.util.ArrayList;

import Objects_General.Company;
import Objects_General.Project;
import android.app.Activity;
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
import com.example.itlog.responseobjects.ListProjectsUserResponse;

public class MeusProj_Activity2 extends Activity implements
		CallbackInterface<ListProjectsUserResponse> {

	ArrayList<Project> projects;
	ArrayList<Company> company;
	MeusProj_ListView_Adapter adapterList;
	MeusProj_Spinner_Adapter adapterSpinner;
	ListView listView;
	Spinner spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		listView = (ListView) findViewById(android.R.id.list);
		spinner = (Spinner) findViewById(R.id.textSpinnerItem);
		projects = new ArrayList<Project>();
		company = new ArrayList<Company>();
		adapterSpinner = new MeusProj_Spinner_Adapter(this,
				R.layout.spinner_item, company);
		adapterList = new MeusProj_ListView_Adapter(this, R.layout.single_row_listview_addproj, projects);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {				
				listView.setAdapter(adapterList);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		spinner.setAdapter(adapterSpinner);
	}

	@Override
	public void callbackCall(ListProjectsUserResponse t) {
		// TODO Auto-generated method stub

	}

}
