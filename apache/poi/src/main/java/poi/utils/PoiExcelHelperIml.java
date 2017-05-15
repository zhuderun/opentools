package poi.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PoiExcelHelperIml extends PoiExcelHelper{
	
	private String excel2003 = "xls";
	
	private String excel2007 = "xlsx";

	@Override
	public ArrayList<String> getSheetList(File file) {
		if(file!=null){
			String filename = file.getName();
			String stype = filename.substring(filename.lastIndexOf(".")+1);
			if(excel2003.equals(stype)){
				return new PoiExcel2003Helper().getSheetList(file);
			}else if(excel2007.equals(stype)){
				return new PoiExcel2007Helper().getSheetList(file);
			}
		}
		return null;
	}

	@Override
	public List<List<String>> readExcel(File file, int sheetIndex, String rows, String columns) {
		if(file!=null){
			String filename = file.getName();
			String stype = filename.substring(filename.lastIndexOf(".")+1);
			if(excel2003.equals(stype)){
				return new PoiExcel2003Helper().readExcel(file, sheetIndex, rows, columns);
			}else if(excel2007.equals(stype)){
				return new PoiExcel2007Helper().readExcel(file, sheetIndex, rows, columns);
			}
		}
		return null;
	}

	@Override
	public List<List<String>> readExcel(File file, int sheetIndex, String rows, int[] cols) {
		if(file!=null){
			String filename = file.getName();
			String stype = filename.substring(filename.lastIndexOf(".")+1);
			if(excel2003.equals(stype)){
				return new PoiExcel2003Helper().readExcel(file,sheetIndex,rows,cols);
			}else if(excel2007.equals(stype)){
				return new PoiExcel2007Helper().readExcel(file,sheetIndex,rows,cols);
			}
		}
		return null;
	}

}
