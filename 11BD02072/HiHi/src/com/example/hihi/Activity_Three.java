package com.example.hihi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Activity_Three extends Fragment {
	private TextView myTextView;
	private String [] articles;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.textview_three, null);
		myTextView = (TextView)view.findViewById(R.id.textView1);
		if(Connector.getCategory() == 0) articles = getResources().getStringArray(R.array.titles0articles);
		else if(Connector.getCategory() == 1) articles = getResources().getStringArray(R.array.titles1articles);
		else if(Connector.getCategory() == 2) articles = getResources().getStringArray(R.array.titles2articles);
		myTextView.setText(articles[Connector.getTitle()]);
		return view;
	}
}
