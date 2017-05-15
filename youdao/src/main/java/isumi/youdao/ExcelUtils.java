package isumi.youdao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import poi.utils.PoiExcelHelperIml;

public class ExcelUtils {
	
	public static List<Word> read(File file,int sheetIndex,int wordStarthang){
		PoiExcelHelperIml reader = new PoiExcelHelperIml();
		List<List<String>> result = reader.readExcel(file, sheetIndex);
		
		List<Word> words = new ArrayList();
		int i = 1;
		for(List<String> hang:result){
			int j = 1;
			if(i>=wordStarthang){
				Word w = new Word();
				for(String lie:hang){
					if(j ==2){
						w.setWord(lie);
					}else if(j ==3){
						w.setTrans(lie);
					}else if(j ==4){
						w.setPhonetic(lie);
					}
					/*switch (j) {
					case 2:
						w.setWord(lie);
						break;
					case 3:
						w.setTrans(lie);
						break;
					case 4:
						w.setPhonetic(lie);
						break;
					default:
						break;
					}*/
					j++;
				}
				if(w.getWord()!=null&&w.getWord().trim()!=""){
					words.add(w);
				}
			}
			i++;
		}
		return words;
	}
	
	public static void main(String[] args) {
		List<Word> words = read(new File("D:/english/���SAT���ϴ����5.5��/�и����и��л����ʻ�����4.0��/��������SAT�и������ʻ�excel���а�3.0/������1500excel���а�3.0.xls"), 0,6);
		for(Word w:words){
			System.out.println(w.getWord());
		}
	}

}
