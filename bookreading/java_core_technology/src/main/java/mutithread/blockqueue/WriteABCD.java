package mutithread.blockqueue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class WriteABCD {
	public static void main(String[] args) throws InterruptedException {
		File afile = new File(System.getProperty("user.dir") + "/src/main/resources/a.txt");
		File bfile = new File(System.getProperty("user.dir") + "/src/main/resources/b.txt");
		File cfile = new File(System.getProperty("user.dir") + "/src/main/resources/c.txt");
		File dfile = new File(System.getProperty("user.dir") + "/src/main/resources/d.txt");
		BlockingQueue<File> aqueue = new ArrayBlockingQueue<>(4);
		BlockingQueue<File> bqueue = new ArrayBlockingQueue<>(4);
		BlockingQueue<File> cqueue = new ArrayBlockingQueue<>(4);
		BlockingQueue<File> dqueue = new ArrayBlockingQueue<>(4);
		aqueue.put(afile);
		bqueue.put(bfile);
		cqueue.put(cfile);
		dqueue.put(dfile);
		
		WriterRunnable wra = new WriterRunnable(aqueue, bqueue, "A");
		WriterRunnable wrb = new WriterRunnable(bqueue, cqueue, "B");
		WriterRunnable wrc = new WriterRunnable(cqueue, dqueue, "C");
		WriterRunnable wrd = new WriterRunnable(dqueue, aqueue, "D");
		
		new Thread(wra).start();
		new Thread(wrb).start();
		new Thread(wrc).start();
		new Thread(wrd).start();
		
		Thread.sleep(10000);
		System.exit(0);
		
	}
	

}

class WriterRunnable implements Runnable{
	private BlockingQueue<File> getQueue;
	private BlockingQueue<File> putQueue;
	private String job;
	
	public WriterRunnable(BlockingQueue<File> getQueue,BlockingQueue<File> putQueue,String job) {
		this.getQueue = getQueue;
		this.putQueue = putQueue;
		this.job = job;
	}
	
	

	@Override
	public void run() {
		try {
			boolean flg  = true;
			while(flg){
				File f = getQueue.take();
				FileWriter fw = new FileWriter(f, true);
				fw.write(job);
				fw.close();
				putQueue.put(f);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
}

