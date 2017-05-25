package archivefile;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;

public class FileManagerTest {
	public static void main(String[] args) throws IOException {
		
		VideoDirectoryResolver cdr = new VideoDirectoryResolver("f:/video",new File("d:/trace/trace.txt"));
		
		FileManager fm = new FileManager("f:/test", Executors.newCachedThreadPool(), new EmptyDirectoryResolver(),cdr);
		fm.startResolve();
		System.out.println(VideoDirectoryResolver.class.getClassLoader().getResource("").getPath());
	}

}
