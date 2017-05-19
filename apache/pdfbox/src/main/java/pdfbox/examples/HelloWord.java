package pdfbox.examples;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.pdfbox.examples.pdmodel.PrintDocumentMetaData;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import org.apache.pdfbox.text.PDFTextStripper;

public class HelloWord {
	
	public static void main(String[] args) throws IOException {
		PDDocument doc =PDDocument.load(new File("d:/book/Spring Framework Reference Documentation.pdf"));
		System.out.println(doc.getNumberOfPages());
		PDPageTree pt = doc.getPages();
//		for(PDPage page:pt){
//			List<PDAnnotation> annotations = page.getAnnotations();
//			System.out.println(annotations.size());
//		}
		PDPage page1 = pt.get(0);
		List<PDAnnotation> annotations = page1.getAnnotations();
		for(PDAnnotation a:annotations){
			if(a instanceof PDAnnotationLink){
				PDAnnotationLink pa = (PDAnnotationLink) a;
				System.out.println(pa.getPreviousURI());
			}
		}
	}

}
