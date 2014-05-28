package com.example.itlog.activities;

import java.util.ArrayList;
import java.util.List;

import Objects_General.Project;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.itlog.*;
//import com.example.itlog.adapters.MeusProj_ListView_Adapter;
//import com.example.itlog.adapters.MeusProj_Spinner_Adapter;
import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.responseobjects.ListProjectsUserResponse;
import com.example.itlog.responseobjects.ListTotalHoursProjectResponse;

public class MeusProj_Activity extends ListActivity implements
		CallbackInterface<ListProjectsUserResponse> {
	String[] meusprojectos;
	ListView listV;
	Spinner spinner;

//	ArrayList<Project> projects = new ArrayList<Project>();
//	ArrayList<Company> company = new ArrayList<Company>();
//	MeusProj_ListView_Adapter listVAdapter;
//	MeusProj_Spinner_Adapter spinnerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.meusprojs_layout);

		listV = (ListView) findViewById(android.R.id.list);
		spinner = (Spinner) findViewById(R.id.spinnerMeusProj);
		listV.setAdapter(new Adaptador(this));

		// ERRO AQUI PQ??? NAO FUNCIONA SEM O OBJECTS GENERAL EM CIMA
//		listVAdapter = new MeusProj_ListView_Adapter(this, projects);
//		spinnerAdapter = new MeusProj_Spinner_Adapter(this, company);

		ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,
				R.array.clientes_array, R.layout.spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter2);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			// Sele��o de diferentes projectos (come�a na posi��o 0)
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Adaptador adapter = (Adaptador) listV.getAdapter();
				switch (position) {
				case 0: {
					adapter.swapData(R.array.meusProjectos);
					break;
				}
				case 1: {
					adapter.swapData(R.array.meusProjectos2);
					break;
				}
				}
			}

			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
			}
		});

		// ao clicar na list View
		listV.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long arg) {
				Intent appInfo = new Intent(MeusProj_Activity.this,
						MostraInfoProj_Activity.class);
				startActivity(appInfo);

				// ....como fa�o para ir buscar os elementos certo e nao zero
				// Intent i = new Intent (MeusProj_Activity.this,
				// MostraInfoProj_Activity.class);
				// i.putExtra("NomeEmpresa", company.get(0).getNome());
				// i.putExtra("NomeProjeto", projects.get(0).getNome());
				// i.putExtra("GestorProjeto", projects.get(0).getGestor());
				// i.putExtra("Descricao", projects.get(0).getDescricao());
				// i.putExtra("Horas", projects.get(0).getHoras());
			}
		});
	}

	class SingleRow {
		String projecto;

		public SingleRow(String projecto) {
			// TODO Auto-generated constructor stub
			this.projecto = projecto;
		}

	}

	// � preciso isto?!
	class Project {
		String name;

		public Project(String name) {
			super();
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	public class Adaptador extends BaseAdapter {

		ArrayList<SingleRow> listSR;
		// isto, e preciso???
		ArrayList<Project> projects;
		Context context;

		public Adaptador(Context c) {
			// TODO Auto-generated constructor stub
			context = c;
			listSR = new ArrayList<SingleRow>();
			// � preciso???
			projects = new ArrayList<Project>();

			Resources res = c.getResources();
			// busca info de meusProjectos ----> mudar para o arraylist
			// "projects" ???????
			String[] projecto = res.getStringArray(R.array.meusProjectos);

			// VER ISTO!//
			// aqui como fa�o para ir buscar os Projects????
			ArrayList<Project> abc = new ArrayList<Project>();

			for (int i = 0; i < projecto.length; i++) {
				// percorre o array de projectos e manda para a ListView
				listSR.add(new SingleRow(projecto[i]));
			}

		}

		// metodo para 'trocar' a informa��o que vai aparecer na ListView
		public void swapData(int resId) {
			Resources res = context.getResources();
			String[] projecto = res.getStringArray(resId);
			listSR.clear();// clear � informa��o
			for (int i = 0; i < projecto.length; i++) {
				listSR.add(new SingleRow(projecto[i]));
				/*
				 * informa que a informa��o foi modificada e � preciso fazer
				 * 'refresh' para mostrar a nova info
				 */
				notifyDataSetChanged();
			}

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listSR.size();
		}

		@Override
		public Object getItem(int i) {
			// TODO Auto-generated method stub
			return listSR.get(i);
		}

		@Override
		public long getItemId(int i) {
			// TODO Auto-generated method stub
			return i;
		}

		class MyViewHolder {
			TextView tV;

			public MyViewHolder(View v) {
				tV = (TextView) v.findViewById(R.id.textView1);
			}
		}

		@Override
		// Chamada a cada "row"
		public View getView(int i, View view, ViewGroup viewGroup) {

			View row = view;
			MyViewHolder holder = null;

			if (row == null) {
				/*
				 * Inflater -> vai ao xml, le propriedades e cria objecto com
				 * essas propriedades! new object everytime: layout inflater ||
				 * same object everytime: findViewById
				 */
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				// referencia para o RelativeLayout

				row = inflater.inflate(
						R.layout.single_row_listview_addproj, viewGroup,
						false);
				holder = new MyViewHolder(row);
				row.setTag(holder);

			} else {
				holder = (MyViewHolder) row.getTag();

			}
			SingleRow temp = listSR.get(i);
			holder.tV.setText(temp.projecto);
			return row;
		}

	}

	@Override
	public void callbackCall(ListProjectsUserResponse t) {

	}

}