package com.fragmentexample.lab212;

        import org.json.JSONException;
        import org.json.JSONObject;

        import android.app.Activity;
        import android.app.FragmentTransaction;
        import android.content.res.Configuration;
        import android.os.Bundle;
        import android.text.Html;
        import android.util.Log;
        import android.view.View;
        import android.widget.ListView;
        import android.widget.ArrayAdapter;
        import com.androidquery.AQuery;
        import com.androidquery.callback.AjaxStatus;

public class MainActivity extends Activity {

    Fragment1 frag1;
    Fragment2 frag2;
    Fragment3 frag3;
    FragmentTransaction fTrans;
    public AQuery aq;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fTrans = getFragmentManager().beginTransaction();
        frag1 = new Fragment1();
        frag2 = new Fragment2();
        frag3 = new Fragment3();
        fTrans.replace(R.id.frgmCont,frag3);
        fTrans.commit();
        aq = new AQuery(this);
    }

    public void onClick(View v) {
       fTrans = getFragmentManager().beginTransaction();
        switch (v.getId()) {

            case R.id.button1:
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    fTrans.replace(R.id.frgmCont2, frag2);
                }else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                    fTrans.replace(R.id.frgmCont, frag2);}
                break;
            case R.id.button:
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    fTrans.replace(R.id.frgmCont2,frag1);
                    String apiURL = "http://kivvi.kz/api/radio/info?stream=europaplus";
                    aq.ajax(apiURL, JSONObject.class, MainActivity.this, "apiCallback");
                }else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                    fTrans.replace(R.id.frgmCont, frag1);
                    String apiURL = "http://kivvi.kz/api/radio/info?stream=europaplus";
                    aq.ajax(apiURL, JSONObject.class, MainActivity.this, "apiCallback");
                }
                break;
            case R.id.button2:
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    fTrans.replace(R.id.frgmCont2,frag1);
                    String apiURL = "http://kivvi.kz/api/radio/info?stream=retrofm";
                    aq.ajax(apiURL, JSONObject.class, MainActivity.this, "apiCallback");
                }else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    fTrans.replace(R.id.frgmCont, frag1);
                    String apiURL = "http://kivvi.kz/api/radio/info?stream=retrofm";
                    aq.ajax(apiURL, JSONObject.class, MainActivity.this, "apiCallback");
                }
                break;
            case R.id.button3:
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    fTrans.replace(R.id.frgmCont2,frag1);
                    String apiURL = "http://kivvi.kz/api/radio/info?stream=radions";
                    aq.ajax(apiURL, JSONObject.class, MainActivity.this, "apiCallback");
                }else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                    fTrans.replace(R.id.frgmCont, frag1);
                    String apiURL = "http://kivvi.kz/api/radio/info?stream=radions";
                    aq.ajax(apiURL, JSONObject.class, MainActivity.this, "apiCallback");
                }
                break;
            case R.id.button4:
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    fTrans.replace(R.id.frgmCont2,frag1);
                    String apiURL = "http://kivvi.kz/api/radio/info?stream=tengrifm";
                    aq.ajax(apiURL, JSONObject.class, MainActivity.this, "apiCallback");
                }else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    fTrans.replace(R.id.frgmCont, frag1);
                    String apiURL = "http://kivvi.kz/api/radio/info?stream=tengrifm";
                    aq.ajax(apiURL, JSONObject.class, MainActivity.this, "apiCallback");
                }
                break;
            default:
                break;
        }
        fTrans.addToBackStack(null);
        fTrans.commit();
    }
    public void apiCallback(String url, JSONObject data, AjaxStatus status) {
    Log.d("MC", "code " + status.getCode());
    Log.d("MC", "message " + status.getMessage());
    Log.d("MC", "error " + status.getError());
    Log.d("MC", "result " + data);
    try {
        String description = data.getString("description");
        int listeners = data.getInt("listeners");
        Log.d("MC", "description: " + description);
        Log.d("MC", "listeners: " + listeners);
        aq.id(R.id.textView).text(Html.fromHtml(description));
    } catch (JSONException e) {
        e.printStackTrace();
    }
}
}