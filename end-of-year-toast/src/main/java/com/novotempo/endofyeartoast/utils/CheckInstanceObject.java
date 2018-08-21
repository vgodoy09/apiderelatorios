package com.novotempo.endofyeartoast.utils;

import java.util.Collection;

public class CheckInstanceObject {

	public static boolean IsNull(Object object) {
		return object == null ? true : false; 
	}
	
	public static boolean IsNullOrIsEmpty(String object) {
		return object == null ? true : object.isEmpty() ? true : false; 
	}
	
	public static <T> boolean IsNullOrIsEmptyList(Collection<T> object) {
		return object == null ? true : object.isEmpty() ? true : false; 
	}
}
