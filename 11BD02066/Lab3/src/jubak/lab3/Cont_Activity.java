package jubak.lab3;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class Cont_Activity extends FragmentActivity {

	
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	        if (getResources().getConfiguration().orientation
	                == Configuration.ORIENTATION_LANDSCAPE || isLarge()) {
	            finish();
	            return;
	        }
	    
	        if (savedInstanceState == null) {
	            Cont_Frag content = Cont_Frag.newInstance(getIntent().getStringExtra("position"));
	            getFragmentManager().beginTransaction().add(android.R.id.content, content).commit();
	        }
	  }
	  
	  boolean isLarge() {
	    return (getResources().getConfiguration().screenLayout 
	        & Configuration.SCREENLAYOUT_SIZE_MASK) 
	        >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	  }
}
