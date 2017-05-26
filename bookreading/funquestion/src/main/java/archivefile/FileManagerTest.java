package archivefile;

import java.io.IOException;
import java.util.concurrent.Executors;

public class FileManagerTest {
	public static void main(String[] args) throws IOException {
		
		VideoDirectoryResolver cdr = new VideoDirectoryResolver("f:/video");
		
		FileManager fm = new FileManager("f:/", Executors.newCachedThreadPool(), new EmptyDirectoryResolver(),cdr);
		fm.startResolve();
		System.out.println(VideoDirectoryResolver.class.getClassLoader().getResource("").getPath());
	}

}
