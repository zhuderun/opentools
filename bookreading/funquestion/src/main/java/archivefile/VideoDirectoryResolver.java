package archivefile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VideoDirectoryResolver implements FileResolver{
	private Logger logger = LoggerFactory.getLogger(VideoDirectoryResolver.class);
	
	private Path videoRoot;
	private Set<String> videoFormat;
	private String [] video = {"mp4","avi","rmvb","wmv","flv"};
	private File traceFile;//移动记录,查看、回滚用
	
	
	public VideoDirectoryResolver(String videoRootPath,File traceFile) throws IOException {
		this.videoRoot = Paths.get(videoRootPath);
		this.traceFile = traceFile;
		if(!traceFile.exists()){
			traceFile.createNewFile();
		}
		videoFormat = new HashSet<>();
		for(String v:video){
			videoFormat.add(v);
		}
	}

	@Override
	public void Resolve(File file) {
		if(canResolve(file)){
			try {
				Path to = Paths.get(videoRoot.toAbsolutePath().toString() + "/" + file.getName());
				logger.info("move {} to {}",file.getAbsolutePath(),to.toAbsolutePath().toString());
				//把移动记录到trace
				try{
					recordInTrace(file.toPath().toAbsolutePath().toString(), to.toAbsolutePath().toString());
				}
				catch(Exception e){
					throw e;
				}
				Files.move(file.toPath(),to,StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean canResolve(File file) {
		boolean flg = false;
		if(file != null && file.isDirectory()){
			File [] files = file.listFiles();
			int videoCount = 0;
			for(File f:files){
				if(isVideo(f)){
					videoCount +=1;
				}else{
					break;
				}
			}
			if(videoCount == files.length && files.length!=0)flg=true;
		}
		return flg;
	}
	
	public  boolean isVideo(File file){
		if(file!=null&&file.isFile()){
			String fileName = file.getName();
			String fileFormat = fileName.substring(fileName.lastIndexOf(".")+1);
			if(videoFormat.contains(fileFormat.toLowerCase()))return true;
			
		}
		return false;
	}
	
	private void recordInTrace(String from,String to) throws IOException{
		if(traceFile!=null){
			synchronized (traceFile) {
				FileWriter fw = new FileWriter(traceFile,true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(from + "_move_" + to);
				bw.newLine();
				bw.close();
			}
		}
	}
	
	public void rollback(){
		if(traceFile!=null){
			
		}
	}
}
