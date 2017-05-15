package youdao.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hpsf.NoPropertySetStreamException;
import org.apache.poi.hpsf.Property;
import org.apache.poi.hpsf.PropertySet;
import org.apache.poi.hpsf.PropertySetFactory;
import org.apache.poi.hpsf.Section;
import org.apache.poi.poifs.eventfilesystem.POIFSReader;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderEvent;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderListener;
import org.apache.poi.util.HexDump;

public class ReadTest {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		POIFSReader pr = new POIFSReader();
		pr.registerListener(new MyPOIFSReaderListener());
		pr.read(new FileInputStream("D:/english/ºï¸çSAT×ÊÁÏ´óÀñ°ü5.5°æ/ÍÐ¸£³õÖÐ¸ßÖÐ»ù´¡´Ê»ã´óÀñ°ü4.0°æ/ºï¸ç³õ¸ßÖÐSATÍÐ¸£»ù´¡´Ê»ãexcel±³ËÐ°æ3.0/ºï¸ç³õÖÐ1500excel±³ËÐ°æ3.0.xls"));
	}

}

class MyPOIFSReaderListener implements POIFSReaderListener{

	@Override
	public void processPOIFSReaderEvent(POIFSReaderEvent event) {
		 PropertySet ps = null;
         try
         {
             ps = PropertySetFactory.create(event.getStream());
         }
         catch (NoPropertySetStreamException ex)
         {
             out("No property set stream: \"" + event.getPath() +
                 event.getName() + "\"");
             return;
         }
         catch (Exception ex)
         {
             throw new RuntimeException
                 ("Property set stream \"" +
                  event.getPath() + event.getName() + "\": " + ex);
         }

         /* Print the name of the property set stream: */
         out("Property set stream \"" + event.getPath() +
             event.getName() + "\":");

         /* Print the number of sections: */
         final long sectionCount = ps.getSectionCount();
         out("   No. of sections: " + sectionCount);

         /* Print the list of sections: */
         List<Section> sections = ps.getSections();
         int nr = 0;
         for (Iterator<Section> i = sections.iterator(); i.hasNext();)
         {
             /* Print a single section: */
             Section sec = i.next();
             out("   Section " + nr++ + ":");
             String s = hex(sec.getFormatID().getBytes());
             s = s.substring(0, s.length() - 1);
             out("      Format ID: " + s);

             /* Print the number of properties in this section. */
             int propertyCount = sec.getPropertyCount();
             out("      No. of properties: " + propertyCount);

             /* Print the properties: */
             Property[] properties = sec.getProperties();
             for (int i2 = 0; i2 < properties.length; i2++)
             {
                 /* Print a single property: */
                 Property p = properties[i2];
                 long id = p.getID();
                 long type = p.getType();
                 Object value = p.getValue();
                 out("      Property ID: " + id + ", type: " + type +
                     ", value: " + value);
             }
         }
     }
	
		static void out(final String msg)
	    {
	        System.out.println(msg);
	    }

	    static String hex(final byte[] bytes)
	    {
	        return HexDump.dump(bytes, 0L, 0);
	    }
		
	
}
