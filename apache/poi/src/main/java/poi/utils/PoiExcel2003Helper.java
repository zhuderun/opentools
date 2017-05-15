package poi.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.google.common.collect.Lists;

public class PoiExcel2003Helper extends PoiExcelHelper{

	/** ��ȡsheet�б� */
	public ArrayList<String> getSheetList(File file) {
		ArrayList<String> sheetList = Lists.newArrayListWithCapacity(0);
		try {
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
			int i = 0;
			while (true) {
				try {
					String name = wb.getSheetName(i);
					sheetList.add(name);
					i++;
				} catch (Exception e) {
					break;
				}
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
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
			HSSFSheet sheet = wb.getSheetAt(sheetIndex);			
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
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
			HSSFSheet sheet = wb.getSheetAt(sheetIndex);
			dataList = readExcel(sheet, rows, cols);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

}
