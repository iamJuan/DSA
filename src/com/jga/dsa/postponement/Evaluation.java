package com.jga.dsa.postponement;

import java.text.NumberFormat;
import java.util.ArrayList;

import android.content.Context;
import android.view.WindowManager.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Evaluation {
	
	private Context context;
	private TableLayout layout;
	private TableRow tr;
	private ArrayList<String> theStack;
	private ArrayList<TableRow> theRows;
	private ArrayList<String> operand1;
	private ArrayList<String> operand2;
	private ArrayList<String> operator;
	private ArrayList<String> pendingNumbers;
	private ArrayList<Float> theNumbers;
	private int pendingNumberIterator;
	
	private NumberFormat formatter;
	
	public Evaluation(Context cont, TableLayout lt){
		context = cont;
		layout = lt;
		theStack = new ArrayList<String>();
		theRows = new ArrayList<TableRow>();
		operand1 = new ArrayList<String>();
		operand2 = new ArrayList<String>();
		operator = new ArrayList<String>();
		pendingNumbers = new ArrayList<String>();
		theNumbers = new ArrayList<Float>();
		tr = new TableRow(context);
	}
	
	public void startEvaluation(){
		theStack = Statics.stack;
		pendingNumberIterator = 0;
		
		for (int i = 0; i < theStack.size(); i++) {
			if(i == 0){

				tr.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
				TextView symbolTxtView = new TextView(context);
				symbolTxtView.setText("Symbol");
				symbolTxtView.setTextSize(Statics.screenW/35);
				
				tr.addView(symbolTxtView);
				
				TextView operand1TxtView = new TextView(context);
				operand1TxtView.setText("Opnd 1");
				operand1TxtView.setTextSize(Statics.screenW/35);
				
				tr.addView(operand1TxtView);
				
				TextView operand2TxtView = new TextView(context);
				operand2TxtView.setText("Opnd 2");
				operand2TxtView.setTextSize(Statics.screenW/35);
				
				tr.addView(operand2TxtView);
				
				TextView operatorTxtView = new TextView(context);
				operatorTxtView.setText("Optr");
				operatorTxtView.setTextSize(Statics.screenW/35);
				
				tr.addView(operatorTxtView);
				
				TextView stackTxtView = new TextView(context);
				stackTxtView.setText("Stack");
				stackTxtView.setTextSize(Statics.screenW/35);
				
				tr.addView(stackTxtView);
				layout.addView(tr);
			}
			
			if(!(theStack.get(i).equals("+") || theStack.get(i).equals("-") || theStack.get(i).equals("*") ||
					theStack.get(i).equals("/") || theStack.get(i).equals("^"))){
				if(pendingNumbers.size() < 1){
					pendingNumbers.add(""+Float.parseFloat(theStack.get(i)));
					theNumbers.add(Float.parseFloat(theStack.get(i)));
				}else{
					pendingNumbers.add(pendingNumbers.get(pendingNumberIterator-1)+"&"+Float.parseFloat(theStack.get(i)));
					theNumbers.add(Float.parseFloat(theStack.get(i)));
				}
				
				pendingNumberIterator++;
				operand1.add("");
				operand2.add("");
				operator.add("");
			}else{					

				
				formatter = NumberFormat.getNumberInstance();
				formatter.setMinimumFractionDigits(2);
				formatter.setMaximumFractionDigits(2);
				
				float n1 = theNumbers.get(pendingNumberIterator-2);
				float n2 = theNumbers.get(pendingNumberIterator-1);
				
				operand1.add(""+formatter.format(n1));
				operand2.add(""+formatter.format(n2));
				operator.add(""+theStack.get(i));
				
				theNumbers.remove(pendingNumberIterator-1);
				pendingNumbers.remove(pendingNumberIterator-1);
				pendingNumberIterator--;
				
				theNumbers.remove(pendingNumberIterator-1);
				pendingNumbers.remove(pendingNumberIterator-1);
				pendingNumberIterator--;
				
				theNumbers.add(getResult(n1, n2, theStack.get(i)));

				if(pendingNumberIterator < 1){
					pendingNumbers.add(""+formatter.format(getResult(n1, n2, theStack.get(i))));
					pendingNumberIterator++;
				}
				else{
					pendingNumbers.add(formatter.format(theNumbers.get(pendingNumberIterator-1))+"&"+formatter.format(getResult(n1, n2, theStack.get(i))));
					pendingNumberIterator++;
				}
				
			}
			
			displayEvaluation(i);
		}
	}
	
	public float getResult(float n1, float n2, String oper){
		
		if(oper.equals("+")){
			return n1 + n2;
		}else if(oper.equals("-")){
			return n1 - n2;
		}else if(oper.equals("*")){
			return n1 * n2;
		}else if(oper.equals("/")){
			return n1 / n2;
		}else if(oper.equals("^")){
			float result = 1;
			
			for (int i = 0; i < n2; i++) {
				result = result * n1;
			}
			
			return result;
		}else{
			return 0;
		}
	}
	
	public void displayEvaluation(int iterator){
		
		theRows.add(new TableRow(context));

		theRows.get(iterator).setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		
		TextView symbol = new TextView(context);
		symbol.setText(theStack.get(iterator));
		symbol.setTextSize(Statics.screenW/40);
		
		theRows.get(iterator).addView(symbol);


		TextView oper1 = new TextView(context);
		oper1.setText(operand1.get(iterator));
		oper1.setTextSize(Statics.screenW/40);
		
		theRows.get(iterator).addView(oper1);


		TextView oper2 = new TextView(context);
		oper2.setText(operand2.get(iterator));
		oper2.setTextSize(Statics.screenW/40);
		
		theRows.get(iterator).addView(oper2);


		TextView oper = new TextView(context);
		oper.setText(operator.get(iterator));
		oper.setTextSize(Statics.screenW/40);
		
		theRows.get(iterator).addView(oper);


		TextView stackers = new TextView(context);
		stackers.setText(pendingNumbers.get(pendingNumberIterator-1));
		stackers.setTextSize(Statics.screenW/40);
		
		theRows.get(iterator).addView(stackers);
	
		
		layout.addView(theRows.get(iterator));
	}
}

