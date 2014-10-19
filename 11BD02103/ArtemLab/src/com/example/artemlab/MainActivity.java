package com.example.artemlab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	ListView listv;
	private String [] choose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        choose = getResources().getStringArray(R.array.choose);
        listv = (ListView)findViewById(R.id.listView1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, choose);
		listv.setAdapter(adapter);
      
		listv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				if (position==0) {
					Intent i = new Intent (MainActivity.this, SecondActivity.class);
					startActivity(i);
				}
				if (position==1) {
					Intent i = new Intent (MainActivity.this, ThirdActivity.class);
					startActivity(i);
				}
				if (position==2) {
					Intent i = new Intent (MainActivity.this, FourthActivity.class);
					startActivity(i);
				}
			}
		});
    }
}