package com.jga.dsa.utilities;

import java.util.ArrayList;

public class MathEquation {

	public static final int NO_ERRORS = 0;
	public static final int ERROR_INVALID_CHARACTERS = 1;
	public static final int ERROR_INCOMPLETE_PARENTHESIS = 2;
	public static final int ERROR_INVALID_EQUATION = 3;

	private static String chars[] = {"+","-","/","*","^"};
	
	public static int equationCheckForError(ArrayList<String> input){
		
		int errorType = 0;

		if(hasUnwantedChars(input)){
			errorType = ERROR_INVALID_CHARACTERS;
		}
		
		if(!isParenthesisCorrect(input)){
			errorType = ERROR_INCOMPLETE_PARENTHESIS;
		}
		
		if(!isValidEquation(input)){
			errorType = ERROR_INVALID_EQUATION;
		}
		
		return errorType;
		
	}

	
	private static boolean hasUnwantedChars(ArrayList<String> stack){
		
		boolean isInvalidExisting = true; 
		
		for (int i = 0; i < stack.size(); ++i) {
			if(!CharInquiry.isCharValid(stack.get(i))){
				isInvalidExisting = true;
				i = stack.size();
			}else{
				isInvalidExisting = false;
			}
		}

		return isInvalidExisting;
	}
	
	private static boolean isParenthesisCorrect(ArrayList<String> stack){
		boolean isCorrect = false;
		boolean isComplete = false;
		boolean isOpenProper = false;
		boolean isCloseProper = false;
		int open = 0, close = 0;
		
		for (int i = 0; i < stack.size(); i++) {
			if(isOpenParenthesis(stack.get(i))){
				open++;
				
				if(i > 0){
					if(isOperator(stack.get(i-1))){
						isOpenProper = true;
					}
				}
			}else if(isCloseParenthesis(stack.get(i))){

				if(i < stack.size()-1){
					if(isOperator(stack.get(i+1))){
						isCloseProper = true;
					}
				}else if(i == stack.size()-1){
					isCloseProper = true;
				}
				close++;
			}
		}
		
		if(open == close){
			isComplete = true;
		}
		
		if(isComplete && isOpenProper && isCloseProper){
			isCorrect = true;
		}
		
		if(open == 0 && close == 0){
			isCorrect = true;
		}
		
		return isCorrect;
	}
	
	private static boolean isValidEquation(ArrayList<String> input){
		ArrayList<String> stack = new ArrayList<String>();
		stack = input;
		
		boolean isValid = false;
		
		for (int i = 0; i < stack.size()-2; i+=2) {
			if(!(isOpenParenthesis(stack.get(i)))){
				if(isNumber(stack.get(i))){
					if(isOperator(stack.get(i+1))){
						isValid = true;
					}else if(!(isOpenParenthesis(stack.get(i)))){
						i++;
					}else{
						isValid = false;
						i = stack.size();
					}
					
				}else{
					isValid = false;
					i = stack.size();
				}
			}else{
				i++;
			}
		}
		
		return isValid;
	}
	
	private static boolean isNumber(String str){
		boolean itIs = false;
		char ch;
			
		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);

			if(ch >= 47 && ch <= 57){
				itIs = true;
			}else{
				itIs = false;
			}
		}
		return itIs;
	}
	
	private static boolean isOperator(String str){
		boolean itIs = false;
	
		for (int i = 0; i < chars.length; i++) {
			if(str.equals(chars[i])){
				itIs = true;
				i = chars.length;
			}
		}
		
		System.out.println(str+":"+itIs);
		return itIs;		
	}
	
	private static boolean isOpenParenthesis(String str){
		boolean itIs = false;
		char ch;
		
		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			if(ch == '('){
				itIs = true;
			}
		}

		return itIs;		
	}	
	
	private static boolean isCloseParenthesis(String str){
		boolean itIs = false;
		char ch;
		
		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			if(ch == ')'){
				itIs = true;
			}
		}

		return itIs;		
	}	
}
