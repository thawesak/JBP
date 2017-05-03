package test;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.HashMap;
import java.util.Map;

public class Test6 {

	private static final int D1 = 2, D7 = 7, D30 = 25;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int result = 0;
		//result = solution(1, 2, 4, 5, 7, 29, 30);
		result = solution(1, 3, 5, 9, 10, 13, 14, 15, 17, 19, 21, 23, 24, 27);
		System.out.println(result);
		
		
	}	
	
	public static int solution(int... paramArrayTravelDays) {
			
//		int result = 0;
//		int[] arrayResult = new int[30];
//		//Map mapResult = new HashMap();
//		for (int i = 0; i < arrayDays.length; i++) 
//		{
//			System.out.println("== arrayDay[" + i + "] -> " + arrayDays[i] + " ==");
//			if (i==0)
//		    {
//		    	arrayResult[i] = D1;
//		    	result = D1;
//		    } else {
//		    	arrayResult[i] = arrayResult[i-1];
//		    	//System.out.println(arrayResult[i] + D1);
//		    	//System.out.println((arrayDays[i]-7)<0?0:(arrayResult[i]+D7));
//		    	
//		    	System.out.println(arrayDays[i]-7);
//		    	
//		    	result = min( arrayResult[i] + D1 , (arrayDays[i]-7)<=0?D7:(arrayResult[i]+D7));
//		    	arrayResult[i] = result;
//		    }
//			
//			System.out.println("result = " + result);
//		}
//		
//		return 999;
			
			
//			if (i==0)
//		    {
//		    	result = result + D1;
//		    	//arrayResult[0] = D1;
//		    	mapResult.put(i, D1);
//		    } else {
//		    	mapResult.put(i, mapResult.get(i-1));
//		    	result = min(((int)(mapResult.get(i)) + D1), ((int)(mapResult.get(i-7)==null?0:mapResult.get(i-7))) + D7);
//		    	mapResult.put(i, result);
//		    	
//		    	System.out.println("result -> " + result);
//		    	System.out.println("(int)(mapResult.get(i)) + D1 -> " + (int)(mapResult.get(i)) + D1);
//		    	System.out.println("(int)(mapResult.get(i-7)==null?0:mapResult.get(i-7))) + D7 -> " + (int)(mapResult.get(i-7)==null?0:mapResult.get(i-7)) + D7);
//		    }
		    
			
			
//			ts[i] = ts[i - 1];
//			r[i] = r[i - 1];
//			
//			if (j < ds.length && ds[j] == i) {
//				++ts[i]; // have a trip
//				++j;
//				
//				r[i] = r[i] + D1; // ordinary ticket price increase
//				r[i] = (ts[i] - ts[max(i-7, 0)]) > 3 ? min(r[max(0, i - 7)] + D7, r[i]) : r[i];
//			}
			
		
	
	
	
		int[] arrayLastCountTravelDays = new int[paramArrayTravelDays[paramArrayTravelDays.length-1]]; 
		int[] arrayResult = new int[paramArrayTravelDays[paramArrayTravelDays.length-1]]; 
		int j = 0;
		int diffLastTravelDays = 0;
		
		for (int i = 1; i < paramArrayTravelDays[paramArrayTravelDays.length-1]; ++i) {
			arrayLastCountTravelDays[i] = arrayLastCountTravelDays[i - 1];
			arrayResult[i] = arrayResult[i - 1];
			
			if (j < paramArrayTravelDays.length && paramArrayTravelDays[j] == i) {
				++j;
				++arrayLastCountTravelDays[i];				
				
				diffLastTravelDays = arrayLastCountTravelDays[i] - arrayLastCountTravelDays[max(i-7, 0)];
				//System.out.println("diffLastTravelDays = " + diffLastTravelDays);
				
				if (diffLastTravelDays >= 0 && diffLastTravelDays < 4)
				{
					arrayResult[i] = arrayResult[i] + D1;
				} else if (diffLastTravelDays >= 4 && diffLastTravelDays < 23)
				{
					arrayResult[i] = min(arrayResult[max(0, i - 7)] + D7, arrayResult[i] + D1);
				} else if (diffLastTravelDays > 23)
				{
					arrayResult[i] = min(arrayResult[max(0, i - 30)] + D30, arrayResult[i] + D1);
				}	
				
				System.out.println("day " + i + " = " + arrayLastCountTravelDays[i]);				
			}
		}	
		
		return arrayResult[arrayResult.length-1];
		
	}
	
	private static int getTrips(int[] arrayForEachTravelDays, int left, int right) {
		return arrayForEachTravelDays[right] - arrayForEachTravelDays[max(left, 0)];
	}

}
