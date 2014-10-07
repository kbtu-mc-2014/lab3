package com.example.mobilecbissenbaylab3;

import org.json.JSONException;
import org.json.JSONObject;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ActionBarActivity {

	private String[] cities, cityId;
	private String temp, humidity, pressure, windspeed, apiURL;
	private ListView myListView;
	private AQuery aQuery;
	private TextView textView;
	private Intent i;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		i = new Intent(this, SecondActivity.class);
		cities = getResources().getStringArray(R.array.cities);
		cityId = getResources().getStringArray(R.array.id);
		myListView = (ListView) findViewById(R.id.listView1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, cities);
		myListView.setAdapter(adapter);
		aQuery = new AQuery(this);

		myListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				apiURL = "http://api.openweathermap.org/data/2.5/weather?q="
						+ cities[position] + "";
				aQuery.ajax(apiURL, JSONObject.class, MainActivity.this,
						"apiCallback");
				i.putExtra("pos", position);
			}
		});
	}

	public void apiCallback(String url, JSONObject data, AjaxStatus status) {
		try {
			JSONObject main = data.getJSONObject("main");
			JSONObject wind = data.getJSONObject("wind");
			windspeed = wind.getString("speed").toString();
			i.putExtra("pressure", main.getString("pressure").toString());
			i.putExtra("humidity", main.getString("humidity").toString());
			i.putExtra("temp", main.getString("temp").toString());
			i.putExtra("speed", wind.getString("speed").toString());
			startActivity(i);

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
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
