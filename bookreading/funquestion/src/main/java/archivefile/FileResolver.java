package archivefile;

import java.io.File;

public interface FileResolver {
	
	public void Resolve(File file);
	
	public boolean canResolve(File file);

}
