package com.trainingmanagement.service.util;

public class ContainerCheck {

	public static boolean isContain(int[] aArray,int id){
		boolean containCheck=false;
		for (int i : aArray) {
			if(i==id){
				containCheck=true;
				break;
			}else{
				continue;
			}
		}
		return containCheck;
	}
}
