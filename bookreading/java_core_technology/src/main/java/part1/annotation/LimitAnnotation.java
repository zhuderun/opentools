package part1.annotation;

import java.io.Serializable;

public class LimitAnnotation <T extends Comparable&Serializable>{
	
	public T findMin(T ...inputs){
		T min = inputs[0];
		for(T t:inputs){
			if(t.compareTo(min)<0){
				min = t;
			}
		}
		return min;
	}
	
	
	public <S extends Comparable> S findMax(S ...inputs){
		S max = inputs[0];
		for(S s:inputs){
			if(s.compareTo(max)>0){
				max = s;
			}
		}
		return max;
	} 
	
	
	public static void main(String[] args) {
		LimitAnnotation<Integer> la = new LimitAnnotation<>();
		int min = la.findMin(1,2,3);
		int max = la.findMax(1,2,3);
		System.out.println(min);
		System.out.println(max);
	}

}
