package mutithread.maipiao;

import org.junit.Test;

public class TicketOffice {
	
	private Ticket [] tickets;
	
	public int ticketNum;
	
	public void init(int num){
		tickets = new Ticket [num];
		ticketNum = num;
		for(int i=0;i<num;i++){
			Ticket t = new Ticket();
			t.num = i;
			tickets[i] = t;
		}
	}
	
	public synchronized Ticket soldRandom(){
		int index = 0;
		for(Ticket t:tickets){
			if(t!=null){
				tickets[index] = null;
				System.out.println(Thread.currentThread() + "get ticket " + index + "and now total is " + --ticketNum);
				return t;
			}
			index++;
		}
		return null;
	}
	
	public synchronized Ticket soldIndex(int index){
		if(tickets[index] != null){
			return tickets[index];
		}
		return null;
	}
	
	
	@Test
	public void single(){
		TicketOffice to = new TicketOffice();
		to.init(10);
		
		Ticket t;
		while((t = to.soldRandom()) != null){
			System.out.println(t.num);
		}
	}
	
	@Test
	public void muti() throws InterruptedException{
		TicketOffice to = new TicketOffice();
		to.init(100);
		
		Runnable r = new Sold(to);
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Ticket t = null;
				for(int i=0;i<100;i++){
					System.out.println(Thread.currentThread() + "give me ticket 0");
					t = to.soldIndex(0);
					if(t!=null){
						System.out.println("yeah i get it ticket 0");
						break;
					}
					System.out.println("oh ticket 0 is soilded");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		t.start();
		
		
		for(int i = 0;i<100;i++){
			Thread t1 = new Thread(r);
			t1.start();
		}
		
		
		
		Thread.sleep(100000);
	}
	
	

}

class Sold implements Runnable{
	private TicketOffice to;
	public Sold(TicketOffice to){
		this.to = to;
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread() + "give me a ticket");
		while(to.ticketNum>0){
			to.soldRandom();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread() + "sorry no more ticket");
	}
}

class Ticket{
	public int num;
}


