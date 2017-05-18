package mutithread.lock;

public class TransferRunable implements Runnable{

	private int fromAccount;
	private Bank bank;
	private double maxAmount;
	private int delay = 10;
	
	public TransferRunable(int fromAccount,Bank bank,double maxAmount){
		this.bank = bank;
		this.fromAccount = fromAccount;
		this.maxAmount = maxAmount;
	}
	
	
	
	@Override
	public void run() {
		while(true){
			int toAccount = (int) (bank.size()*Math.random());
			double amount = (int)(maxAmount * Math.random());
			bank.transfer(fromAccount, toAccount, amount);
		}
		
	}

}
