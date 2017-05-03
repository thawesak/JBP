package test;

public class Test {

	public static void main(String[] args) {
//		String s = "00-44 48 5555 8361";
//		String s = "0 - 22 1985--324";
		String s = "555372654";
		System.out.println(reformat(s));
	}
	
	public static String reformat(String s){
		String result = "";
		if(s != null && !s.isEmpty()){
			s = s.replaceAll(" ", "");
			s = s.replaceAll("/", "");
			s = s.replaceAll("-", "");
			
			int len = s.length();
			System.out.println("length = "+len);
			if(len > 2){
				int mod = len%3;
				System.out.println("length % 3 = "+mod);
				
				if(mod != 1){
					for(int i=0 ; i<len ; i++){
						if(i != 0 && (i%3 == 0)){
							result = result+"-";
						}
						result = result+s.charAt(i);
					}
				}else{
					int j = len-4;
					for(int i=0 ; i<len ; i++){
						if(i < j){
							if(i != 0 && (i%3 == 0)){
								result = result+"-";
							}
							result = result+s.charAt(i);
						}else{
							if(i%2 == 0){
								result = result+"-";
							}
							result = result+s.charAt(i);
						}
					}
				}
			}else{
				result = s;
			}
		}
		
		return result;
	}

}
