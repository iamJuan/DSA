package com.jga.dsa.utilities;

public class CharInquiry {
	
	static int charValue[] = {1,1,2,2,3};
	static char chars[] = {'+','-','/','*','^'};
	
	public static int getValue(String str){
		int value = 0;
		char ch = str.charAt(0);
		
		for (int j = 0; j < 5; j++) {
			if(ch == chars[j]){
				value = charValue[j];
				j = 6;
			}
		}
		
		return value;
	}
	
	public static boolean isIncomingHigher(String incoming, String stack){
		int incomingValue = getValue(incoming);
		int stackValue = getValue(stack);
		
		if(incomingValue > stackValue){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isCharValid(String str){
		boolean isChar = false;
		
		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '('|| str.charAt(i) == ')'|| str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '/' 
					|| str.charAt(i) == '*' || str.charAt(i) == '^' || (str.charAt(i) >= 47 && str.charAt(i) <= 57)){	//0-9
				isChar = true;
			}else{
				isChar = false;
			}	
		}
		
		return isChar;
	}
	
	public static boolean isIncomingEqual(String incoming, String stack){

		int incomingValue = getValue(incoming);
		int stackValue = getValue(stack);

		if(incomingValue == stackValue){
			return true;
		}else{
			return false;
		}
	}
}
