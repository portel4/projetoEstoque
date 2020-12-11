package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversao {

	public static SimpleDateFormat date2dmy = new SimpleDateFormat("dd/MM/yyyy");
	
	public static Date str2dmy(String s) {
		Date data;
		try {
			data = date2dmy.parse(s);
		} catch (ParseException e) {
			data = null;
		}
		return data;
	}
	
	public static int str2int(String s) {
		int num = 0;
		try {
			num = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			num = 0;
		}
		return num;
	}
	
	public static double str2double(String s) {
		double valor = 0;
		try {
			valor = Double.parseDouble(s);
		} catch (NumberFormatException e) {
			valor = 0;
		}
		return valor;
	}	
	
}