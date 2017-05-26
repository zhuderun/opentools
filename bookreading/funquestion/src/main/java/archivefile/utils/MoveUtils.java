package archivefile.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoveUtils {

	/*
	 * 移动一个文件到另一个文件夹里，如果已存在，换一个名字移动
	 */
	
	private static Logger logger = LoggerFactory.getLogger(MoveUtils.class);
	
	public static File moveFileIntoDir(Path from,Path to,String newName) throws IOException{
		return moveDirIntoDir(from,to,newName,0);
	}
	
	private static File moveDirIntoDir(Path from,Path to,String newName,int version) throws IOException{
		if(from!=null&&to!=null&&StringUtils.isNotEmpty(newName)&&to.toFile().isDirectory()){
			File [] sons = to.toFile().listFiles();
			boolean conflit = false;
			for(File son:sons){
				if(son.getName().equals(newName)){
					conflit = true;
					break;
				}
			}
			if(conflit){
				return moveDirIntoDir(from,to,newName + version,version+1);
			}else{
				Path finallyPath = Paths.get(to.toAbsolutePath() + "/" + newName);
				logger.info("move {} to {}", from.toAbsolutePath().toString(),finallyPath.toAbsolutePath().toString());
				Files.move(from,finallyPath, StandardCopyOption.REPLACE_EXISTING);
				return finallyPath.toFile();
			}
		}
		return null;
	}
	
}
