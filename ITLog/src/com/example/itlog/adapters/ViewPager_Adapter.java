package com.example.itlog.adapters;

import com.example.itlog.fragments.GridView_Anterior;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

public class ViewPager_Adapter extends FragmentPagerAdapter {

	public ViewPager_Adapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int i) {
		// TODO Auto-generated method stub
		Fragment fragment = null;
		if (i == 0) {
			// como por info de mes anterior neste fragment?????
			fragment = new GridView_Anterior();

		}

		return fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

}
