package archivefile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import archivefile.utils.MoveUtils;

public class VideoDirectoryResolver implements FileResolver{
	private Logger logger = LoggerFactory.getLogger(VideoDirectoryResolver.class);
	
	private Path videoRoot;
	private Set<String> videoFormat;
	private String [] video = {"mp4","avi","rmvb","wmv","flv"};
	private File traceFile;//移动记录,查看、回滚用
	
	
	public VideoDirectoryResolver(String videoRootPath) throws IOException {
		this.videoRoot = Paths.get(videoRootPath);
		traceFile = new File(System.getProperty("user.dir") + "/trace"+System.currentTimeMillis()+".txt");
		if(!traceFile.exists()){
			traceFile.createNewFile();
		}
		videoFormat = new HashSet<>();
		for(String v:video){
			videoFormat.add(v);
		}
	}
	
	public VideoDirectoryResolver(File traceFile){
		this.traceFile = traceFile;
	}

	@Override
	public void Resolve(File file) {
		if(canResolve(file)){
			try{
				File result = MoveUtils.moveFileIntoDir(file.toPath(), videoRoot, file.getName());
				if(result!=null){
					recordInTrace(file.getAbsolutePath().toString(),result.getAbsolutePath().toString());
				}
			}
			catch(IOException e){
				logger.error(e.toString());
				throw new RuntimeException(e);
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
	
	public void rollback() throws IOException{
		if(traceFile!=null){
			BufferedReader br = new BufferedReader(new FileReader(traceFile));
			for(String line=null;(line = br.readLine())!=null;){
				String [] ft = line.split("_move_");
				if(ft.length == 2){
					String from = ft[0].substring(0, ft[0].lastIndexOf(System.getProperty("file.separator")));
					String to = ft[1];
					Path ffrom = Paths.get(from);
					Path fto = Paths.get(to);
					MoveUtils.moveFileIntoDir(fto,ffrom,ft[0].substring(ft[0].lastIndexOf(System.getProperty("file.separator"))+1));
				}
			}
			br.close();
		}
	}
}
