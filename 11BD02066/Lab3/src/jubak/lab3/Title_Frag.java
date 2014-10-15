package jubak.lab3;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Title_Frag extends ListFragment {

	public interface titleEventListener {
	    public void titleClickEvent(int pos);
	  }
	
	titleEventListener someListener;
	String titles[] = new String[] { "Week", "Month", "Year"};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {
		View v =  inflater.inflate(R.layout.title_frag, container, false);	
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	  super.onActivityCreated(savedInstanceState);
	  ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
	      android.R.layout.simple_list_item_1, titles);
	  setListAdapter(adapter);
	}
	
	@Override
	  public void onAttach(Activity activity) {
	    super.onAttach(activity);
        someListener = (titleEventListener) activity; 
    }
	  
	public void onListItemClick(ListView l, View v, int position, long id) {
	   super.onListItemClick(l, v, position, id);
	   someListener.titleClickEvent(position);	  
	}
	  
}
