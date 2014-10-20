package com.example.hihi;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private FragmentTransaction fragmentTransaction;
	private Fragment frag;
	int orientation;
	ListView myListView1, myListView2;
	String [] categories1, titles1, articles1;
	TextView myTextView1;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if(getResources().getConfiguration().orientation ==
				Configuration.ORIENTATION_PORTRAIT) {
			fragmentTransaction = getFragmentManager().beginTransaction();
			frag = new Activity_One();
			fragmentTransaction.add(R.id.frameLayout, frag);
			fragmentTransaction.commit();
		} else if(getResources().getConfiguration().orientation ==
				Configuration.ORIENTATION_LANDSCAPE) {
			myListView1 = (ListView)findViewById(R.id.listView1);
			myListView2 = (ListView)findViewById(R.id.listView2);
			myTextView1 = (TextView)findViewById(R.id.textView1);
			myListView2.setVisibility(View.GONE);
			myTextView1.setVisibility(View.GONE);
			categories1 = getResources().getStringArray(R.array.categories);
			ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
	                android.R.layout.simple_list_item_1, categories1);
			myListView1.setAdapter(adapter1);			
			myListView1.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Connector.setCategory(position);
					if(position == 0) titles1 = getResources().getStringArray(R.array.titles0);
					else if(position == 1) titles1 = getResources().getStringArray(R.array.titles1);
					else if(position == 2) titles1 = getResources().getStringArray(R.array.titles2);
					ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getBaseContext(),
			                android.R.layout.simple_list_item_1, titles1);
					myListView2.setAdapter(adapter2);
					myListView2.setVisibility(View.VISIBLE);
				}
			});
			myListView2.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					if(Connector.getCategory() == 0) articles1 = getResources().getStringArray(R.array.titles0articles);
					else if(Connector.getCategory() == 1) articles1 = getResources().getStringArray(R.array.titles1articles);
					else if(Connector.getCategory() == 2) articles1 = getResources().getStringArray(R.array.titles2articles);
					myTextView1.setText(articles1[position]);
					myTextView1.setVisibility(View.VISIBLE);
				}
			});
		}
		
	}
	
	public void changeFragment(int id) {
			fragmentTransaction = getFragmentManager().beginTransaction();
			if(id == R.layout.listview_one) {
				frag = new Activity_Two();
			} else if (id == R.layout.listview_two) {
				frag = new Activity_Three();
			}
			fragmentTransaction.replace(R.id.frameLayout, frag);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
	}
}
