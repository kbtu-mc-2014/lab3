package com.example.testfragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// Context context = getActivity().getApplicationContext();
		// LinearLayout layout = new LinearLayout(context);
		// layout.setBackgroundColor(Color.BLUE);
		// TextView text = new TextView(context);
		// text.setText("��� ������� ���������");
		// layout.addView(text);
		//
		// return layout;

		View myFragmentView = inflater.inflate(R.layout.fragmentlayout,
				container, false);

		return myFragmentView;
	}
}