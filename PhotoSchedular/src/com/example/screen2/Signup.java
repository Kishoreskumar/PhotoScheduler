package com.example.screen2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class Signup extends Activity  {
	 
	 StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	
     public void onCreate(Bundle savedInstanceState) 
     {
    	 requestWindowFeature(Window.FEATURE_NO_TITLE);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);
           
            StrictMode.setThreadPolicy(policy); 
            Button Signup = (Button) findViewById(R.id.button1);
            
          Signup.setOnClickListener(new View.OnClickListener()
          {
			
			@Override
			public void onClick(View v) {
				String result=null;
				InputStream is = null;
				
				   EditText editText = (EditText)findViewById(R.id.editText1);
                   String v1 = editText.getText().toString();
                   EditText editText1 = (EditText)findViewById(R.id.editText2);
                   String v2 = editText1.getText().toString();
                   EditText editText2 = (EditText)findViewById(R.id.editText3);
                   String v3 = editText2.getText().toString(); 
                   EditText editText3 = (EditText)findViewById(R.id.editText4);
                   String v4 = editText3.getText().toString();
                   EditText editText4 = (EditText)findViewById(R.id.editText5);
                   String v5 = editText4.getText().toString();
                  
                   ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                   
                   nameValuePairs.add(new BasicNameValuePair("f1",v1));
                   nameValuePairs.add(new BasicNameValuePair("f2",v2));
                   nameValuePairs.add(new BasicNameValuePair("f3",v3));
                   nameValuePairs.add(new BasicNameValuePair("f4",v4));
                   nameValuePairs.add(new BasicNameValuePair("f5",v5));

                   
                   StrictMode.setThreadPolicy(policy); 
                   StrictMode.setThreadPolicy(policy); 
				
                   try{
                       HttpClient httpclient = new DefaultHttpClient();
                       HttpPost httppost = new HttpPost("http://192.168.1.5/PhotoScheduler/create_user.php");
                       httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                       HttpResponse response = httpclient.execute(httppost); 
                       HttpEntity entity = response.getEntity();
                       is = entity.getContent();

                       Log.e("log_tag", "connection success ");
     Toast.makeText(getApplicationContext(), "pass", Toast.LENGTH_SHORT).show();
                  }
               
               
               catch(Exception e)
               {
                       Log.e("log_tag", "Error in http connection "+e.toString());
 Toast.makeText(getApplicationContext(), "Connection fail",Toast.LENGTH_SHORT).show();

               }
                   try{
                       BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
                       StringBuilder sb = new StringBuilder();
                       String line = null;
                       while ((line = reader.readLine()) != null) 
                       {
                               sb.append(line + "\n");
                                  Intent i = new Intent(getBaseContext(),Login.class);
                               startActivity(i);
                       }
                       is.close();

                       result=sb.toString();
               }
               catch(Exception e)
               {
                      Log.e("log_tag", "Error converting result "+e.toString());
                  }

                   try{
                       
                       JSONObject json_data = new JSONObject(result);

                       CharSequence w= (CharSequence) json_data.get("re");
                      
                     
                       //Toast.makeText(getApplicationContext(), w, Toast.LENGTH_SHORT).show();
                       Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();

             
            }
       catch(JSONException e)
          {
               Log.e("log_tag", "Error parsing data "+e.toString());
               //Toast.makeText(getApplicationContext(), "JsonArray fail", Toast.LENGTH_SHORT).show();
           }
                   
			}
		});
	
}}