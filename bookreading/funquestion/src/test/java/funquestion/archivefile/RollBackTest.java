package funquestion.archivefile;

import java.io.File;
import java.io.IOException;

import archivefile.VideoDirectoryResolver;

public class RollBackTest {
	
	public static void main(String[] args) throws IOException {
		VideoDirectoryResolver vd = new VideoDirectoryResolver(new File("D:/git2/bookreading/funquestion/trace1495778170243.txt"));
		vd.rollback();
	}

}
