package archivefile;

import java.io.File;

public class GameDirectoryResolver implements FileResolver{

	@Override
	public void Resolve(File file) {
		
	}

	@Override
	public boolean canResolve(File file) {
		if(file!=null && file.isDirectory()){
			
		}
		return false;
	}

}
