package sort.radix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RadixSort {
	
	private List<LinkedList<Integer>> array0_9;
	
	public RadixSort(){
		array0_9 = new ArrayList();
		for(int i=0;i<10;i++){
			array0_9.add(new LinkedList<>());
		}
	}
	
	public void sort(int [] input){
		int startDigit = 1;
		while(sortByDigit(startDigit,input)){startDigit = startDigit * 10;}
	}
	
	private boolean sortByDigit(int digit,int [] input){
		boolean needLoop = false;
		for(int i:input){
			if(i>=digit*10){
				needLoop = true;
			}
			int index = (i/digit)%10;
			LinkedList<Integer> t = array0_9.get(index);
			t.add(i);
		}
		int index = 0;
		for(LinkedList<Integer> t:array0_9){
			while(!t.isEmpty()){
				input[index] = t.pop();
				index ++ ;
			}
		}
		return needLoop;
	}
	
	public static void main(String[] args) {
		List<Integer> input = new ArrayList<Integer>();
		for(int i=0;i<100;i++){
			input.add(new Random().nextInt(10000));
		}
		int [] a = new int [input.size()];
		for(int i=0;i<input.size();i++){
			a[i] = input.get(i);
		}
		new RadixSort().sort(a);
		
		for(int i:a){
			System.out.print(i + " ");
		}
		System.out.println();
	}

}
