package org.jivesoftware.openfire.plugin.conferenceVote.util;

public class NumberUtil {

	public static double diviReturnDouble(int dividend,int divisor){
		double percent = ((double)dividend)/divisor;
    	int percentInt = (int)(percent*10000);
    	percent = ((double)percentInt) / 100;
    	return percent;
	}
	
	
	public static void main(String args[]){
		System.out.println(diviReturnDouble(1,3));
	}
}
