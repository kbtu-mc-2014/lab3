package jubak.lab3;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

public class Sub_Frag extends ListFragment {
	
	 public static Sub_Frag newInstance(int pos) {
		    Sub_Frag subs = new Sub_Frag();
		    Bundle args = new Bundle();
		    args.putInt("position", pos);
		    subs.setArguments(args);
		    return subs;
	 }

	 int getPosition() {
		 return getArguments().getInt("position", 0);
	 }

	public interface subEventListener {
	    public void subClickEvent(String pos);
	  }
	
	subEventListener someListener;
	String subtitles[] = {"", "", "", "", "","", "", "", "", "" };
	String subID[] = {"", "", "", "", "","", "", "", "", "" };
	private AQuery aq;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.sub_frag, container, false);
		 TextView tv = (TextView) v.findViewById(R.id.tvSub);
		 tv.setText("Clicked Title "+getPosition());
		 
			String x;
			if (getPosition()==0) x="week";
				else if (getPosition()==1) x="month";
					else x="year";
			
			aq = new AQuery(v);
			
			
			
			String apiURL = "http://rutube.ru/api/search/video/?query=%3Ccreated="+x+"%3E&format=json";
			aq.ajax(apiURL, JSONObject.class, this, "subCallback");
			
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	  super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	  public void onAttach(Activity activity) {
	    super.onAttach(activity);
	    someListener = (subEventListener) activity;
	  }
	
	public void onListItemClick(ListView l, View v, int position, long id) {
	   super.onListItemClick(l, v, position, id);
	   someListener.subClickEvent(subID[position]);
	}
	
	   public void subCallback(String url, JSONObject data, AjaxStatus status) {
	    	try {
	    		JSONArray jArray;
	    		jArray = data.getJSONArray("results");
	    		for (int i=0; i<10; i++){
	    			subtitles[i]=jArray.getJSONObject(i).getString("title");
	    			subID[i]=jArray.getJSONObject(i).getString("id");
	    		}
	    		
	    		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
    		    		android.R.layout.simple_list_item_1, subtitles);
		    	setListAdapter(adapter);
	    			  
	    	} catch (JSONException e) {
	    		e.printStackTrace();
	    	}
	    }
	
}
