package com.example.radiolist;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

public class SecondActivity extends Activity {

	private ListView lw;
	private AQuery aQuery;
	private String apiURL;
	private Intent i, i2;
	private String [] stream, str2;
	private String str;
	private int pos;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		i = getIntent();
		i2 = new Intent(this, ThirdActivity.class);
		pos = i.getIntExtra("pos", 0);
		if(pos == 0) stream = getResources().getStringArray(R.array.rock);
		else if(pos == 1) stream = getResources().getStringArray(R.array.techno);
		else if(pos == 2) stream = getResources().getStringArray(R.array.shanson);
		lw = (ListView) findViewById(R.id.listView2);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, stream);
		lw.setAdapter(adapter);
		aQuery = new AQuery(this);
		lw.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(pos == 0) str2 = getResources().getStringArray(R.array.rock2);
				else if(pos == 1) str2 = getResources().getStringArray(R.array.techno2);
				else if(pos == 2) str2 = getResources().getStringArray(R.array.shanson2);
				if(position == 0) str = str2[0];
				else if (position == 1) str = str2[1];
				else if (position == 2) str = str2[2];
				apiURL = "http://kivvi.kz/api/radio/info?stream="+str;
				aQuery.ajax(apiURL, JSONObject.class, SecondActivity.this,
						"apiCallback");
			}
		});
	}

	public void apiCallback(String url, JSONObject data, AjaxStatus status) {
		try {
			String description = data.getString("description");
			i2.putExtra("des", description);
			startActivity(i2);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
