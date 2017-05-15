package part1.annotation;

/*
 * 在类上使用泛型，相当于定义一个类型变量，在类中有效
 */
public class AnnotationClass <T>{
	
	private T field1;
	
	public T getField1(){
		return field1;
	}

}

class MyMap<K,V>{
}
