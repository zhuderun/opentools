package dom4j.v1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class FileWriterUtils {
	
	public static void write(File file,Document document) throws IOException{
		OutputFormat format = new OutputFormat("  ", true);

		FileWriter out = new FileWriter(file.getAbsolutePath());
		XMLWriter writer = new XMLWriter(out, format);
		writer.write(document);
		out.close();
	}

}
