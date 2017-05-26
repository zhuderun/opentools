package funquestion.archivefile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTest {
	public static void main(String[] args) throws IOException {
//		File f = new File("d:/asd/m.txt");
//		Path p = f.toPath();
//		try {
//			f.createNewFile();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(f.exists());
//		Path p = Paths.get("d:/1.JPG");
		File f = new File("f:/");
		for(File ff:f.listFiles()){
			System.out.println(ff.getName()+ "->" + Files.isReadable(ff.toPath()));
		}
	}
}
