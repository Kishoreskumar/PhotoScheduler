package com.example.screen2;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;

import android.view.Window;


public class MainActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		    builder.setMessage("Scheduling Photo Shoot for professionals").create();
		    builder.setPositiveButton("Signup", new DialogInterface.OnClickListener() {

		    	@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent nextScreen = new Intent(getApplicationContext(), Signup.class);					  
	                startActivity(nextScreen);						
				}
			});
		    
 builder.setNegativeButton("Login", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent nextScreen = new Intent(getApplicationContext(), Location.class);					  
	                startActivity(nextScreen);						
				}
			});
		    
        
            builder.setTitle("Photo Shoot Schedular");            
            builder.show();
           
            Log.d("finish","finish");
	}
		
}
