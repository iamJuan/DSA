package com.jga.dsa;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TableLayout;

public class DSATabulationActivity extends Activity{
	
	private DSATabulationView view;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.postponement_table);
		
		TableLayout layout = (TableLayout)findViewById(R.id.postponementMain);
		view = new DSATabulationView(this, layout);
		layout.addView(view, 0);
	}

}
