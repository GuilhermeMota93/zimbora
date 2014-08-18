package com.example.itlog.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.itlog.R;

public class Calendario2_Adapter extends ArrayAdapter {

	private int recurso;
	Context context;
	LayoutInflater inflater;
	ArrayList<String> list = new ArrayList<String>();
	ImageButton imageButton;
	TextView textView;

	public Calendario2_Adapter(final Context context, final int recurso,
			ArrayList<String> list) {
		super(context, R.layout.calendario_versao2, list);
		this.list = list;
		this.recurso = recurso;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		if (v == null)
			v = inflater.inflate(R.layout.gridview_item, null);
		textView = (TextView) v.findViewById(R.id.textViewCalendarItem2);
		textView.setText(list.get(position));
//		imageButton = (ImageButton) v
//				.findViewById(R.id.imageButtonCalendarItem2);
		return v;
	}

}
