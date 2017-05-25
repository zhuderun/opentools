package archivefile;

import java.io.File;
import java.util.concurrent.ExecutorService;

public class FileManager {
	
	private FileResolver [] fileResolvers;
	private File root;
	private ExecutorService pool;
	
	public FileManager(String root,ExecutorService pool,FileResolver ...fileResolvers){
		this.root = new File(root);
		this.pool = pool;
		this.fileResolvers = fileResolvers;
	}
	
	
	public void startResolve(){
		execute(root);
	}
	
	
	private void execute(File root){
		File [] files = root.listFiles();
		for(File f:files){
			boolean hasResolve = false;
			for(FileResolver fr:fileResolvers){
				if(fr.canResolve(f) && !hasResolve){
					hasResolve = true;
					Runnable task = new Runnable() {
						@Override
						public void run() {
							fr.Resolve(f);
						}
					};
					pool.submit(task);
					break;
				}
			}
			if(!hasResolve&&f.isDirectory()){
				execute(f);
			}
		}
	}

}
