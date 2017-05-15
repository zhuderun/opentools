package isumi.youdao;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.dom4j.CDATA;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import dom4j.v1.FileWriterUtils;

public class WriteUtils {
	
	public static void write(File file,List<Word> words) throws IOException{
		if(file!=null&&words!=null){
			FileWriterUtils.write(file, conver(words));
		}
	}
	
	private static Document conver(List<Word> words){
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("wordbook");
		if(words!=null){
			for(Word word:words){
				Element item = root.addElement("item");
				Element eword = item.addElement("word");
				eword.addText(word.getWord());
				Element etrans = item.addElement("trans");
				etrans.addCDATA(word.getTrans());
				Element ephonetic = item.addElement("phonetic");
				ephonetic.addCDATA(word.getPhonetic());
				Element etags = item.addElement("tags");
				etags.addText(word.getTags());
				Element eprogress = item.addElement("progress");
				eprogress.addText(word.getProgress());
			}
		}
		return document;
	}
	
	public static void main(String[] args) throws IOException {
		List<Word> words = ExcelUtils.read(new File("D:/english/猴哥SAT资料大礼包5.5版/托福初中高中基础词汇大礼包4.0版/猴哥初高中SAT托福基础词汇excel背诵版3.0/猴哥初中1500excel背诵版3.0.xls"), 0,6);
		write(new File("d:/word.xml"), words);
	}

}
