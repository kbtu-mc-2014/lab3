package com.example.radiolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class ThirdActivity extends Activity {
	
	private Intent i;
	private TextView txt;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		i = getIntent();
		txt = (TextView)findViewById(R.id.textView1);
		txt.setText(Html.fromHtml(i.getStringExtra("des")));
	}
}
