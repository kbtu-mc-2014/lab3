package com.example.lab3;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Fragment1 extends ListFragment {


	
	OnHeadlineSelectedListener mCallback;

	// Container Activity must implement this interface
	public interface OnHeadlineSelectedListener {
		public void onArticleSelected(int position);
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception
		try {
			mCallback = (OnHeadlineSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// Send the event to the host activity
		super.onListItemClick(l, v, position, id);
		mCallback.onArticleSelected(position);
	}
	String values[] = {"hiphop", "rock", "hardcore", "trance"};

	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment1, null);

		return view;
	}
}