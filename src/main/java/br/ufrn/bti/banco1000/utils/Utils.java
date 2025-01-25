package br.ufrn.bti.banco1000.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * 
 * @author Gilson Kedson 
 * 
 */
public class Utils {
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    static {
        DATE_FORMAT.setLenient(false);
    }
	
	public static Date getDataFormatada(String data) throws ParseException {
		return DATE_FORMAT.parse(data);
	}
	
	public static String getDataFormatadaStr(Date data) throws ParseException {
		return DATE_FORMAT.format(data);
	}
}
