package com.example.itlog.fragments;

import com.example.itlog.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GridView_Atual extends Fragment {

	private int number;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		switch (number) {
		case 0:
			//fazer coisas aqui
			break;
		case 1:
			//etc etc
		}
		return inflater.inflate(R.layout.gridview_atual, container, false);
	}

}



