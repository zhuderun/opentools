package mutithread.blockqueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockQueueTest {
	
	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		System.out.print("enter startingDirectory ");
//		String directory = in.nextLine();
//		System.out.println("enter keyword");
//		String keyword = in.nextLine();
//		in.close();
		String directory = "d:/book";
		String keyword ="btrace";
		
		
		final int FILE_QUEUE_SIZE = 10;
		final int SEARCH_THREADS = 100;
	
		
		
		BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);
		
		FileEnumertionTask enumerator = new FileEnumertionTask(queue, new File(directory));
		
		System.out.println("start scan files");
		new Thread(enumerator).start();
		
		for(int i=0;i<SEARCH_THREADS;i++){
			new Thread(new SearchTask(queue,keyword)).start();;
		}
		
		
	}

}


class FileEnumertionTask implements Runnable{

	public static File DUMMY = new File("");
	private BlockingQueue<File> queue;
	private File startingDirectory;
	
	public FileEnumertionTask(BlockingQueue<File> queue,File startingDirectory){
		this.queue = queue;
		this.startingDirectory = startingDirectory;
	}
	
	
	@Override
	public void run() {
		try{
			enumerate(startingDirectory);
			queue.put(DUMMY);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		
	}
	
	public void enumerate(File directory) throws InterruptedException{
		File [] files = directory.listFiles();
		for(File f:files){
			if(f.isDirectory()) enumerate(f);
			else queue.put(f);
		}
	}
	
}

class SearchTask implements Runnable{

	private BlockingQueue<File> queue;
	private String keyword;
	
	public SearchTask(BlockingQueue<File> queue,String keyword){
		this.queue = queue;
		this.keyword = keyword;
	}
	
	@Override
	public void run() {
		try{
			boolean done = false;
			while(!done){
				File file = queue.take();
				if(file == FileEnumertionTask.DUMMY){
					done = true;
					queue.put(file);
				}else{
					search(file);
				}
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void search(File file) throws FileNotFoundException{
		try(Scanner in = new Scanner(file)){
			int lineNumber = 0;
			while(in.hasNextLine()){
				lineNumber++;
				String line = in.nextLine();
				if(line.contains(keyword)){
					System.out.printf("%s:%d:%s%n",file.getPath(),lineNumber,line);
				}
			}
		}
	}
	
}
