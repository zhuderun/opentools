package part1.annotation;

public class InstanceTest {
	
	/*
	 * 运行时只检查原始类型，应为泛型擦除之后都是固定相同的类型
	 */
	public static void main(String[] args) {
		Animal<String> a = new Animal();
		//System.out.println(a instanceof Animal<String>);//error
		Animal<Object> b = new Animal();
		System.out.println(a.getClass().equals(b.getClass()));
	}
	

}

class Animal <T>{
	
}
