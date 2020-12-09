package util;

public class Conversao {
	
	public static int str2int(String s) {
		int num = 0;
		try {
			num = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			num = 0;
		}
		return num;
	}

}