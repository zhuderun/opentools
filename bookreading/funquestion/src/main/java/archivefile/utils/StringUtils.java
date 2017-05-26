package archivefile.utils;

public class StringUtils {
	
	public static boolean isNotEmpty(String str){
		if(str == null)return false;
		if(str.equals("")) return false;
		return true;
	}

}
