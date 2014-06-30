package com.example.itlog.adapters;

import java.util.ArrayList;

import com.example.itlog.R;
import com.example.itlog.objects.Project;

import android.content.Context;
import android.graphics.Typeface;
import android.renderscript.Type;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AddProject_ListView_Adapter extends ArrayAdapter<Project> {

	private Context context;
	private ArrayList<Project> projects;
	private LayoutInflater inflater;
	private boolean notifyOnChange = true;

	// TYPE FACE FONT PARA O ADAPTER -------> VER ISTO
	private Typeface font;

	public AddProject_ListView_Adapter(Context context,
			ArrayList<Project> projects, LayoutInflater inflater, Typeface font) {
		super(context, R.layout.single_row_listview_addproj);
		this.font = font;
		this.context = context;
		this.projects = new ArrayList<Project>(projects);
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public AddProject_ListView_Adapter(Context context,
			ArrayList<Project> projects) {
		super(context, R.layout.single_row_listview_addproj);
		this.context = context;
		this.projects = new ArrayList<Project>(projects);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return super.getCount();
	}

	@Override
	public Project getItem(int position) {
		// TODO Auto-generated method stub
		return super.getItem(position);
	}

	@Override
	public int getPosition(Project item) {
		// TODO Auto-generated method stub
		return super.getPosition(item);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return super.getItemId(position);
	}

	@Override
	public int getViewTypeCount() {
		return 1; // Number of types + 1 !!!!!!!!
	}

	@Override
	public int getItemViewType(int position) {
		return 1;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Project p = projects.get(position);
		final ViewHolderListViewMeusProj holder;
		int type = getItemViewType(position);

		if (convertView == null) {
			holder = new ViewHolderListViewMeusProj();
			switch (type) {
			case 1:
				convertView = inflater.inflate(
						R.layout.single_row_listview_addproj, parent, false);
				holder.name = (TextView) convertView
						.findViewById(R.id.textView1);

				break;
			}
			convertView.setTag(holder);
		} else {
			holder = (ViewHolderListViewMeusProj) convertView.getTag();
		}
		holder.name.setText(p.getNome());
		holder.pos = position;

		return convertView;
	}

	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
		notifyOnChange = true;
	}

	public void setNotifyOnChange(boolean notifyOnChange) {
		this.notifyOnChange = notifyOnChange;
	}

}

class ViewHolderListViewAddProj {

	TextView name;
	int pos; // to store the position of the item within the list
}
