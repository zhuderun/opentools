package archivefile;

import java.io.File;

public class GameDirectoryResolver implements FileResolver{

	@Override
	public void Resolve(File file) {
		
	}

	/*
	 * (exe文件)[文件夹的大小][exe文件的个数]
	 * 
	 * @see archivefile.FileResolver#canResolve(java.io.File)
	 */
	@Override
	public boolean canResolve(File file) {
		if(file!=null && file.isDirectory()){
			
		}
		return false;
	}

}
