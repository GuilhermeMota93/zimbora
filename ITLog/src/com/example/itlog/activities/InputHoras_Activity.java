package com.example.itlog.activities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itlog.R;
import com.example.itlog.adapters.Calendario_Adapter;
import com.example.itlog.adapters.InputHoras_Spinner_Adapter;
import com.example.itlog.objects.Project;

public class InputHoras_Activity extends GeneralButtons_Activity {

	public Calendar month, itemmonth;// instancias do calendario
	public Calendario_Adapter adapter;// instacia do adaptador
	public Handler handler;// para mostrar o 'ponto' nos eventos. futuramente
							// retirar
	public ArrayList<String> items;// contentor para guardar os items de
									// calendario necess�rios para os eventos

	GridView myGrid;
	// TextView title;

	InputHoras_Spinner_Adapter adapterSpinner;
	ArrayList<Project> projects = Project.generateFakeProjects();
	ArrayList<Project> arrayEspecifico = new ArrayList<Project>();

	Button imputar;
	Typeface font;
	Spinner spinner;
	// o username vem em forma de string desde o log in
	String info;
	ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendario_versao2);

		// buscar info de user de tras, que vem do log in
		info = getIntent().getExtras().getString("USERNAME").toString();
		// para o tipo de letra
		font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
		month = Calendar.getInstance();
		itemmonth = (Calendar) month.clone();
		items = new ArrayList<String>();
		adapter = new Calendario_Adapter(this, month);
		myGrid = (GridView) findViewById(R.id.gridViewCustom2);
		myGrid.setAdapter(adapter);
		handler = new Handler();
		handler.post(calendarUpdater);// generate some calendar items
		// title = (TextView) findViewById(R.id.title);
		// title.setText(android.text.format.DateFormat.format("MMMM yyyy",
		// month));
		viewPager = (ViewPager) findViewById(R.id.meuViewPager);

		imputar = (Button) findViewById(R.id.button1);
		spinner = (Spinner) findViewById(R.id.spinnerGridView1);
		getListaProjsUser();
		adapterSpinner = new InputHoras_Spinner_Adapter(
				InputHoras_Activity.this, R.layout.spinner_item,
				arrayEspecifico, font);
		spinner.setAdapter(adapterSpinner);

		// RelativeLayout previous = (RelativeLayout)
		// findViewById(R.id.previous);
		//
		// previous.setOnClickListener(new OnClickListener() {
		//
		// public void onClick(View v) {
		// setPreviousMonth();// mes anterior
		// refreshCalendar();// update ao calendario
		//
		// }
		// });
		//
		// RelativeLayout next = (RelativeLayout) findViewById(R.id.next);
		// next.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// setNextMonth();// prox mes
		// refreshCalendar();// update ao calendario
		//
		// }
		// });

		myGrid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				((Calendario_Adapter) parent.getAdapter()).setSelected(view);
				String selectedGridDate = Calendario_Adapter.dayString
						.get(position);
				String[] separatedTime = selectedGridDate.split("-");
				// tira ultima parte de uma data. ex: 2 from 2012-12-02
				String gridvalueString = separatedTime[2].replaceFirst("^0*",
						"");
				int gridValue = Integer.parseInt(gridvalueString);
				// vai para o proximo ou anterior mes ao clickar nos dias 'off'
				if ((gridValue > 10) && (position < 8)) {
					setPreviousMonth();
					refreshCalendar();
				} else if ((gridValue < 7) && (position > 28)) {
					setNextMonth();
					refreshCalendar();
				}
				((Calendario_Adapter) parent.getAdapter()).setSelected(view);

				showToast(selectedGridDate);
			}
		});

		// imputar.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {// accao ao clicar ao ADICIONAR
		// // PROJECTO
		// // TODO Auto-generated method stub
		// Intent intencao = new Intent(InputHoras_Activity.this,
		// ConfirmaHoras_Activity.class);// ao carregar no botao
		// // IMPUTAR HORAS vai
		// // para InputHoras
		// InputHoras_Activity.this.startActivity(intencao);
		// }
		// });

		imputar.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				LayoutInflater inflate = LayoutInflater
						.from(InputHoras_Activity.this);
				View layout = inflate.inflate(R.layout.escolher_horas_layout,
						null);
				;

				TextView tv1 = (TextView) layout.findViewById(R.id.titulo);
				tv1.setText("Quantas Horas pretende adicionar a este projeto?");
				TextView tv2 = (TextView) layout.findViewById(R.id.pergunta);
				tv2.setText("Se imputar 8 horas neste projeto, n�o poder� imputar horas a mais nenhum projecto neste dia!");
				Button b1 = (Button) layout.findViewById(R.id.botaoQuatroHoras);
				Button b2 = (Button) layout.findViewById(R.id.botaoOitoHoras);

				final AlertDialog.Builder builder = new AlertDialog.Builder(
						InputHoras_Activity.this);
				builder.setView(layout);
				final AlertDialog dialog = builder.create();
				dialog.show();
				b1.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Toast.makeText(InputHoras_Activity.this,
								"4 Horas adicionadas com sucesso! ",
								Toast.LENGTH_LONG).show();
						dialog.dismiss();
					}
				});

				b2.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Toast.makeText(InputHoras_Activity.this,
								"8 Horas adicionadas com sucesso! ",
								Toast.LENGTH_LONG).show();
						dialog.dismiss();
					}
				});

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actionbar_buttons, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();

		}
		return true;
	}

	protected void setNextMonth() {
		if (month.get(Calendar.MONTH) == month.getActualMaximum(Calendar.MONTH)) {
			month.set((month.get(Calendar.YEAR) + 1),
					month.getActualMinimum(Calendar.MONTH), 1);
		} else {
			month.set(Calendar.MONTH, month.get(Calendar.MONTH) + 1);
		}
		refreshCalendar();

	}

	protected void setPreviousMonth() {
		if (month.get(Calendar.MONTH) == month.getActualMinimum(Calendar.MONTH)) {
			month.set((month.get(Calendar.YEAR) - 1),
					month.getActualMaximum(Calendar.MONTH), 1);
		} else {
			month.set(Calendar.MONTH, month.get(Calendar.MONTH) - 1);
		}
		refreshCalendar();
	}

	protected void showToast(String string) {
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();

	}

	public void refreshCalendar() {
		// TextView title = (TextView) findViewById(R.id.title);

		adapter.refreshDays();
		adapter.notifyDataSetChanged();
		handler.post(calendarUpdater); // generate some calendar items

		// title.setText(android.text.format.DateFormat.format("MMMM yyyy",
		// month));
	}

	public Runnable calendarUpdater = new Runnable() {

		@Override
		public void run() {
			items.clear();

			// Print dates of the current week
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String itemvalue;
			for (int i = 0; i < 7; i++) {
				itemvalue = df.format(itemmonth.getTime());
				itemmonth.add(Calendar.DATE, 1);

			}

			adapter.setItems(items);
			adapter.notifyDataSetChanged();
		}
	};

	public ArrayList<Project> getListaProjsUser() {
		arrayEspecifico.clear();
		for (Project auxProject : projects) {
			if (auxProject.getUserid() != null
					&& (auxProject.getUserid()).equals(info)) {
				arrayEspecifico.add(auxProject);
			}
		}
		return arrayEspecifico;

	}

}
