package com.jga.dsa;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TableLayout;

public class DSAEvaluationActivity extends Activity{
	
	private DSAEvaluationView view;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.evaluation_table);
		
		TableLayout layout = (TableLayout)findViewById(R.id.evaluationMain);
		view = new DSAEvaluationView(this, layout);
		layout.addView(view, 0);
	}

}
