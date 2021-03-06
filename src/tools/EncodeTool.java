package tools;

import java.io.UnsupportedEncodingException;

public class EncodeTool {
	public static int strToint(String str) { // 将String型数据转换为int型数据的方法
		if (str == null || str.equals(""))
			str = "0";
		int i = 0;
		try {
			i = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			i = 0;
			e.printStackTrace();
		}
		return i;
	}

	public static String toChinese(String str) { // 进行转码操作的方法
		if (str == null)
			str = "";
		try {
			str = new String(str.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			str = "";
			e.printStackTrace();
		}
		return str;
	}

	public static String toChineseGBK(String str) { // 进行转码操作的方法
		if (str == null)
			str = "";
		try {
			str = new String(str.getBytes("gbk"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			str = "";
			e.printStackTrace();
		}
		return str;
	}
	public static String toChineseGB(String str) { // 进行转码操作的方法
		if (str == null)
			str = "";
		try {
			str = new String(str.getBytes("gb2312"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			str = "";
			e.printStackTrace();
		}
		return str;
	}
}