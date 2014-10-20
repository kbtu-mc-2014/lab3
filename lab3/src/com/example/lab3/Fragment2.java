package com.example.lab3;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

public class Fragment2 extends ListFragment {

	public FragmentTransaction fragTrans;

	SelectedListener mCallback;


	// Container Activity must implement this interface



	public interface SelectedListener {
		public void onArticleSelected2(int position, String id);
	}
	String values[];

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		titles = getArguments().getStringArrayList("titles");
		ID = getArguments().getStringArrayList("ID");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, titles);
		Log.d("aa", "" + titles.size());
		setListAdapter(adapter);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception
		try {
			mCallback = (SelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement SelectedListener");
		}


		/*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, titles);
		setListAdapter(adapter);*/
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// Send the event to the host activity
		super.onListItemClick(l, v, position, id);
		mCallback.onArticleSelected2(position, ID.get(position));
	}


	private AQuery aq;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment2, null);

		return v;
	}
	ArrayList<String> titles = new ArrayList<String>();
	ArrayList<String> ID = new ArrayList<String>();
	
}
