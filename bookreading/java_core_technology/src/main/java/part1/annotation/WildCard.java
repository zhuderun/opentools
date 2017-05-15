package part1.annotation;

public class WildCard {
	
	public static void main(String[] args) {
		
	}
	
	/*
	 * 只能用在方法的参数
	 */
	public void test(Person<? extends Object> p){
		
	}
	
	public void test2(Person<? super Object> p){}
	
	public void test3(Person<?> p){}
}
