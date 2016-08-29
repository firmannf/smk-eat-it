package com.eat.it;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class SplashActivity extends Activity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);	        
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
        	
        	public void run() {
        	// TODO Auto-generated method stub
        		startActivity(new Intent(getApplicationContext(), MainActivity.class));
        		finish();
        	}
        }, 2000);
    }
}