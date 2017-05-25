package archivefile;

import java.io.File;

public class App1 {
	
	public static void main(String[] args) {
		File root = new File("f:/");
		File [] files = root.listFiles();
		for(File f:files){
			System.out.println(f.getName());
		}
		
	}

}
