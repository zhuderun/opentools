package funquestion;

/*
 * 给一个数字，求0-该数字之间，有多少个1
 */
public class HowMany1 {
	
	
	public static void main(String[] args) {
		int input = 9999;
		System.out.println(violenceMethod(input));
	}
	
	/*
	 * 暴力解法
	 */
	public static int violenceMethod(int input){
		int result = 0;
		for(int i=0;i<=input;i++){
			if(String.valueOf(i).indexOf("1")>=0){
				System.out.println(i);
			}
			result+=String.valueOf(i).length() - String.valueOf(i).replace("1", "").length();
		}
		return result;
	}
	
	/*
	 * 排列组合解法
	 */
	public static int plzh(int input){
		return 0;
	}
	
	//计算排列组合
	public static int c(int cardinal,int option){
		return jc(cardinal)/jc(option);
	}
	
	public static int jc(int n){
		int result = 1;
		for(int i = 2;i<=n;i++){
			result *= i;
		}
		return result;
	}
	
	public static int digit(int input){
		return String.valueOf(input).length();
	}
	
	public static int [] split(int input){
		String str = String.valueOf(input);
		int result [] = new int[str.length()];
		for(int i=0;i<str.length();i++){
			result[i] = Integer.valueOf(String.valueOf(str.charAt(i)));
		}
		return result;
	}

}


