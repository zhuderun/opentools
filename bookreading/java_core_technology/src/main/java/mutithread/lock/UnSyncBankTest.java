package mutithread.lock;

public class UnSyncBankTest {
	
	public static void main(String[] args) {
		Bank b = new Bank(100, 1000);
		for(int i=0;i<100;i++){
			TransferRunable tr = new TransferRunable(i, b, 1000);
			Thread t = new Thread(tr);
			t.start();
		}
	}
}
