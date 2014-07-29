package com.example.itlog.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.itlog.R;
import com.example.itlog.objects.Project;

public class MeusProj_ListView_Adapter extends ArrayAdapter<Project> {

	private ArrayList<Project> projects;
	private int recurso;
	LayoutInflater inflater;
	boolean notifyOnChange = true;
	Typeface font;

	public MeusProj_ListView_Adapter(final Context context, final int recurso,
			ArrayList<Project> projects) {
		super(context, R.layout.meusprojs_layout, projects);
		this.projects = projects;
		this.recurso = recurso;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public MeusProj_ListView_Adapter(final Context context, final int recurso,
			ArrayList<Project> projects, Typeface font) {
		super(context, R.layout.meusprojs_layout, projects);
		this.projects = projects;
		this.recurso = recurso;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.font = font;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null)
			v = inflater.inflate(
					R.layout.single_row_listview_meusproj, null);
		Project row = projects.get(position);
		if (row != null) {
			TextView textView = (TextView) v
					.findViewById(R.id.textViewMeusProj);
			textView.setTypeface(font);
			textView.setText(row.getName());
		}
		return v;
	}

	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
	}

	public void setNotifyOnChange(boolean notifyOnChange) {
		// TODO Auto-generated method stub
		setNotifyOnChange(notifyOnChange);
	}
}
