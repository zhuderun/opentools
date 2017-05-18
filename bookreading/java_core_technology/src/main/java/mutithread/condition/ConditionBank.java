package mutithread.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionBank {
private double [] accounts;
	
	private Lock lock;
	
	private Condition sufficient;
	
	public ConditionBank(int n,double balanceMoney){
		lock = new ReentrantLock();
		sufficient = lock.newCondition();
		accounts = new double [n];
		for(int i=0;i<n;i++){
			accounts[i] = balanceMoney;
		}
	}
	
	public  void transfer(int from,int to,double amount) throws InterruptedException{
		lock.lock();
		try{
			while(accounts[from]<amount){
				sufficient.await();
			}
			
			System.out.print(Thread.currentThread() + "----");
			System.out.print(from + " transfer " + amount + "to " + to);
			accounts[from] = accounts[from] - amount;
			accounts[to] = accounts[to] + amount;
			System.out.println("total is " + count());
			sufficient.signalAll();
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
