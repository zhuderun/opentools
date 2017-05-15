package dom4j.v1;

import java.io.FileWriter;
import java.util.Enumeration;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class CreateDemo {

	@Test
	public void writeSystemProperties() throws Exception {
		Document d = createDocument();
		OutputFormat format = new OutputFormat("  ", true);

		FileWriter out = new FileWriter("d:/t.xml");
		XMLWriter writer = new XMLWriter(out, format);
		writer.write(d);
		out.close();

	}

	protected static Document createDocument() throws Exception {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("system");

		Properties properties = System.getProperties();
		for (Enumeration elements = properties.propertyNames(); elements.hasMoreElements();) {
			String name = (String) elements.nextElement();
			String value = properties.getProperty(name);
			Element element = root.addElement("property");
			element.addAttribute("name", name);
			element.addText(value);
		}
		return document;
	}
}
