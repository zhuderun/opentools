package poi.utils;

import java.io.File;
import java.util.List;

import org.junit.Test;

public class PoiExcelHelperTest {
	
	@Test
	public void testRead(){
		PoiExcelHelperIml read = new PoiExcelHelperIml();
		File f = new File(System.getProperty("user.dir") + "/src/main/resources/t2.xls");
		List<String> sheets = read.getSheetList(f);
		for(String s:sheets){
			System.out.println(s);
		}
		
		List<List<String>> result = read.readExcel(f,0);
		int i = 1;
		for(List<String> hang:result){
			int j = 1;
			for(String lie:hang){
				System.out.print("ÐÐ" + i + "ÁÐ" + j);
				System.out.println(lie);
				j++;
			}
			i++;
		}
		
	}
	
	@Test
	public void testPath(){
		System.out.println(System.getProperty("user.dir"));
	}

}
