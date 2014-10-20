package com.example.hihi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


public class Activity_Two extends Fragment {
	private ListView listTitles;
	private String [] titles;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.listview_two, null);
		listTitles = (ListView)view.findViewById(R.id.listView2);
		if(Connector.getCategory() == 0) titles = getResources().getStringArray(R.array.titles0);
		else if(Connector.getCategory() == 1) titles = getResources().getStringArray(R.array.titles1);
		else if(Connector.getCategory() == 2) titles = getResources().getStringArray(R.array.titles2);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_list_item_1, titles);
		listTitles.setAdapter(adapter);
		listTitles.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {	
				Connector.setTitle(position);
				((MainActivity)getActivity()).changeFragment(R.layout.listview_two);
			}
		});
		return view;
	}
}
