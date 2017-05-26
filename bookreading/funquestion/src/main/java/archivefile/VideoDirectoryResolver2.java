package archivefile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import archivefile.utils.Directorys;

public class VideoDirectoryResolver2 implements FileResolver{
	private Logger logger = LoggerFactory.getLogger(VideoDirectoryResolver2.class);
	
	private Path videoRoot;
	private Set<String> videoFormat;
	private String [] video = {"mp4","avi","rmvb","wmv","flv"};
	private File traceFile;//移动记录,查看、回滚用
	
	
	public VideoDirectoryResolver2(String videoRootPath) throws IOException {
		this.videoRoot = Paths.get(videoRootPath);
		traceFile = new File(System.getProperty("user.dir") + "/trace.txt");
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
			//如果videoroot已经包含了这个文件夹
			if(Directorys.isContainDir(videoRoot.toFile(), file)){
				moveFiles2D(file.listFiles(), new File(videoRoot.toAbsolutePath().toString() + "/" + file.getName()));
			}else{
				moveD2D(file,videoRoot.toFile());
			}
			
			
			Path to = Paths.get(videoRoot.toAbsolutePath().toString() + "/" + file.getName());
			logger.info("move {} to {}",file.getAbsolutePath(),to.toAbsolutePath().toString());
			//把移动记录到trace
			try{
				Files.move(file.toPath(),to,StandardCopyOption.REPLACE_EXISTING);
				recordInTrace(file.toPath().toAbsolutePath().toString(), to.toAbsolutePath().toString());
			}catch(DirectoryNotEmptyException e){
				//文件夹重名
				File [] files = file.listFiles();
				for(File f:files){
					Path too = Paths.get(to.toAbsolutePath().toString() + "/" + f.getName());
					try{
						Files.move(f.toPath(),too , StandardCopyOption.REPLACE_EXISTING);
						recordInTrace(f.toPath().toAbsolutePath().toString(), too.toAbsolutePath().toString());
					}catch(IOException ee){
						logger.error(ee.toString());
						throw new RuntimeException(ee);
					}
				}
			}
			catch(IOException e){
				logger.error(e.toString());
				throw new RuntimeException(e);
			}
		}
	}
	
	private void moveD2D(File from,File to){
		if(from!=null && to!=null && from.isDirectory() && to.isDirectory()){
			Path newDir = Paths.get(from.getAbsolutePath() + "/"+to.getName());
			try{
				Files.move(from.toPath(), newDir, StandardCopyOption.REPLACE_EXISTING);
				recordInTrace(from.toPath().toAbsolutePath().toString(), newDir.toAbsolutePath().toString());
			}
			catch(IOException e){
				logger.error(e.toString());
				throw new RuntimeException(e);
			}
		}
	}
	
	
	private void moveFiles2D(File [] files,File to){
		if(files!=null && to!=null && to.isDirectory()){
			for(File f:files){
				
			}
		}
	}
	
	private void moveFile2D(File file,File to){
		if(file!=null&&to!=null&&to.isDirectory()){
			if(Directorys.isContainFile(to, file)){
				
			}else{
				Path newFile = Paths.get(to.getAbsolutePath() + "/"+file.getName());
				try{
					Files.move(file.toPath(), newFile , StandardCopyOption.REPLACE_EXISTING);
					recordInTrace(file.toPath().toAbsolutePath().toString(), newFile.toAbsolutePath().toString());
				}
				catch(IOException e){
					logger.error(e.toString());
					throw new RuntimeException(e);
				}
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
