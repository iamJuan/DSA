package com.jga.dsa;

import com.jga.dsa.postponement.Tabulation;

import android.content.Context;
import android.view.View;
import android.widget.TableLayout;

public class DSATabulationView extends View{

	private TableLayout theLayout;
	private Context myContext;
	private Tabulation tabulation;
	
	public DSATabulationView(Context context, TableLayout layout) {
		super(context);
		myContext = context;
		theLayout = layout;
		
		tabulation = new Tabulation(myContext, theLayout);
		tabulation.createStacks();
	}

}
