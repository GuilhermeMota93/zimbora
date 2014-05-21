package com.example.itlog.adapters;

import java.util.ArrayList;
import java.util.List;

import Objects_General.Company;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.itlog.R;

public class MeusProj_Spinner_Adapter extends ArrayAdapter<Company> {

	private Context context;
	private ArrayList<Company> company;
	private LayoutInflater inflater;
	private boolean notifyOnChange = true;

	public MeusProj_Spinner_Adapter(Context context,
			ArrayList<Company> company, LayoutInflater inflater) {
		super(context, R.layout.spinner_item);
		this.context = context;
		this.company = new ArrayList<Company>(company);
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public MeusProj_Spinner_Adapter(Context context,
			ArrayList<Company> company) {
		super(context, R.layout.spinner_item);
		this.context = context;
		this.company = new ArrayList<Company>(company);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return super.getCount();
	}

	@Override
	public Company getItem(int position) {
		// TODO Auto-generated method stub
		return super.getItem(position);
	}

	@Override
	public int getPosition(Company item) {
		// TODO Auto-generated method stub
		return super.getPosition(item);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return super.getItemId(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Company c = company.get(position);
		final ViewHolderSpinner holder;
		int type = getItemViewType(position);
		if (convertView == null) {
			holder = new ViewHolderSpinner();
			switch (type) {
			case 1:
				convertView = inflater.inflate(R.layout.spinner_item, parent,
						false);
				holder.name = (TextView) convertView.findViewById(R.id.text1);
				break;
			}
			convertView.setTag(holder);
		}else{
			holder = (ViewHolderSpinner) convertView.getTag();
		}
		holder.name.setText(c.getNome());
		holder.pos = position;

		return convertView;

	}

	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
	}

	@Override
	public void setNotifyOnChange(boolean notifyOnChange) {
		// TODO Auto-generated method stub
		super.setNotifyOnChange(notifyOnChange);
	}

	class ViewHolderSpinner {

		TextView name;
		int pos; // to store the position of the item within the list
	}
	
}