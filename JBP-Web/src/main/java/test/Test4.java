package test;

import java.util.ArrayList;
import java.util.List;

public class Test4 {
	static int[] F = null;
	static int[] X = null;
	static List<Integer> B = new ArrayList<Integer>();

	public static void main(String[] args) {	
//		int A[] = {1, 2, 4, 5, 7, 
//					29, 30};
//		int A[] = {1, 2, 4, 5, 7, 
//					29, 30, 31, 32, 35, 
//					100, 101, 102, 103, 104, 105, 106, 
//					107, 108, 109, 110, 111, 112, 113, 
//					114, 115, 116, 117, 118, 119, 120, 
//					121, 122, 123};
//		int A[] = {100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 
//				116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127};
		int A[] = {1, 3, 5, 
					9, 10, 
					13, 14, 15, 17, 19, 
					21, 23, 24, 27};
		int N = A.length;
		X = new int[31];
		System.out.println("Answer : "+solution(A, N));
	}
	
	public static int solution(int A[], int N){
		if(A == null || A.length == 0){
			return 0;
		}
		X[1] = 2;
		X[2] = 4;
		X[3] = 6;
		X[4] = 7;
		X[5] = 7;
		X[6] = 7;
		X[7] = 7;
		X[24] = 25;
		X[25] = 25;
		X[26] = 25;
		X[27] = 25;
		X[28] = 25;
		X[29] = 25;
		X[30] = 25;
		List<Integer> list = new ArrayList<Integer>();
		for(int a : A){
			list.add(a);
		}
		return solution(list);
	}
	
	public static int solution(List<Integer> A){
//		System.out.println("A.size() = "+A.size());
		if(A.size() == 0) return 0;
		if(A.size() > 0 && A.size() <= 3) return X[A.size()];
		B = new ArrayList<Integer>();
//		int consecutivePeriod = 0;
		int maxPeriod = A.size() >= 30 ? 30 : A.size();
		int startDay = 0;
//		int endDay = 0;
		int cnt = 0;
		int diffDay = 0;
		for(int i=0 ; i<maxPeriod ; i++){
			if(i == 0){
				startDay = A.get(i);
//				endDay = startDay+maxPeriod;
				B.add(A.get(i));
				cnt++;
			}else{
				diffDay = A.get(i) - startDay;
				if( diffDay >= 30 ){
					break;
				}else if(diffDay < 7 && cnt <= 7){
					B.add(A.get(i));
					cnt++;
				}else if(diffDay < 30 && cnt >= 7 && cnt <= 30){
					B.add(A.get(i));
					cnt++;
				}else{
					break;
				}
			}
		}
//		System.out.println("1 cnt = "+cnt);
//		for(int a : A){
//			System.out.print(a+", ");
//		}
//		System.out.println("");
		if(X[cnt] > 0){
			A.removeAll(B);
		}else if(cnt >= 7){
			A.subList(0, 7).clear();
			cnt = 7;
		}else if(cnt >= 1){
			A.remove(0);
			cnt = 1;
		}
//		System.out.println("2 cnt = "+cnt);
//		for(int a : A){
//			System.out.print(a+", ");
//		}
//		System.out.println("");
//		System.out.println("######################");
		return X[cnt] + solution(A);
	}
	
}
