package suanfashouce;

public class HowManyBinary1 {
	
	public static void main(String[] args) {
		long a = 1231231231231231l;
		String binary = Long.toBinaryString(a);
		System.out.println(binary.length() - binary.replaceAll("1","").length());
		
	}
	
	
	
	
	public static void test(){
		int a = 0x123;
		System.out.println(a);
	}

}
