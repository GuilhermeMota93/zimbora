package com.example.itlog.activities;

import org.apache.commons.logging.Log;

import com.example.itlog.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity{
	//tempo de "splash"
	private static int SPLASH_TIME_OUT = 1000;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
 
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Come�a quando timer termina; lan�a actividade principal
                Intent i = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(i);
 
                // fechar actividade
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
