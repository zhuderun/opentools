package part1.annotation;

import part1.annotation.AnnotationArray.Animal;

public class AnnotationArray {
	
	public static void main(String[] args) {
		/*
		 * ���������������飬�����޷�ʵ����
		 */
//		Person <String> [] p = new Person<String>[10];//error
		Person<String> [] p = new Person[10];
		p[0] = new Person<String>();
		
	}
	
	class Animal <T>{
		
	}

}


