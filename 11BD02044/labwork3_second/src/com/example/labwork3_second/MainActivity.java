package com.example.labwork3_second;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.androidquery.callback.AjaxStatus;

public class MainActivity extends Activity implements OnClickListener{


	private Button submitButton1;
	private Button submitButton2;
	private Button submitButton3;
	private Button submitButton4;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

        submitButton1 = (Button)findViewById(R.id.button1);
        submitButton2 = (Button)findViewById(R.id.button2);
        submitButton3 = (Button)findViewById(R.id.button3);
        submitButton4 = (Button)findViewById(R.id.button4);
	    
	        
      
        
        submitButton1.setOnClickListener(this);
        submitButton2.setOnClickListener(this);
        submitButton3.setOnClickListener(this);
        submitButton4.setOnClickListener(this);
       
	}
	
	@Override
	public void onClick(View v) {

		Intent intent = new Intent(MainActivity.this, SecondActivity.class);
		intent.putExtra("season", ((Button)v).getText());
		startActivity(intent);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
}
