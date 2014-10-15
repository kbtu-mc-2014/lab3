package ilzhan.lab3mc;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

public class Cont_Frag extends Fragment {
	
	 public static Cont_Frag newInstance(String pos) {
		    Cont_Frag cont = new Cont_Frag();
		    Bundle args = new Bundle();
		    args.putString("position", pos);
		    cont.setArguments(args);
		    return cont;
	 }

		private AQuery aq;
		
	 String getPosition() {
		 return getArguments().getString("position", "");
	 }

	 @Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	     Bundle savedInstanceState) {
		 View v = inflater.inflate(R.layout.cont_frag, container, false);
		 
			aq = new AQuery(v);
			
			String apiURL = "http://rutube.ru/api/video/"+getPosition()+"?format=json";
			aq.ajax(apiURL, JSONObject.class, this, "contCallback");
			
			
		 return v;
	 }
	 
	 public void contCallback(String url, JSONObject data, AjaxStatus status) {
	    	try {

	    		aq.id(R.id.ivContent).image(data.getString("thumbnail_url"));
	    		aq.id(R.id.tvContent).text(data.getString("title"));
	    		
	    	} catch (JSONException e) {
	    		e.printStackTrace();
	    	}
	    }
	

}
