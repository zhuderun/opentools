package archivefile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/*
 * 归档文件
 */
public class App {
	
	public static void main(String[] args) throws IOException {
		File root = new File("f:/");
		File [] files = root.listFiles();
		for(File f:files){
			
		}
	}
	
	public static String judgeFileType(File file){
		if(file.isDirectory()){
			
		}else{
			return FileType.File;
		}
		return null;
	}
	
	public static String judgeDirectoryType(File directory){
		File [] sonFiles = directory.listFiles();
		boolean isLeafDirectory = true;
		for(File f:sonFiles){
			if(f.isDirectory()){
				isLeafDirectory = false;
				break;
			}
		}
		
		return null;
	}

}



class FileType{
	//文件夹的类型
	public final static String D_GAL_GAME = "galgame";
	public final static String D_MIXED = "mixed";
	public final static String D_VIDEO = "video";
	public final static String File = "";
}


class MyFileVisitor extends SimpleFileVisitor<Path>{

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		System.out.println(file.getFileName());
		return super.visitFile(file, attrs);
	}
}
