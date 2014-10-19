package com.example.artemlab;


import org.json.JSONException;
import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;

public class ThirdActivity extends Activity {

	private AQuery aq;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		aq = new AQuery(this);
        
    	String apiURL = "http://kivvi.kz/api/radio/info?stream=";
    	aq.ajax(apiURL, JSONObject.class, ThirdActivity.this, "apiCallback");
	}
	public void apiCallback(String url, JSONObject data, AjaxStatus status) {
    	Log.d("MC", "code " + status.getCode());
    	Log.d("MC", "message " + status.getMessage());
    	Log.d("MC", "error " + status.getError());
    	Log.d("MC", "result " + data);
    	try {
    		String title = data.getString("title");
    		String website = data.getString("website");
    		String description = data.getString("description");
    		Log.d("MC", "description: " + title);
    		Log.d("MC", "listeners: " + website);
    		Log.d("MC", "listeners: " + description);
    		aq.id(R.id.text).text(Html.fromHtml(title));
    		aq.id(R.id.text2).text(Html.fromHtml(website));
    		aq.id(R.id.text3).text(Html.fromHtml(description));
    	}
    	catch (JSONException e) {
    		e.printStackTrace();
    	}  		
    }
	    
}
