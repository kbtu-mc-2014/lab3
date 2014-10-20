package com.example.lab3;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

public class Fragment3 extends Fragment {


	TextView ET;
	String mydata;
	String getText(){
		return getArguments().getString("stream", "empty");
	}
	AQuery aq;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view=inflater.inflate(R.layout.fragment3, container,false);
        TextView text = (TextView) view.findViewById(R.id.text);
        aq = new AQuery(getActivity());
		String apiURL = "http://kivvi.kz/api/radio/info?stream=" + getText();
		aq.ajax(apiURL, JSONObject.class, this, "contCallback");
        text.setText(mydata);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        ET=(TextView) getActivity().findViewById(R.id.text);
    }
    public void contCallback(String url, JSONObject data, AjaxStatus status) {
    	try {
    		mydata = data.getString("description");
    	} catch (JSONException e) {
    		e.printStackTrace();
    	}
    }
}
