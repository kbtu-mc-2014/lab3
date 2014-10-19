package com.example.artemlab;

import java.util.ArrayList;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FourthActivity extends Activity {

	private AQuery aq;
	ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fourth);
		aq = new AQuery(this);
        lv = (ListView)findViewById(R.id.listView3);
        
    	String apiURL = "http://api.openweathermap.org/data/2.5/find?lat=55.5&lon=37.5&cnt=10";
    	aq.ajax(apiURL, JSONObject.class, FourthActivity.this, "apiCallback");
	}
	
	public void apiCallback(String url, JSONObject data, AjaxStatus status) {
    	Log.d("MC", "code " + status.getCode());
    	Log.d("MC", "message " + status.getMessage());
    	Log.d("MC", "error " + status.getError());
    	Log.d("MC", "result " + data);
    	try {
    		JSONArray array1 = data.getJSONArray("list");
    		ArrayList<String> list = new ArrayList<String>();
    		for (int i = 0; i < array1.length(); i++) {
    			JSONObject obj1 = array1.getJSONObject(i);	         
                String name = obj1.getString("name");                		
                list.add(name);                
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
           				android.R.layout.simple_list_item_1, list);
                lv.setAdapter(adapter);
                Log.d("MC", "dt: " + name);       	
    		}
    	}
    	catch (JSONException e) {
    		e.printStackTrace();
    	}  		
    }    

	
}
