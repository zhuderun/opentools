package part1.annotation;

public class AnnotationMethod {
	
	/*
	 * �ڷ�����ʹ�÷��ͣ��൱�ڶ���һ�����ͱ������ڴ˷��������ͷ����ڲ���Ч�������ڷ���ֵ֮ǰ
	 */
	public static <T> T getMidle(T... t){
		return t[t.length/2];
	}
	
	public static void main(String[] args) {
		String result = getMidle("a","b","c");
		System.out.println(result);
	}

}
