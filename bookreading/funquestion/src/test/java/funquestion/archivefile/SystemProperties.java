package funquestion.archivefile;

import java.util.Properties;

public class SystemProperties {
	public static void main(String[] args) {
		Properties pros = System.getProperties();
		pros.list(System.out);
	}
}
