package com.example.mobilecbissenbaylab3;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SecondActivity extends ActionBarActivity {

	private Intent i;
	private String[] cities;
	private TextView cityName, temp, humidity, pressure, windspeed;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		i = getIntent();
		cities = getResources().getStringArray(R.array.cities);
		cityName = (TextView) findViewById(R.id.cityName);
		cityName.setText(cities[i.getIntExtra("pos", 0)]);
		temp = (TextView) findViewById(R.id.Intemp);
		humidity = (TextView) findViewById(R.id.Inhumidity);
		pressure = (TextView) findViewById(R.id.Inpressure);
		windspeed = (TextView) findViewById(R.id.Inwindspeed);
		temp.setText(i.getStringExtra("temp") + " F");
		humidity.setText(i.getStringExtra("humidity") + " %");
		pressure.setText(i.getStringExtra("pressure") + " hPa");
		windspeed.setText(i.getStringExtra("speed") + " ì/ñ");
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
