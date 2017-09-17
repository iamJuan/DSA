package com.jga.dsa;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class DSAProjectActivity extends Activity{
	
	private DSAProjectView view;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.project_layout);
		
		RelativeLayout layout = (RelativeLayout)findViewById(R.id.projectLayout);
		view = new DSAProjectView(this, layout);
		layout.addView(view, 0);
	}
}
