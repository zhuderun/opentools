package mutithread.future;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		String directory = "d:/book";
		String keyword = "btrace";
		
		MatchCounter mc = new MatchCounter(new File(directory), keyword);
		FutureTask<Integer> task = new FutureTask<>(mc);
		
		Thread t = new Thread(task);
		
		t.start();
		
		System.out.println(task.get() + "match " + keyword);
	}
	

}

class MatchCounter implements Callable<Integer>{
	
	private String keyword;
	private File directory;
	private int count;
	
	
	public MatchCounter(File directory,String keyword){
		this.directory = directory;
		this.keyword = keyword;
	}
	

	@Override
	public Integer call() throws Exception {
		count = 0;
		try{
			File [] files = directory.listFiles();
			List<Future<Integer>> results = new ArrayList(); 
			
			for(File f:files){
				if(f.isDirectory()){
					MatchCounter  mc = new MatchCounter(f, keyword);
					FutureTask<Integer> task = new FutureTask<>(mc);
					results.add(task);
					Thread t = new Thread(task);
					t.start();
				}else{
					if(search(f)) count++;
				}
			}
			
			for(Future<Integer> result:results){
				count+=result.get();
			}
			
		}catch(Exception e){
			
		}
		return count;
	}
	
	public boolean search(File file){
		try{
			Scanner in  = new Scanner(file);
			while(in.hasNextLine()){
				String line = in.nextLine();
				if(line.contains(keyword)){
					return true;
				}
			}
			return false;
		}catch(Exception e){
			return false;
		}
		
	}
	
}
