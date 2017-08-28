package com.samples.suj;

public class StoreUtil {

	public static String getName(String nameAndNumber) {
		return nameAndNumber.substring(2, 8);
	}

	public static void main(String[] args) {
		
		System.out.println(StoreUtil.getName("12sreenivas566"));
		
		System.out.println(StoreUtil.getName("12sreeiutytvas566"));
		
	}
}
