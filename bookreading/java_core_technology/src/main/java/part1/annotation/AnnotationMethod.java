package part1.annotation;

public class AnnotationMethod {
	
	/*
	 * 在方法中使用泛型，相当于定义一个泛型变量，在此方法声明和方法内部有效。定义在返回值之前
	 */
	public static <T> T getMidle(T... t){
		return t[t.length/2];
	}
	
	public static void main(String[] args) {
		String result = getMidle("a","b","c");
		System.out.println(result);
	}

}
