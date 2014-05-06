package com.example.volleyjsonexample;

import java.util.ArrayList;

import android.app.DownloadManager.Request;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends ActionBarActivity {

	ListView videoList;
	ArrayList<String> videoArray = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		videoList = (ListView) findViewById(R.id.videoList);
		
		ArrayAdapter <String> videoAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, videoArray);
		videoList.setAdapter(videoAdapter);
		
		RequestQueue rq = Volley.newRequestQueue(this);
		
//		JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Me, null, null, null, null) {
//		};
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	
}