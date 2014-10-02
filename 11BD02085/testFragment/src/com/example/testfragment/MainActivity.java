package com.example.testfragment;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.example.testfragment.ActivityTwo;

public class MainActivity extends FragmentActivity {
	private TextView tw;
	private AQuery aq;
	private ListView lvMain;
	private ArrayList <Integer> array = new ArrayList<Integer>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lvMain = (ListView) findViewById(R.id.listView1);
		String[] names = { "item 1", "item 2", "item 3" };
		tw = (TextView) findViewById(R.id.textView1);
		String apiUrl = "http://codeforces.ru/api/contest.list?gym=false";
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, names);
		    
		    lvMain.setAdapter(adapter);
		    
		aq = new AQuery(this);
		aq.ajax(apiUrl, JSONObject.class, MainActivity.this, "apiCallback");
		
	    
	    lvMain.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View view,
	            int position, long id) {
	          Log.d("MC", "itemClick: position = " + position + ", id = "+id);
	          Intent intent = new Intent(getApplicationContext(), ActivityTwo.class);
	          int res = (int)id;
	          int res2 = array.get(res);
	          tw.setText(array.get(res)+"");
	          intent.putExtra("Answer", res2);
	          startActivity(intent);
	        }
	      });
	}
	
	public void apiCallback(String url, JSONObject data, AjaxStatus status){
		Log.d("MC", "code " + status.getCode());
		try{
			JSONArray result = data.getJSONArray("result");
			ArrayList<String> list = new ArrayList<String>();
			for (int i=0; i<10; i++){
				JSONObject ob = result.getJSONObject(i);
				String name = ob.getString("name");
				int id = ob.getInt("id");
				list.add(name);
				array.add(id);
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
