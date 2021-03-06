package com.example.viewpagertest1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Calendario_Adapter extends BaseAdapter {

	private Context mContext;
	private java.util.Calendar month;

	// instacia para mes anterior
	public GregorianCalendar pmonth;

	// instancia de mes anterior para ter View completa
	public GregorianCalendar pmonthmaxset;

	private GregorianCalendar selectedDate;

	int firstDay, maxWeeknumber, maxP, calMaxP, lastWeekDay, leftDays,
			mnthlength;

	String itemvalue, curentDateString;

	SimpleDateFormat df;

	int[] checkStates;

	private ArrayList<String> items;
	public static List<String> dayString;
	private View previousView;
	Calendar cal;

	public Calendario_Adapter(Context c, Calendar month2) {
		Calendario_Adapter.dayString = new ArrayList<String>();
		month = month2;
		selectedDate = (GregorianCalendar) month2.clone();
		mContext = c;
		month.set(GregorianCalendar.DAY_OF_MONTH, 1);
		this.items = new ArrayList<String>();
		df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		curentDateString = df.format(selectedDate.getTime());
		refreshDays();
	}

	public void setItems(ArrayList<String> items) {
		for (int i = 0; i != items.size(); i++) {
			if (items.get(i).length() == 1) {
				items.set(i, "0" + items.get(i));
			}
		}
		this.items = items;
	}

	public int getCount() {
		return dayString.size();
	}

	public Object getItem(int position) {
		return dayString.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new view for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		TextView dayView;
		final ImageView imgViewGrid;
		// CheckBox checkBox;

		if (convertView == null) { // if it's not recycled, initialize some
			// attributes
			LayoutInflater vi = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.calendario_item2, null);

		}
		dayView = (TextView) v.findViewById(R.id.textViewCalendarItem2);
		imgViewGrid = (ImageView) v.findViewById(R.id.imageGrid);

		// checkBox = (CheckBox) v.findViewById(R.id.checkBox1);

		checkStates = new int[dayString.size()];
		for (int i = 0; i < items.size(); i++) {
			checkStates[i] = 0;
		}
		// checkBox.setTag(position);
		//
		// checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		// //guardar os 'checks' nas checkboxes
		// @Override
		// public void onCheckedChanged(CompoundButton buttonView, boolean
		// isChecked) {
		//
		// int pos = (Integer) buttonView.getTag();
		// if(!buttonView.isChecked()){
		// checkStates[pos] = 0;
		// }else{
		// checkStates[pos] = 1;
		// }
		// notifyDataSetChanged();
		// }
		// });

		// separates daystring into parts.
		String[] separatedTime = dayString.get(position).split("-");
		// taking last part of date. ie; 2 from 2012-12-02
		String gridvalue = separatedTime[2].replaceFirst("^0*", "");

		// checking whether the day is in current month or not.
		if ((Integer.parseInt(gridvalue) > 1) && (position < firstDay)) {
			// setting offdays to white color.
			dayView.setTextColor(Color.WHITE);
			// checkBox.setClickable(false);
			dayView.setFocusable(false);

		} else if ((Integer.parseInt(gridvalue) < 7) && (position > 28)) {
			dayView.setTextColor(Color.WHITE);
			// dayView.setClickable(false);
			// dayView.setFocusable(false);

		}
		// dia selecionado aqui
		if (dayString.get(position).equals(curentDateString)) {
			setSelected(v);
			previousView = v;
		} else {
			v.setBackgroundResource(R.drawable.list_item_background);
		}

		dayView.setText(gridvalue);

		imgViewGrid.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				imgViewGrid.setSelected(event.getAction() == MotionEvent.ACTION_DOWN);
				return true;
			}
		});

		// create date string for comparison
		String date = dayString.get(position);

		if (date.length() == 1) {
			date = "0" + date;
		}
		String monthStr = "" + (month.get(GregorianCalendar.MONTH) + 1);
		if (monthStr.length() == 1) {
			monthStr = "0" + monthStr;
		}

		// show icon if date is not empty and it exists in the items array
		/*
		 * ImageView iw = (ImageView) v.findViewById(R.id.date_icon); if
		 * (date.length() > 0 && items != null && items.contains(date)) {
		 * iw.setVisibility(View.VISIBLE); } else {
		 * iw.setVisibility(View.INVISIBLE); }
		 */
		return v;
	}

	// Cor azul a volta do dia selecionado!
	public View setSelected(View view) {
		if (previousView != null) {
			previousView.setBackgroundResource(R.drawable.list_item_background);
		}
		previousView = view;
		view.setBackgroundResource(R.drawable.calendar_cel_selectl);
		return view;
	}

	public void refreshDays() {
		// clear items
		items.clear();
		dayString.clear();
		pmonth = (GregorianCalendar) month.clone();
		// month start day. ie; sun, mon, etc
		firstDay = month.get(GregorianCalendar.DAY_OF_WEEK);
		// finding number of weeks in current month.
		maxWeeknumber = month.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH);
		if (maxWeeknumber >= 6)
			maxWeeknumber = 5;
		// allocating maximum row number for the gridview.
		mnthlength = maxWeeknumber * 7;
		maxP = getMaxP(); // previous month maximum day 31,30....
		calMaxP = maxP - (firstDay - 1);// calendar offday starting 24,25 ...
		/**
		 * Calendar instance for getting a complete gridview including the three
		 * month's (previous,current,next) dates.
		 */
		pmonthmaxset = (GregorianCalendar) pmonth.clone();
		/**
		 * setting the start date as previous month's required date.
		 */
		pmonthmaxset.set(GregorianCalendar.DAY_OF_MONTH, calMaxP + 1);

		/**
		 * filling calendar gridview.
		 */
		for (int n = 0; n < mnthlength; n++) {

			itemvalue = df.format(pmonthmaxset.getTime());
			pmonthmaxset.add(GregorianCalendar.DATE, 1);
			dayString.add(itemvalue);

		}
	}

	private int getMaxP() {
		int maxP;
		if (month.get(GregorianCalendar.MONTH) == month
				.getActualMinimum(GregorianCalendar.MONTH)) {
			pmonth.set((month.get(GregorianCalendar.YEAR) - 1),
					month.getActualMaximum(GregorianCalendar.MONTH), 1);
		} else {
			pmonth.set(GregorianCalendar.MONTH,
					month.get(GregorianCalendar.MONTH) - 1);
		}
		maxP = pmonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

		return maxP;
	}

}