package opencv.v1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

public class SnapshotDemo {

	public static void main(String[] args) throws IOException {
		FFmpegFrameGrabber ffg = FFmpegFrameGrabber.createDefault("F:/video/Wildlife.mp4");
		ffg.start();
		ffg.setSensorPattern(10);
		Frame f =  null;
		f = ffg.grabImage();
		f = ffg.grabImage();
		FileChannel fc1 = new FileOutputStream("F:/video/Wildlife.jpg").getChannel();
		Java2DFrameConverter j2d = new Java2DFrameConverter();
		BufferedImage bi = j2d.convert(f);
		System.out.println(bi);
		ImageIO.write(bi, "jpg", new File("F:/video/Wildlife.jpg"));
		fc1.close();
		ffg.stop();
	}
}
