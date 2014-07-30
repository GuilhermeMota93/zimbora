package com.example.itlog.activities;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.itlog.R;
import com.example.itlog.adapters.Calendario2_Adapter;

public class InputHoras_Activity2 extends GeneralButtons_Activity {
	GridView grid;
	Calendario2_Adapter calendarioAdapter;
	TextView tv1;
	ImageButton imgButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendario_versao2);

		grid = (GridView) findViewById(R.id.gridViewCustom2);
		imgButton = (ImageButton) findViewById(R.id.imageButtonCalendarItem2);
		tv1 = (TextView) findViewById(R.id.textViewCalendarItem2);

		ArrayList<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");

		calendarioAdapter = new Calendario2_Adapter(InputHoras_Activity2.this,
				R.layout.calendario_item2, list);
		grid.setAdapter(calendarioAdapter);
		grid.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE);
		
		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				imgButton.setClickable(true);
			}

		});
	}

}
