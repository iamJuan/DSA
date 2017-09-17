package com.jga.dsa;

import com.jga.dsa.postponement.Statics;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class DSAView extends View{

	private Button okBtn;
	private Context myContext;
	private RelativeLayout relativeLayout;
	
	public DSAView(Context context, RelativeLayout parentLayout) {
		super(context);
		myContext = context;
		
		relativeLayout = parentLayout;
		
		okBtn = (Button)relativeLayout.findViewById(R.id.okBtn);
		okBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent newIntent = new Intent(myContext, DSAProjectActivity.class);
				myContext.startActivity(newIntent);
			}
		});
	}
	
	@Override
	public void onSizeChanged(int w, int h, int oldw, int oldh){
		Statics.screenW = w;
		Statics.screenH = h;
	}
}
