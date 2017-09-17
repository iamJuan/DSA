package com.jga.dsa.postponement;

import java.util.ArrayList;

import com.jga.dsa.DSAEvaluationActivity;
import com.jga.dsa.utilities.CharInquiry;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Tabulation {
	
	private ArrayList<String> stack;
	private ArrayList<String> symbolStack;
	private ArrayList<String> operatorStack;
	private ArrayList<String> postfixStack;
	private ArrayList<String> symbols;
	private ArrayList<String> operators;
	private ArrayList<String> postfix;
	private ArrayList<TextView> symbolsDisp;
	private ArrayList<TextView> operatorsDisp;
	private ArrayList<TextView> postfixDisp;
	private Button proceedButton;
	private TextView dummyButton;
	private TableLayout layout;
	private Context context;
	private TableRow tr;
	private TableRow trbtn;
	private ArrayList<TableRow> trs;
	
	public Tabulation(Context context, TableLayout layout){
		this.layout = layout;
		this.context = context;
		stack = new ArrayList<String>();
		symbolStack = new ArrayList<String>();
		operatorStack = new ArrayList<String>();
		postfixStack = new ArrayList<String>();
		
		symbols = new ArrayList<String>();
		operators = new ArrayList<String>();
		symbolsDisp = new ArrayList<TextView>();
		postfix = new ArrayList<String>();
		operatorsDisp = new ArrayList<TextView>();
		postfixDisp = new ArrayList<TextView>();
		
		tr = new TableRow(context);
		trbtn = new TableRow(context);
		trs = new ArrayList<TableRow>();
	}
	
	public void createStacks(){
		
		stack = Statics.stack;
		setSymbolColumn();
	}

	public void setSymbolColumn(){
	
		int i;
		for (i = 0; i < stack.size(); ++i) {
			symbolStack.add(stack.get(i));	
		}
	
		symbolStack.add(0,"(");
		symbolStack.add(symbolStack.size(),")");
	
		startPostponement();
	}


	public void startPostponement(){
	
		int i;
		for(i = 0;i < symbolStack.size();i++){
	
			if(symbolStack.get(i).equals("(") || symbolStack.get(i).equals(")") || symbolStack.get(i).equals("+") ||
					symbolStack.get(i).equals("-")  || symbolStack.get(i).equals("/")  || symbolStack.get(i).equals("*")  || symbolStack.get(i).equals("^") ){
	
				if(operatorStack.size() == 0 || operatorStack.size() == 1){
					operatorStack.add(symbolStack.get(i));
	
				}else{
	
					if(symbolStack.get(i).equals("(") || symbolStack.get(i).equals("^")){
						operatorStack.add(symbolStack.get(i));
	
					}else if(symbolStack.get(i).equals(")")){
	
						int reverseIterator = operatorStack.size() - 1;
	
						do{
							postfixStack.add(operatorStack.get(operatorStack.size()-1));
							operatorStack.remove(operatorStack.size()-1);
							reverseIterator--;
	
						}while(!(operatorStack.get(reverseIterator).equals("(")));
	
						operatorStack.remove(operatorStack.size()-1);
	
					}else{
						int reverseIterator = operatorStack.size() - 1;
	
						do{
							if(CharInquiry.isIncomingHigher(symbolStack.get(i), operatorStack.get(reverseIterator))){
	
								operatorStack.add(symbolStack.get(i));
								reverseIterator = 0;
	
							}else if(CharInquiry.isIncomingEqual(symbolStack.get(i), operatorStack.get(reverseIterator)) ||
									(!(CharInquiry.isIncomingHigher(symbolStack.get(i), operatorStack.get(reverseIterator))))){
	
								postfixStack.add(operatorStack.get(operatorStack.size()-1));
								operatorStack.remove(operatorStack.size()-1);
	
								if(operatorStack.size() == 1){
									operatorStack.add(symbolStack.get(i));
								}
							}
	
							reverseIterator--;
	
						}while(reverseIterator > 0);
					}
				}
	
			}else if((symbolStack.get(i).charAt(0) >= 47 && symbolStack.get(i).charAt(0) <= 57)){						//0-9
	
				postfixStack.add(symbolStack.get(i));
	
			}
	
			displayPostponementTable(i);

		}

		proceedButton = new Button(context);
		dummyButton = new TextView(context);
		proceedButton.setText("Evaluate");
		
		trbtn.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		trbtn.addView(dummyButton);
		trbtn.addView(proceedButton);
		layout.addView(trbtn);
		
		proceedButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				goNext();
			}
		});
	}
	
	public void displayPostponementTable(int iterator){
	
		
			if(iterator == 0){
				tr.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				TextView symbolTxtView = new TextView(context);
				symbolTxtView.setText("Symbol");
				symbolTxtView.setTextSize(Statics.screenW/35);
				
				tr.addView(symbolTxtView);
				
				TextView operStackTxtView = new TextView(context);
				operStackTxtView.setText("Operator Stack");
				operStackTxtView.setTextSize(Statics.screenW/35);
				
				tr.addView(operStackTxtView);
				
				TextView postfixStackTxtView = new TextView(context);
				postfixStackTxtView.setText("Postfix Stack");
				postfixStackTxtView.setTextSize(Statics.screenW/35);
				
				tr.addView(postfixStackTxtView);
				layout.addView(tr);
			}
			trs.add(new TableRow(context));

			symbols.add(""+symbolStack.get(iterator));
			
			symbolsDisp.add(new TextView(context));
			TextView temp = symbolsDisp.get(iterator);
			temp.setText(""+symbols.get(iterator));
			temp.setTextSize(Statics.screenW/40);
			
			trs.get(iterator).addView(symbolsDisp.get(iterator));
			
			String tempStr = "";
			
			for (int i = 0; i < operatorStack.size(); i++) {
				tempStr = tempStr+" "+operatorStack.get(i);
			}
			
			operators.add(tempStr);
			
			operatorsDisp.add(new TextView(context));
			TextView temp2 = operatorsDisp.get(iterator);
			temp2.setText(""+operators.get(iterator));
			temp2.setTextSize(Statics.screenW/40);

			trs.get(iterator).addView(operatorsDisp.get(iterator));
			
			String tempStr2 = "";
			
			for (int i = 0; i < postfixStack.size(); i++) {
				tempStr2 = tempStr2+" "+postfixStack.get(i);
			}
			
			postfix.add(tempStr2);
			
			postfixDisp.add(new TextView(context));
			TextView temp3 = postfixDisp.get(iterator);
			temp3.setText(""+postfix.get(iterator));
			temp3.setTextSize(Statics.screenW/40);
			
			trs.get(iterator).addView(postfixDisp.get(iterator));
			trs.get(iterator).setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			
			
			layout.addView(trs.get(iterator));
	}
	
	private void goNext(){
		
		Statics.stack = postfixStack;
		
		Intent newIntent = new Intent(context, DSAEvaluationActivity.class);
		context.startActivity(newIntent);
	}
}


