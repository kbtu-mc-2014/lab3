package com.example.labwork3_second;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;


public class SecondActivity extends Activity {

	private AQuery aq;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		aq = new AQuery(this);
		String x = getIntent().getStringExtra("season");
		String apiURL = "http://rutube.ru/api/metainfo/tv/28/video/?season=4&episode="+x+"&type=2&format=json";
		//Log.d("MC", "url " + apiURL);
        aq.ajax(apiURL, JSONObject.class, SecondActivity.this, "apiCallback");
	}
	
	
	
	public void apiCallback(String url, JSONObject data, AjaxStatus status) {
    	try {
    		Log.d("MC", "url " + url);
    		JSONArray results = data.getJSONArray("results");
    		Log.d("MC", "url " + url);
    		JSONObject description = results.getJSONObject(0);
    		String desc = description.getString("description");
    		Log.d("MC", "description: " + description);
    		aq.id(R.id.textView1).text(desc);
    	} catch (JSONException e) {
    		e.printStackTrace();
    	}
    }
	
	
}
