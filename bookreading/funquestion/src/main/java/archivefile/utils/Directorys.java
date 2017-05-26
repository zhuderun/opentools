package archivefile.utils;

import java.io.File;

public class Directorys {
	
	public static boolean isContainDir(File parent,File son){
		boolean flg = false;
		if(parent!=null && son!=null && parent.isDirectory() && son.isDirectory()){
			File [] files = parent.listFiles();
			for(File f:files){
				if(f.isDirectory()&&f.getName().equals(son.getName())){
					flg = true;
					break;
				}
			}
		}
		return flg;
	}
	
	public static boolean isContainFile(File parent,File son){
		boolean flg = false;
		if(parent!=null && son!=null && parent.isDirectory()){
			File [] files = parent.listFiles();
			for(File f:files){
				if(f.isDirectory()&&f.getName().equals(son.getName())){
					flg = true;
					break;
				}
			}
		}
		return flg;
	}

}
