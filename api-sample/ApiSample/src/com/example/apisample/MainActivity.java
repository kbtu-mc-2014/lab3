package com.example.apisample;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;


public class MainActivity extends ActionBarActivity {
	private Button submitButton;
	private EditText textEdit;
	private AQuery aq;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        aq = new AQuery(this);

        submitButton = (Button)findViewById(R.id.submit);
	        textEdit = (EditText)findViewById(R.id.stream);
	        
        submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("MC", "button clicked");
		        String apiURL = "http://kivvi.kz/api/radio/info?stream=" + textEdit.getText();
				Log.d("MC", "url " + apiURL);
		        aq.ajax(apiURL, JSONObject.class, MainActivity.this, "apiCallback");
			}
        });
    }

    public void apiCallback(String url, JSONObject data, AjaxStatus status) {
    	Log.d("MC", "code " + status.getCode());
    	Log.d("MC", "message " + status.getMessage());
    	Log.d("MC", "error " + status.getError());
    	Log.d("MC", "result " + data);
    	try {
    		String description = data.getString("description");
    		int listeners = data.getInt("listeners");
    		Log.d("MC", "description: " + description);
    		Log.d("MC", "listeners: " + listeners);
    		aq.id(R.id.text).text(Html.fromHtml(description));
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
