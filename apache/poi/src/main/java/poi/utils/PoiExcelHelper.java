package poi.utils;



import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.google.common.collect.Lists;

/**
 * ExcelͳһPOI�����ࣨ���2003��ǰ��2007�Ժ����ָ�ʽ�ļ��ݴ���
 * @author	chengesheng
 * @date	2012-5-3 ����03:10:23
 * @note	PoiHelper
 */
public abstract class PoiExcelHelper {
	public static final String SEPARATOR = ",";
	public static final String CONNECTOR = "-";

	/** ��ȡsheet�б��������ʵ�� */
	public abstract ArrayList<String> getSheetList(File file);
	
	/** ��ȡExcel�ļ����� */
	public List<List<String>> readExcel(File file, int sheetIndex) {
		return readExcel(file, sheetIndex, "1-", "1-");
	}
	
	/** ��ȡExcel�ļ����� */
	public List<List<String>> readExcel(File file, int sheetIndex, String rows) {
		return readExcel(file, sheetIndex, rows, "1-");
	}
	
	/** ��ȡExcel�ļ����� */
	public List<List<String>> readExcel(File file, int sheetIndex, String[] columns) {
		return readExcel(file, sheetIndex, "1-", columns);
	}
	
	/** ��ȡExcel�ļ����ݣ��������ʵ�� */
	public abstract List<List<String>> readExcel(File file, int sheetIndex, String rows, String columns);

	/** ��ȡExcel�ļ����� */
	public List<List<String>> readExcel(File file, int sheetIndex, String rows, String[] columns) {
		int[] cols = getColumnNumber(columns);
		
		return readExcel(file, sheetIndex, rows, cols);
	}

	/** ��ȡExcel�ļ����ݣ��������ʵ�� */
	public abstract List<List<String>> readExcel(File file, int sheetIndex, String rows, int[] cols);
	

	/**
	 * ��ȡExcel�ļ�����
	 * @param sheet
	 * @param rows
	 * @param cols
	 * @return
	 */
	protected List<List<String>> readExcel(Sheet sheet, String rows, int[] cols) {
		List<List<String>> dataList = Lists.newArrayList();
		// ��������Ϣ���������п��ȡ����
		String[] rowList = rows.split(SEPARATOR);
		for (String rowStr : rowList) {
			if (rowStr.contains(CONNECTOR)) {
				String[] rowArr = rowStr.trim().split(CONNECTOR);
				int start = Integer.parseInt(rowArr[0]) - 1;
				int end;
				if (rowArr.length == 1) {
					end = sheet.getLastRowNum();
				} else {
					end = Integer.parseInt(rowArr[1].trim()) - 1;
				}
				dataList.addAll(getRowsValue(sheet, start, end, cols));
			} else {
				dataList.add(getRowValue(sheet, Integer.parseInt(rowStr) - 1, cols));
			}
		}
		return dataList;
	}


	 /**
	  *  ��ȡ�����С�������
	  * @param sheet
	  * @param startRow ��ʼ��
	  * @param endRow ������
	  * @param startCol ��ʼ��
	  * @param endCol ������ 
	  * @return
	  */
	protected List<List<String>> getRowsValue(Sheet sheet, int startRow, int endRow,
			int startCol, int endCol) {
		if (endRow < startRow || endCol < startCol) {
			return null;
		}
		
		List<List<String>> data = Lists.newArrayList();
		for (int i = startRow; i <= endRow; i++) {
			data.add(getRowValue(sheet, i, startCol, endCol));
		}
		return data;
	}


	/**
	 * ��ȡ�����С�������������
	 * @param sheet
	 * @param startRow ��ʼ������
	 * @param endRow ����������
	 * @param cols ������
	 * @return
	 */
	private List<List<String>> getRowsValue(Sheet sheet, int startRow, int endRow, int[] cols) {
		if (endRow < startRow) {
			return null;
		}
		
		List<List<String>> data = Lists.newArrayList();
		for (int i = startRow; i <= endRow; i++) {
			data.add(getRowValue(sheet, i, cols));
		}
		return data;
	}
	
	/**
	 * ��ȡ������������
	 * @param sheet
	 * @param rowIndex �к�
	 * @param startCol ��ʼ������
	 * @param endCol ����������
	 * @return
	 */
	private ArrayList<String> getRowValue(Sheet sheet, int rowIndex, int startCol, int endCol) {
		if(endCol < startCol) {
			return null;
		}
		
		Row row = sheet.getRow(rowIndex);
		ArrayList<String> rowData = new ArrayList<String>();
		for (int i = startCol; i <= endCol; i++) {
			rowData.add(getCellValue(row, i));
		}
		return rowData;
	}
	
	/** ��ȡ�в����������� */
	private ArrayList<String> getRowValue(Sheet sheet, int rowIndex, int[] cols) {
		Row row = sheet.getRow(rowIndex);
		ArrayList<String> rowData = new ArrayList<String>();
		for (int colIndex : cols) {
			rowData.add(getCellValue(row, colIndex));
		}
		return rowData;
	}
	
	/**
	 * ��ȡ��Ԫ������
	 * 
	 * @param row
	 * @param column
	 *            a excel column string like 'A', 'C' or "AA".
	 * @return
	 */
	protected String getCellValue(Row row, String column) {
		return getCellValue(row,getColumnNumber(column));
	}

	/**
	 * ��ȡ��Ԫ������
	 * 
	 * @param row
	 * @param col
	 *            a excel column index from 0 to 65535
	 * @return
	 */
	private String getCellValue(Row row, int col) {
		if (row == null) {
			return "";
		}
		Cell cell = row.getCell(col);
		return getCellValue(cell);
	}

	/**
	 * ��ȡ��Ԫ������
	 * 
	 * @param cell
	 * @return
	 */
	private String getCellValue(Cell cell) {
		if (cell == null) {
			return "";
		}

		String value = cell.toString().trim();
		try {
			// This step is used to prevent Integer string being output with
			// '.0'.
			Float.parseFloat(value);
			value=value.replaceAll("\\.0$", "");
			value=value.replaceAll("\\.0+$", "");
			return value;
		} catch (NumberFormatException ex) {
			return value;
		}
	}

	/**
	 * Change excel column letter to integer number
	 * 
	 * @param columns
	 *            column letter of excel file, like A,B,AA,AB
	 * @return
	 */
	private int[] getColumnNumber(String[] columns) {
		int[] cols = new int[columns.length];
		for(int i=0; i<columns.length; i++) {
			cols[i] = getColumnNumber(columns[i]);
		}
		return cols;
	}

	/**
	 * Change excel column letter to integer number
	 * 
	 * @param column
	 *            column letter of excel file, like A,B,AA,AB
	 * @return
	 */
	private int getColumnNumber(String column) {
		int length = column.length();
		short result = 0;
		for (int i = 0; i < length; i++) {
			char letter = column.toUpperCase().charAt(i);
			int value = letter - 'A' + 1;
			result += value * Math.pow(26, length - i - 1);
		}
		return result - 1;
	}

	/**
	 * Change excel column string to integer number array
	 * 
	 * @param sheet
	 *            excel sheet
	 * @param columns
	 *            column letter of excel file, like A,B,AA,AB
	 * @return
	 */
	protected int[] getColumnNumber(Sheet sheet, String columns) {
		// ��ֺ����Ϊ��̬������List�ݴ�
		ArrayList<Integer> result =Lists.newArrayList();
		String[] colList = columns.split(SEPARATOR);
		for(String colStr : colList){
			if(colStr.contains(CONNECTOR)){
				String[] colArr = colStr.trim().split(CONNECTOR);
				int start = Integer.parseInt(colArr[0]) - 1;
				int end;
				if(colArr.length == 1){
					end = sheet.getRow(sheet.getFirstRowNum()).getLastCellNum() - 1;
				}else{
					end = Integer.parseInt(colArr[1].trim()) - 1;
				}
				for(int i=start; i<=end; i++) {
					result.add(i);
				}
			}else{
				result.add(Integer.parseInt(colStr) - 1);
			}
		}
		
		// ��Listת��Ϊ����
		int len = result.size();
		int[] cols = new int[len]; 
		for(int i = 0; i<len; i++) {
			cols[i] = result.get(i).intValue();
		}

		return cols;
	}
}
