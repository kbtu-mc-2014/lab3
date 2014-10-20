package com.example.lab3;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;



public class MainActivity extends Activity implements Fragment1.OnHeadlineSelectedListener, Fragment2.SelectedListener{

	Fragment1 frag1 = new Fragment1();
	Fragment2 frag2 = new Fragment2();
	Fragment3 frag3 = new Fragment3();
	Bundle newBundle = new Bundle();
	
	int title = 0;
	int subtitle = 0;
	
	

	String values[] = {"hiphop", "rock", "hardcore", "trance"};
	
	
	AQuery aq, bq;
	
	public void onArticleSelected(int position) {
		// The user selected the headline of an article from the HeadlinesFragment
		// Do something here to display that article
		newBundle.putInt("title", position);
		Fragment2 newFragment = new Fragment2();
		
		Bundle args = new Bundle();
		args.putString("keyword", values[position]);
		aq = new AQuery(this);
		String apiURL = "http://kivvi.kz/api/radio/list?category=" + values[position];
		Log.d("URL", apiURL);
		titles = new ArrayList<>();
		ID = new ArrayList<>();
		
		aq.ajax(apiURL, String.class, MainActivity.this, "myCallback");
		
		args.putStringArrayList("titles", titles);
		args.putStringArrayList("ID", ID);
		Log.d("titles ", titles.size() + "");
		Log.d("ID ", ID.size() + "");
		
		
		

		newFragment.setArguments(args);

		FragmentTransaction transaction = getFragmentManager().beginTransaction();

		// Replace whatever is in the fragment_container view with this fragment,
		// and add the transaction to the back stack so the user can navigate back
		if(getResources().getConfiguration().orientation
				== Configuration.ORIENTATION_LANDSCAPE){
			transaction.replace(R.id.frgmCont2, newFragment);
			transaction.addToBackStack(null);
		}
		else {

			transaction.replace(R.id.frgmCont1, newFragment);
			transaction.addToBackStack(null);
		}

		// Commit the transaction
		transaction.commit();
	}
	ArrayList<String> titles;
	ArrayList<String> ID;
	
	public void myCallback(String url, String data, AjaxStatus status) {
		Log.d("error ", status.getError());
		Log.d("message ", status.getMessage());
		Log.d("redirect ", status.getRedirect());
		

		Log.d("MC ", "result " + data);
		try {
			JSONArray jArray = new JSONArray(data);
			for (int i=0; i<jArray.length(); i++)
			{
				
				titles.add(jArray.getJSONObject(i).getString("title"));
				ID.add(jArray.getJSONObject(i).getString("stream"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onArticleSelected2(int position, String stream) {
		// TODO Auto-generated method stub
		// The user selected the headline of an article from the HeadlinesFragment
		// Do something here to display that article
		Fragment3 newFragment = new Fragment3();
		Bundle args = new Bundle();
		args.putString("stream", stream);
		newFragment.setArguments(args);

		FragmentTransaction transaction = getFragmentManager().beginTransaction();

		// Replace whatever is in the fragment_container view with this fragment,
		// and add the transaction to the back stack so the user can navigate back
		if(getResources().getConfiguration().orientation
				== Configuration.ORIENTATION_LANDSCAPE){
			transaction.replace(R.id.frgmCont2, newFragment);
			transaction.addToBackStack(null);
		}
		else {
			transaction.replace(R.id.frgmCont1, newFragment);
			transaction.addToBackStack(null);
		}
		// Commit the transaction
		transaction.commit();

	}


	FragmentTransaction fTrans;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Fragment1 frag = new Fragment1();
		getFragmentManager().beginTransaction().replace(R.id.frgmCont1, frag).commit();

	}
}