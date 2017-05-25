package archivefile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmptyDirectoryResolver implements FileResolver{
	private Logger logger = LoggerFactory.getLogger(EmptyDirectoryResolver.class);
	
	@Override
	public void Resolve(File file) {
		if(canResolve(file)){
			try {
				logger.info("delete empty directory {}",file.getAbsolutePath());
				Files.delete(file.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean canResolve(File file) {
		if(file!=null&&file.isDirectory()){
			if(file.list().length==0){
				return true;
			}
		}
		return false;
	}

}
