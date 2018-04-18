package mercari.com.example.lily.common.helper;


import java.text.DecimalFormat;

import mercari.com.example.lily.common.constant.Constant;


public class ConvertHelper {

	public static String trim(Object obj) {
		if (obj == null) {
			return Constant.STRING_EMPTY;
		}

		return obj.toString().trim();
	}

	public static String toString(Object obj) {
		return trim(obj);
	}

	public static Double toDouble(String str) {

		if (CheckHelper.isStringEmpty(str)) {
			return 0d;
		}
		try {
			return Double.parseDouble(str);
		} catch (Exception e) {
			return 0d;
		}
	}

	public static String toDollar(Object obj){
		String strObj = toString(obj);
		if (CheckHelper.isNumber(strObj) == false){
			return Constant.STRING_EMPTY;
		}

		DecimalFormat df = new java.text.DecimalFormat("$#,###");
		String value = df.format(toDouble(strObj));
		return value;
	}
}
