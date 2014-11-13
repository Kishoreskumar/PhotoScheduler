package com.example.screen2;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;


public class Location extends Activity {
	public static String stat="~~state~~";
	//StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	
	 AutoCompleteTextView lv;
	 ArrayAdapter<String> adapter;
	 ArrayList<String> a =new ArrayList<String>();
	 
	 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        	//StrictMode.setThreadPolicy(policy); 
        setContentView(R.layout.activity_location);
   	    final TextView state;
     	state = (TextView) findViewById(R.id.textView5);
   	 
   	 
        String k="blank";
        String k1="http://192.168.1.7/PhotoScheduler/city.php";
        String query="city";
        prot(k, k1,query);
        lv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,  a);
        lv.setAdapter(adapter);
       
      
        
        lv.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			//	Toast.makeText(getApplicationContext(), "onChanged", Toast.LENGTH_LONG).show();
				 String k=lv.getText().toString();
			        String k1="http://192.168.1.7/PhotoScheduler/selectstate.php";
			        String query="state";
			        prot(k, k1,query);
			
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			//	Toast.makeText(getApplicationContext(), "b4", Toast.LENGTH_LONG).show();
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				//Toast.makeText(getApplicationContext(), "After Text changed", Toast.LENGTH_LONG).show();
				lv.clearFocus();
			}
		});
       
  lv.setOnItemClickListener(new OnItemClickListener() {
        	
        	public void onItemClick(AdapterView<?> arg0, View arg1,
	                int arg2, long arg3){
        		Toast.makeText(getApplicationContext(), "Item picked", Toast.LENGTH_LONG).show();
        		String g=stat;
        		Log.d("g", g);
        	//	state.setText(g.toString());
        		state.setText("fa");
        		//state.setText(stat);
        		

	        }
		});
 
    }
           
   
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.location, menu);
        return true;
    }
    
    
    private void prot(String k,String k1,String k2) {
        	
    	   InputStream is = null;
    	     String result = null;
    	     String v1=k;
    	 ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
         nameValuePairs.add(new BasicNameValuePair("Username",v1));
         try 
         {
             HttpClient httpclient = new DefaultHttpClient();
             HttpPost httppost = new HttpPost(k1);
             httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
             HttpResponse response = httpclient.execute(httppost); 
             HttpEntity entity = response.getEntity();
             is = entity.getContent();
             Log.e("log_tag", "connection success ");
         }
     catch(Exception e)      {                     
     	Log.e("log_tag", "Error in http connection "+e.toString());
         Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();
         }
     //convert response to string
         try{
             BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"),8);
             StringBuilder sb = new StringBuilder();
             String line = null;
             while ((line = reader.readLine()) != null) 
             {
                     sb.append(line + "\n");
             }
             is.close();
             result=sb.toString();
             Log.i("Result",result);
         }
         catch(Exception e)
         {
            Log.e("log_tag", "Error converting result "+e.toString());
         }
     //parse json data
     try{
                
             JSONObject object = new JSONObject(result);
             String ch=object.getString("re");
             Log.i("Ch",ch);
             if(ch.equals("success"))
             {
                JSONObject no;
                int i=0;
                while((no=object.getJSONObject(""+i))!=null){
                	String f=no.getString(k2);
                	a.add(f);
                	if(k2.equals("state")){
                		stat=f;
                		Log.e("f", f);
                	}
                	i++;
                }
               }
             else
             {                           
                 Toast.makeText(getApplicationContext(), "failed while fetching", Toast.LENGTH_LONG).show();
             }
     }
     catch(JSONException e)
     {
             Log.e("log_tag", "Error parsing data "+e.toString());
             stat=a.get(0);
             Log.i("stat",stat);
             
     }
	}
    
}
