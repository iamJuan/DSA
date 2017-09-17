package com.jga.dsa;

import java.util.ArrayList;

import com.jga.dsa.postponement.Statics;
import com.jga.dsa.utilities.MathEquation;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class DSAProjectView extends View{
	
	private Context myContext;
	private RelativeLayout layout;
	
	private EditText inputText;
	
	private String userInput;

	public DSAProjectView(Context context, RelativeLayout parentLayout) {
		super(context);
		
		myContext = context;
		layout = parentLayout;
		
		inputText = (EditText)layout.findViewById(R.id.inputEditText);

		this.setOnTouchListener(new OnTouchListener() {
			float touchDown = 0;
			float touchUp = 0;
			@Override
			public boolean onTouch(View v, MotionEvent me) {
				
				int action = MotionEventCompat.getActionMasked(me);
				
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					
					touchDown = me.getX();
					
					return true;
					
				case MotionEvent.ACTION_UP:
					
					touchUp = me.getX();
					
					if(touchUp < touchDown){ // go right
						goNext();
					}else{
						Toast.makeText(myContext, "Proceed to Tabulation Method by swiping left", Toast.LENGTH_SHORT).show();
					}
					
					return true;

				default:
					return false;
				}
				
			}
		});
	}
	
	public void goNext(){
		try{
			userInput = inputText.getText().toString();					
		}catch(NullPointerException npe){}

		ArrayList<String> inputList = new ArrayList<String>();
		boolean isNowNumber = false;
		
		for (int i = 0, j = 0; i < userInput.length(); i++) {
			
			if(userInput.charAt(i) == '('|| userInput.charAt(i) == ')'|| 
					userInput.charAt(i) == '+' || userInput.charAt(i) == '-' || userInput.charAt(i) == '/' 
					|| userInput.charAt(i) == '*' || userInput.charAt(i) == '^'){
				
				inputList.add(""+userInput.charAt(i));
				j++;
				isNowNumber = false;
			}else{
				if(!isNowNumber){
					inputList.add(""+userInput.charAt(i));
					j++;
					isNowNumber = true;
				}else if(isNowNumber){
					inputList.set(j-1, inputList.get(j-1)+userInput.charAt(i));
				}
			}			
		}
		
		if(!userInput.equals("") && inputList.size() <= 15){
			int mathError = 0;
			mathError = MathEquation.equationCheckForError(inputList);
			
			if(mathError == MathEquation.NO_ERRORS){
				Statics.stack = inputList;
			
				Intent newIntent = new Intent(myContext, DSATabulationActivity.class);
				myContext.startActivity(newIntent);
				
			}else if(mathError == MathEquation.ERROR_INVALID_CHARACTERS){
				Toast.makeText(myContext, "Input contents should be numerical only.", Toast.LENGTH_SHORT).show();
			}else if(mathError == MathEquation.ERROR_INCOMPLETE_PARENTHESIS){
				Toast.makeText(myContext, "Please make sure that parenthesis are correct.", Toast.LENGTH_SHORT).show();
			}else if(mathError == MathEquation.ERROR_INVALID_EQUATION){
				Toast.makeText(myContext, "Invalid arithmetic equation.", Toast.LENGTH_SHORT).show();
			}
			
		}else{
			if(userInput.equals("")){
				Toast.makeText(myContext, "Input is required.", Toast.LENGTH_SHORT).show();
			}else if(inputList.size() > 15){
				Toast.makeText(myContext, "Input is too long. \nMAX is 15, sum of total operators and operands.", Toast.LENGTH_SHORT).show();
			}
		}
	}
}
