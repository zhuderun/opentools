package poi.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.common.collect.Lists;

public class PoiExcel2007Helper extends PoiExcelHelper{

	/** ��ȡsheet�б� */
	public ArrayList<String> getSheetList(File file) {
		ArrayList<String> sheetList = new ArrayList<String>(0);
		try {
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
			Iterator<XSSFSheet> iterator = wb.iterator();
			while (iterator.hasNext()) {
				sheetList.add(iterator.next().getSheetName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sheetList;
	}

	/** ��ȡExcel�ļ����� */
	public List<List<String>> readExcel(File file, int sheetIndex, String rows, String columns) {
		List<List<String>> dataList = Lists.newArrayList();
		try {
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet = wb.getSheetAt(sheetIndex);			
			dataList = readExcel(sheet, rows, getColumnNumber(sheet, columns));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}
	
	/** ��ȡExcel�ļ����� */
	public List<List<String>> readExcel(File file, int sheetIndex, String rows, int[] cols) {
		List<List<String>> dataList = Lists.newArrayList();
		try {
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet = wb.getSheetAt(sheetIndex);			
			dataList = readExcel(sheet, rows, cols);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

}
