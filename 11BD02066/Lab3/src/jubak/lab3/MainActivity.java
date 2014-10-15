package jubak.lab3;

import jubak.lab3.Sub_Frag.subEventListener;
import jubak.lab3.Title_Frag.titleEventListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity implements titleEventListener, subEventListener{

		Cont_Frag cont_frag = new Cont_Frag();
		Sub_Frag sub_frag = new Sub_Frag();
		Title_Frag title_frag = new Title_Frag();
		int titlePos = 0;
		String subTitlePos = null;
		boolean withContent = true;
 
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.main);
		  Title_Frag title = new Title_Frag();
		  getFragmentManager().beginTransaction().replace(R.id.frameTS, title).commit();

		  if (savedInstanceState!= null) subTitlePos = savedInstanceState.getString("subTitlePos");
		  withContent = (findViewById(R.id.frameC) != null);
		  if (withContent) showContent(subTitlePos);
		}
		
		void showContent(String pos) {
			if (withContent) {
			    Cont_Frag content = (Cont_Frag) getFragmentManager().findFragmentById(R.id.frameC);
			    Title_Frag title = new Title_Frag();
		        if (content == null || content.getPosition() != pos) {
		          content = Cont_Frag.newInstance(pos);
		          getFragmentManager().beginTransaction().replace(R.id.frameTS, title).commit();
		          getFragmentManager().beginTransaction().replace(R.id.frameC, content).commit();
		        }
	        } else {
	            startActivity(new Intent(this, Cont_Activity.class).putExtra("position", subTitlePos));      
	        }
		}
		
		void showSubTitle(int pos) {
			Sub_Frag subtitles = Sub_Frag.newInstance(pos);
			getFragmentManager().beginTransaction().replace(R.id.frameTS, subtitles).commit();
		}
		
		@Override
		public void titleClickEvent(int pos) {
			this.titlePos = pos;
			showSubTitle(pos);
			Log.e("TITLE CLICKED", "position = "+ pos);
		}
		
		protected void onSaveInstanceState(Bundle outState) {
		    super.onSaveInstanceState(outState);
		    outState.putInt("titlePos", titlePos);
		    outState.putString("subTitlePos", subTitlePos);
		}

		@Override
		public void subClickEvent(String pos) {
			this.subTitlePos = pos;
			showContent(pos);		
		}
}
