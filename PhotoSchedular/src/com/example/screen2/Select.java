
package com.example.screen2;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Select extends Activity
{
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    
     public void onCreate(Bundle savedInstanceState)
     {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_select);
            
            Button button = (Button) findViewById(R.id.button1);
            StrictMode.setThreadPolicy(policy); 
            
            button.setOnClickListener(new View.OnClickListener()
            {
            public void onClick(View view)
              {
                 String result = null;
                InputStream is = null;
                EditText editText = (EditText)findViewById(R.id.editText1);
                String v1 = editText.getText().toString();
                     EditText editText1 = (EditText)findViewById(R.id.editText2);

              

          ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("Username",v1));
                    try
                    {
                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httppost = new HttpPost("http://192.168.1.5/PhotoScheduler/select.php");
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

                    Toast.makeText(getApplicationContext(), " Input reading fail", Toast.LENGTH_SHORT).show();

                    }

                //parse json data
                try{
                           
                        JSONObject object = new JSONObject(result);
                        String ch=object.getString("re");
                        if(ch.equals("success"))
                        {
                           
                           JSONObject no = object.getJSONObject("0");
                            
                         //long q=object.getLong("f1");
                        String w= no.getString("User");
                      //  long e=no.getLong("Password");
                        
                        editText1.setText(w);
                    //   String myString = NumberFormat.getInstance().format(e);

                        
                    //    editText2.setText(myString);

                          }

                        
                        else
                        {                           
                            Toast.makeText(getApplicationContext(), "Record is not available.. Enter valid number", Toast.LENGTH_SHORT).show();
                        }
                    
                
                }
                catch(JSONException e)
                {
                        Log.e("log_tag", "Error parsing data "+e.toString());
                        Toast.makeText(getApplicationContext(), "JsonArray fail", Toast.LENGTH_SHORT).show();
                }


           }
           });                       
    
     }
    

}


