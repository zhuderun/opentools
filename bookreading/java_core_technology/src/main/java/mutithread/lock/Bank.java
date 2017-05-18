package mutithread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
	private double [] accounts;
	
	private Lock lock = new ReentrantLock();
	
	public Bank(int n,double balanceMoney){
		accounts = new double [n];
		for(int i=0;i<n;i++){
			accounts[i] = balanceMoney;
		}
	}
	
	public  void transfer(int from,int to,double amount){
		lock.lock();
		try{
			System.out.print(Thread.currentThread() + "----");
			System.out.print(from + " transfer " + amount + "to " + to);
			accounts[from] = accounts[from] - amount;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			accounts[to] = accounts[to] + amount;
			System.out.println("total is " + count());
		}finally{
			lock.unlock();
		}
		
	}
	
	public  double count(){
		double sum = 0l;
		for(double d:accounts){
			sum = sum + d;
		}
		return sum;
	}
	
	public int size(){
		return accounts.length;
	}
	
	
}
