package com.jga.dsa;

import com.jga.dsa.postponement.Evaluation;

import android.content.Context;
import android.view.View;
import android.widget.TableLayout;

public class DSAEvaluationView extends View{

	private Context myContext;
	private TableLayout layout;
	
	private Evaluation evaluation;
	
	public DSAEvaluationView(Context context, TableLayout tlayout) {
		super(context);
		
		myContext = context;
		layout = tlayout;
		
		evaluation = new Evaluation(myContext, layout);
		evaluation.startEvaluation();
	}

}
