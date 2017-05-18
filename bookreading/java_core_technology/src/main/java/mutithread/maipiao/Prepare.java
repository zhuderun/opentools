package mutithread.maipiao;

public class Prepare {
	
	public static void main(String[] args) {
		int [] a = new int [7];
		for(int i = 0;i<1000;i++){
			long current = System.currentTimeMillis();
			long r = current&a.length-1;
			System.out.println(current);
			System.out.println(r);
		}
		
		System.out.println(Math.random());
		
	}

}
