package com.davin.common.util;

public class StringUtil {

	public static String removeCage(String input){
        String inCage = "";
        int start=0;
        char[] wordInCharArray = input.toCharArray();
        int countTotal = wordInCharArray.length;
        for(int a=0; a<countTotal; a++){
            if((start == 1)&&(!String.valueOf(wordInCharArray[a]).equals(")"))){
                inCage += wordInCharArray[a];
            }
            if(String.valueOf(wordInCharArray[a]).equals("(")){
                start=1;
            }else if(String.valueOf(wordInCharArray[a]).equals(")")){
                start=0;
                inCage += "\n";
            }
        }
        
        return inCage;
    }
}
