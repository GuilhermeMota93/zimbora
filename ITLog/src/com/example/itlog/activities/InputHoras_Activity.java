package com.example.itlog.activities;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.itlog.R;
import com.example.itlog.adapters.Calendario_Adapter;
import com.example.itlog.adapters.InputHoras_Spinner_Adapter;
import com.example.itlog.adapters.ViewPager_Adapter;
import com.example.itlog.communication.CallbackInterface;
import com.example.itlog.communication.CommunicationCenter;
import com.example.itlog.communication.GetCalendario;
import com.example.itlog.communication.GetCalendario.GetCalendarioListener;
import com.example.itlog.objects.Projecto;
import com.example.itlog.objects.TimeSheet;
import com.example.itlog.objects.TimeSheetAllocate;
import com.example.itlog.objects.TimeSheetDay;
import com.example.itlog.objects.TimeSheetPut;
import com.example.itlog.requestobjects.POST_API_TimeSheetsPut_Request;
import com.example.itlog.responseobjects.GET_API_ProjectosAndAusLst_Response;
import com.example.itlog.responseobjects.POST_API_Login_Response;
import com.example.itlog.responseobjects.POST_API_TimeSheetsPut_Response;
import com.example.itlog.responseobjects.POST_API_TimeSheets_Response;
import com.example.itlog.services.GET_API_ProjectosAndAusLst_Service;
import com.example.itlog.services.POST_API_TimeSheetsPut_Service;

public class InputHoras_Activity extends GeneralButtons_Activity implements
		GetCalendarioListener {

	POST_API_Login_Response token = POST_API_Login_Response.getInstance();
	protected static final String TAG = null;
	TimeSheetPut alocaHoras;
	public Calendar month, itemmonth;// instancias do calendario
	public Calendario_Adapter adapter;// instacia do adaptador
	InputHoras_Spinner_Adapter adapterSpinner;
	ArrayList<Projecto> projects = new ArrayList<Projecto>();
	Button imputar;
	Typeface font;
	Spinner spinner;
	ViewPager pager;
	PagerTitleStrip strip;
	ViewPager_Adapter viewPagerAdapter;
	ProgressDialog progressDialog;
	Calendar mesAtual, mesMais, mesMenos, mesMaisDois, mesMenosDois, janeiro,
			fevereiro, marco, abril, maio, junho, julho, agosto, setembro,
			outubro, novembro, dezembro;
	ArrayList<Calendar> listaMesesMostrar = new ArrayList<Calendar>();
	ArrayList<Calendario_Adapter> arrayCalendarioAdapter = new ArrayList<Calendario_Adapter>();

	TimeSheetPut allocTS;
	GetCalendario getCalendario;
	private ArrayList<POST_API_TimeSheets_Response> respostaLocal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendario_versao2);
		// para o tipo de letra
		font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
		getListaMesesMostrar();
		getCalendario = new GetCalendario(this, token, this, listaMesesMostrar);

		pager = (ViewPager) findViewById(R.id.viewPager);

		imputar = (Button) findViewById(R.id.button1);
		spinner = (Spinner) findViewById(R.id.spinnerGridView1);

		getServiceListaProjsAus();

		// posicao onde come�a o ViewPager
		month = Calendar.getInstance();

		pager.setOnPageChangeListener(new OnPageChangeListener() {

			int posAntes = pager.getCurrentItem();

			@Override
			public void onPageSelected(int posicaoAtual) {
				// TODO Auto-generated method stub

				if (posicaoAtual >= posAntes) {
					Log.i(TAG, "SWIPING RIGHT");

					// se chegar ao max do viewpager (com o getCount)
					if (posicaoAtual == viewPagerAdapter.getCount() - 1) {
						// limpa a lista, e cria com o (YEAR+1)

						if (arrayCalendarioAdapter.get(posAntes) != null) {
							viewPagerAdapter.getListaMesesMostrarMaisUm();
							viewPagerAdapter.notifyDataSetChanged();
							posAntes++;
						}
					}
				} else if (posicaoAtual <= posAntes) {
					Log.i(TAG, "SWIPING LEFT");

					// se chegar ao min do viewpager (com o getCount)
					if (posicaoAtual == 0) {
						// limpa a lista, e cria de novo com (YEAR-1)
						if (arrayCalendarioAdapter.get(posAntes) == null) {
							viewPagerAdapter.getListaMesesMostarMenosUm();
							// progressbar aqui
							viewPagerAdapter.notifyDataSetChanged();
							pager.setCurrentItem(12);
							posAntes--;
						}
					}
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				// progressBar.setVisibility(View.GONE);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		imputar.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				// adapter = new Calendario_Adapter(InputHoras_Activity.this,
				// month,);

				LayoutInflater inflate = LayoutInflater
						.from(InputHoras_Activity.this);
				View layout = inflate.inflate(
						R.layout.botao_quatro_oito_horas_layout, null);
				TextView tv1 = (TextView) layout.findViewById(R.id.titulo);
				tv1.setText("Quantas horas pretende adicionar a este projeto?");
				TextView tv2 = (TextView) layout.findViewById(R.id.pergunta);
				tv2.setText("Se imputar 8 horas neste projeto, n�o poder� imputar horas a mais nenhum projecto neste dia!");
				Button quatroHoras = (Button) layout
						.findViewById(R.id.botaoQuatroHoras);
				Button oitoHoras = (Button) layout
						.findViewById(R.id.botaoOitoHoras);
				Button zeroHoras = (Button) layout
						.findViewById(R.id.botaoZeroHoras);
				final AlertDialog.Builder builder = new AlertDialog.Builder(
						InputHoras_Activity.this);
				builder.setView(layout);
				final AlertDialog dialog = builder.create();
				dialog.show();

				quatroHoras.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						arrayCalendarioAdapter.get(pager.getCurrentItem())
								.escreveQuatro();
						for (int i = 0; i < arrayCalendarioAdapter
								.get(pager.getCurrentItem())
								.getViewsPreencher().size(); i++) {
							arrayCalendarioAdapter.get(pager.getCurrentItem())
									.getViewsPreencher().get(i)
									.setBackgroundColor(Color.WHITE);
						}
						arrayCalendarioAdapter.get(pager.getCurrentItem())
								.getViewsPreencher().clear();
						arrayCalendarioAdapter.get(pager.getCurrentItem())
								.getArraySelecionaDias().clear();
						dialog.dismiss();
					}
				});

				oitoHoras.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						arrayCalendarioAdapter.get(pager.getCurrentItem())
								.escreveOito();
						for (int i = 0; i < arrayCalendarioAdapter
								.get(pager.getCurrentItem())
								.getViewsPreencher().size(); i++) {
							arrayCalendarioAdapter.get(pager.getCurrentItem())
									.getViewsPreencher().get(i)
									.setBackgroundColor(Color.WHITE);
						}
						arrayCalendarioAdapter.get(pager.getCurrentItem())
								.getViewsPreencher().clear();
						arrayCalendarioAdapter.get(pager.getCurrentItem())
								.getArraySelecionaDias().clear();
						dialog.dismiss();

					}
				});

				zeroHoras.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						arrayCalendarioAdapter.get(pager.getCurrentItem())
								.escreveZero();
						for (int i = 0; i < arrayCalendarioAdapter
								.get(pager.getCurrentItem())
								.getViewsPreencher().size(); i++) {
							arrayCalendarioAdapter.get(pager.getCurrentItem())
									.getViewsPreencher().get(i)
									.setBackgroundColor(Color.WHITE);
						}
						arrayCalendarioAdapter.get(pager.getCurrentItem())
								.getViewsPreencher().clear();
						arrayCalendarioAdapter.get(pager.getCurrentItem())
								.getArraySelecionaDias().clear();
						dialog.dismiss();
					}
				});

				dialog.setCanceledOnTouchOutside(true);
			}
		});

	}

	public void getServiceListaProjsAus() {

		progressDialog = ProgressDialog.show(this, "Um momento, por favor",
				"A carregar dados...", true);
		progressDialog.setCancelable(true);
		new GET_API_ProjectosAndAusLst_Service(new CallbackListaProjsAus(),
				CommunicationCenter.GetLstProjsEAusencias).execute(token
				.getToken());
	}

	private class CallbackListaProjsAus implements
			CallbackInterface<GET_API_ProjectosAndAusLst_Response> {

		@Override
		public void callbackCall(GET_API_ProjectosAndAusLst_Response t) {
			// TODO Auto-generated method stub
			projects = t.getProjectos();
			adapterSpinner = new InputHoras_Spinner_Adapter(
					InputHoras_Activity.this, R.layout.spinner_item, projects,
					font);
			spinner.setAdapter(adapterSpinner);

			progressDialog.dismiss();
		}

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
		case R.id.enviar:
			alocarHorasMetodo();
			getTimeSheetsPut(alocaHoras);
		}
		return true;
	}

	@Override
	public void onGetCalendarioComplete(
			ArrayList<POST_API_TimeSheets_Response> respostasArray) {

		respostaLocal = respostasArray;
		// TODO Auto-generated method stub
		for (int i = 0; i < 12; i++) {
			Calendario_Adapter adapter = null;
			if (respostasArray.get(i).getStatusCd().equals("OK")) {
				adapter = new Calendario_Adapter(this,
						listaMesesMostrar.get(i), respostasArray.get(i));
			}
			arrayCalendarioAdapter.add(adapter);
		}
		viewPagerAdapter = new ViewPager_Adapter(InputHoras_Activity.this,
				arrayCalendarioAdapter);
		pager.setAdapter(viewPagerAdapter);
		pager.setCurrentItem(month.get(Calendar.MONTH));
		progressDialog.dismiss();

	}

	public ArrayList<Calendar> getListaMesesMostrar() {
		janeiro = Calendar.getInstance();
		janeiro.set(Calendar.MONTH, Calendar.JANUARY);
		fevereiro = Calendar.getInstance();
		fevereiro.set(Calendar.MONTH, Calendar.FEBRUARY);
		marco = Calendar.getInstance();
		marco.set(Calendar.MONTH, Calendar.MARCH);
		abril = Calendar.getInstance();
		abril.set(Calendar.MONTH, Calendar.APRIL);
		maio = Calendar.getInstance();
		maio.set(Calendar.MONTH, Calendar.MAY);
		junho = Calendar.getInstance();
		junho.set(Calendar.MONTH, Calendar.JUNE);
		julho = Calendar.getInstance();
		julho.set(Calendar.MONTH, Calendar.JULY);
		agosto = Calendar.getInstance();
		agosto.set(Calendar.MONTH, Calendar.AUGUST);
		setembro = Calendar.getInstance();
		setembro.set(Calendar.MONTH, Calendar.SEPTEMBER);
		outubro = Calendar.getInstance();
		outubro.set(Calendar.MONTH, Calendar.OCTOBER);
		novembro = Calendar.getInstance();
		novembro.set(Calendar.MONTH, Calendar.NOVEMBER);
		dezembro = Calendar.getInstance();
		dezembro.set(Calendar.MONTH, Calendar.DECEMBER);

		listaMesesMostrar.add(janeiro);
		listaMesesMostrar.add(fevereiro);
		listaMesesMostrar.add(marco);
		listaMesesMostrar.add(abril);
		listaMesesMostrar.add(maio);
		listaMesesMostrar.add(junho);
		listaMesesMostrar.add(julho);
		listaMesesMostrar.add(agosto);
		listaMesesMostrar.add(setembro);
		listaMesesMostrar.add(outubro);
		listaMesesMostrar.add(novembro);
		listaMesesMostrar.add(dezembro);

		return listaMesesMostrar;

	}

	public void getTimeSheetsPut(TimeSheetPut allocTS) {

		new POST_API_TimeSheetsPut_Service(new CallbackTimeSheetsPut(),
				CommunicationCenter.PostTimeSheetsPut,
				new POST_API_TimeSheetsPut_Request(allocTS, token.getToken()))
				.execute(new String[0]);

	}

	public class CallbackTimeSheetsPut implements
			CallbackInterface<POST_API_TimeSheetsPut_Response> {

		@Override
		public void callbackCall(POST_API_TimeSheetsPut_Response t3) {
			// TODO Auto-generated method stub
			POST_API_TimeSheetsPut_Response respostaDoCallbackTSPut = t3;
		}

	}

	// Metodo para o servi�o de alocar horas. Criar objectos que vao enviar para
	// o servi�o
	// Falta cria o obj TimeSheetAllocate e o TimeSheetDay

	public TimeSheetPut alocarHorasMetodo() {
		trataTodosDias();
		// TimeSheetPut allocTS = new TimeSheetPut(arrayCalendarioAdapter.get(
		// pager.getCurrentItem()), mes, trataDiasAlocar());

		return allocTS;
	}

	public ArrayList<TimeSheetDay> trataTodosDias() {
		trataDiasAlocar();
		ArrayList<TimeSheetDay> diasTodosAlocar = new ArrayList<TimeSheetDay>();
		TimeSheetDay alocaTodos;
		Calendario_Adapter mes = arrayCalendarioAdapter.get(pager
				.getCurrentItem());

		onGetCalendarioComplete(respostaLocal);
		
		for (int i = 0; i < arrayCalendarioAdapter.size(); i++) {
			TimeSheetDay dia = respostaLocal.get(i).getImpt().getDia().get(i);
//			alocaTodos = new  TimeSheetDay(
//					dia.getDia(),
//					dia.getDiaDaSemana(),
//					dia.isDiaFeriado(),
//					dia.isDiaAvlbToAllocate(),
//					);
			
			
			//UTILIZAR "respostaLocal" criado la em cima!!!!
//
//			 alocaTodos = new TimeSheetDay(month.get(Calendar.DAY_OF_MONTH),
//			 month.get(Calendar.DAY_OF_WEEK_IN_MONTH),
//			 ,
//			 diaAvlbToAllocate,
//			 trataDiasAlocar());
		}

		return diasTodosAlocar;

	}

	public ArrayList<TimeSheetAllocate> trataDiasAlocar() {
		ArrayList<TimeSheetAllocate> diasAlocar = new ArrayList<TimeSheetAllocate>();
		TimeSheetAllocate aloca;
		ArrayList<View> tamanhoMes = arrayCalendarioAdapter.get(
				pager.getCurrentItem()).getViewsPreencher();
		for (int i = 0; i < tamanhoMes.size(); i++) {
			aloca = new TimeSheetAllocate(spinner.getSelectedItem().toString(),
					Integer.parseInt(adapter.getListaPopuladaString().get(i)),
					0);
			diasAlocar.add(aloca);
		}
		return diasAlocar;
	}

}
