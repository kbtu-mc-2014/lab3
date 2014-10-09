package com.example.labwork3;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.example.labwork3.MainActivity;

import com.example.labwork3.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private AQuery aq;
	private Button submitButton;
	private EditText textEdit;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		aq = new AQuery(this);

        submitButton = (Button)findViewById(R.id.button1);
	    textEdit = (EditText)findViewById(R.id.editText1);
	        
        submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("MC", "button clicked");
		        String apiURL = "http://rutube.ru/api/metainfo/tv/28/video/?season=4&episode="+textEdit.getText()+"&type=2&format=json";
				Log.d("MC", "url " + apiURL);
		        aq.ajax(apiURL, JSONObject.class, MainActivity.this, "apiCallback");
			}
        });
	}
	
	public void apiCallback(String url, JSONObject data, AjaxStatus status) {
    	try {
    		JSONArray results = data.getJSONArray("results");
    		JSONObject description = results.getJSONObject(0);
    		String desc = description.getString("description");
    		Log.d("MC", "description: " + description);
    		aq.id(R.id.textView1).text(desc);
    	} catch (JSONException e) {
    		e.printStackTrace();
    	}
    }

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

