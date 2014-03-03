package com.davin.common.util;

import java.util.AbstractMap;
import java.util.Collection;

public class CheckNullEmpty {

    public static Boolean isNotNullOrEmpty(Object object) {
        return !isNullOrEmpty(object);
    }
    
    public static Boolean isNullOrEmpty(Object object) {
        if (object == null) {
            return true;
        } else {
            if (object instanceof Collection) {
                if (((Collection) object).isEmpty()) {
                    return true;
                }
            } else if (object instanceof AbstractMap) {
                if (((AbstractMap) object).isEmpty()) {
                    return true;
                }
            } else {
                if (object.toString().trim().equals("")) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public static synchronized String checkKeyword(String keyword){
		String searchTerm = "";
		if(keyword != null){
			searchTerm = keyword.toLowerCase();
		}
		
		return searchTerm;
	}
}
