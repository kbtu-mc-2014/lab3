package com.example.testfragment;

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
import android.widget.TextView;
public class ActivityTwo extends Activity {
	private TextView tw1;
	private ListView lvMain;
	private AQuery aq;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		tw1 = (TextView) findViewById(R.id.textView1);
		//Intent intent = new Intent(this,MainActivity.class);
		int n = getIntent().getIntExtra("Answer",-1);
		//tw1.setText(n+"");
		String apiUrl = "http://codeforces.ru/api/contest.standings?contestId="+n+"&from=1&count=5&showUnofficial=true";
		aq = new AQuery(this);
		aq.ajax(apiUrl, JSONObject.class, ActivityTwo.this, "apiCallback");
		
		
	}
	public void apiCallback(String url, JSONObject data, AjaxStatus status){
		Log.d("MC", "code " + status.getCode());
		try{
			
			JSONObject result = data.getJSONObject("result");
			ArrayList <String> list = new ArrayList<String>();
			list.add("Rank  Handle  Points");
			JSONArray rows = result.getJSONArray("rows");
			for (int i=0; i<5; i++){
				rows.getJSONObject(i);
				JSONObject row = rows.getJSONObject(i);
				JSONObject party = row.getJSONObject("party");
				JSONArray members = party.getJSONArray("members");
				JSONObject member = members.getJSONObject(0);
				String name = member.getString("handle");
				int rank = row.getInt("rank");
				int points = row.getInt("points");
				Log.d("MC", "name: " + name);
				list.add(rank+"  "+name+"  "+points);
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			        android.R.layout.simple_list_item_1, list);
			lvMain = (ListView) findViewById(R.id.listView1);
			lvMain.setAdapter(adapter);
		}catch(JSONException e){
			e.printStackTrace();
		}
	}
}
