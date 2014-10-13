package com.example.myfirstapp;

import org.json.JSONException;
import org.json.JSONObject;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	private AQuery aq;	
	
	
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view, int position){
    	aq = new AQuery(this);
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	String apiURL = "http://kivvi.kz/api/radio/list?category=CATEGORY_KEYWORD" + message;
		aq.ajax(apiURL, JSONObject.class,MainActivity.class, "apiCallBack");
		intent.putExtra(EXTRA_MESSAGE, message);
    }
    
    public void apiCallBack(String url, JSONObject data, AjaxStatus status){
    	try{
    		String description = data.getString("description");
    		String listeners = data.getString("listeners");
    		Log.d("Kivvi.kz", "description: "+description);
    		Log.d("Kivvi.kz", "listeners: " + listeners);
    		aq.id(R.id.text).text(Html.fromHtml(description));
    	}catch(JSONException e){
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
