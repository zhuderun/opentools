package mutithread.threadgroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadPoolTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		File directory = new File("d:/book");
		String keyword = "btrace";
		
		ExecutorService pool  = Executors.newCachedThreadPool();
		
		Callable counter = new MatchCounter(directory, keyword,pool);
		FutureTask<Integer> task = new FutureTask<>(counter);
		
		pool.submit(task);
		
		System.out.println("match count is" + task.get());
		
		pool.shutdown();
		
		
	}

}


class MatchCounter implements Callable<Integer>{
	private File startingDirectory;
	private String keyword;
	private int count = 0;
	private ExecutorService pool;
	
	public MatchCounter(File dir,String keyword,ExecutorService pool){
		this.startingDirectory = dir;
		this.keyword = keyword;
		this.pool = pool;
	} 

	@Override
	public Integer call() throws Exception {
		File [] files = startingDirectory.listFiles();
		for(File f:files){
			if(f.isDirectory()){
				MatchCounter mc = new MatchCounter(f, keyword,pool);
				FutureTask<Integer> task = new FutureTask<>(mc);
				pool.submit(task);
				count = count + task.get();
			}else{
				if(isMatch(f)){
					count++;
				}
			}
		}
		return count;
	}
	
	public boolean isMatch(File file) throws FileNotFoundException{
		Scanner in = new Scanner(file);
		while(in.hasNextLine()){
			String line = in.nextLine();
			if(line.contains(keyword)){
				return true;
			}
		}
		return false;
	}
	
}