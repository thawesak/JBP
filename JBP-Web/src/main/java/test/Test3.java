package test;

public class Test3 {

	public static void main(String[] args) {
		String S = "We test coders. Give us a try?";
//		String S = "Forget CVs..Save time . x x";
		System.out.println(solution(S));
	}
	
	private static int solution(String S){
		int result = 0;
//		String[] sentences = S.split("[[\\.]*|[?]*|[!]*]+");
		String[] sentences = S.split("[.?!]");
		int a = 1;
		String[] words = null;
		for(String sentence : sentences){
			System.out.println("sentence["+a+"] = '"+sentence+"'");
			words = sentence.split(" ");
			int i = 0;
			for(String word : words){
				System.out.println("	word = "+word);
				if(!word.trim().isEmpty()){
					i++;
				}
			}
			result = i>result ? i : result;
			a++;
		}
		
		return result;
	}

}
