package part1.annotation;

public class InstanceTest {
	
	/*
	 * ����ʱֻ���ԭʼ���ͣ�ӦΪ���Ͳ���֮���ǹ̶���ͬ������
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
