package test;

public class TextJBP {

	public static void main(String[] args) {
		String x = "P-LO-17-0003";
		System.out.println(x.lastIndexOf("-"));
		x = x.substring(x.lastIndexOf("-")+1);
		System.out.println(x);
	}

}
