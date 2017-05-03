package test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Test2 {

	public static void main(String[] args) {
//		int A[] = {60, 80, 40};
//		int B[] = {2, 3, 5};
//		int N = 3;
//		int M = 5;
//		int X = 2;
//		int Y = 200;
		
		int A[] = {40, 40, 100, 80, 20};
		int B[] = {3, 3, 2, 2, 3};
		int N = 5;
		int M = 3;
		int X = 5;
		int Y = 200;
				
		System.out.println(solution(A, B, N, M, X, Y));
	}

	public static int solution(int A[], int B[], int N, int M, int X, int Y){
		int result = 0;
		if(A.length != B.length && A.length != N){
			System.out.println("Parameter is invalid.");
		}
		
		int weight = 0;
		int countPerson = 0;
		Set<Integer> floors = new HashSet<Integer>(); 
		
		for(int i=0 ; i<N ; i++){
			int tmpWeight = weight+A[i];
			if(tmpWeight <= Y && countPerson < X){
				weight = weight+A[i];
				floors.add(B[i]);
				countPerson++;
			}else{
				System.out.println(Arrays.asList(floors));
				result = result + floors.size() + 1;
				weight = 0;
				countPerson = 0;
				floors = new HashSet<Integer>();
				weight = A[i];
				floors.add(B[i]);
				countPerson++;
			}
		}
		System.out.println(Arrays.asList(floors));
		result = result + floors.size() + 1;
		
		return result;
	}
}
