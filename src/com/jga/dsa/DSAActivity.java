package com.jga.dsa;

import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class DSAActivity extends Activity{
	
	private DSAView view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.intro_layout);

		RelativeLayout layout = (RelativeLayout) findViewById(R.id.introLayout);
		
		view = new DSAView(this, layout);
		layout.addView(view, 0);
	}
}
