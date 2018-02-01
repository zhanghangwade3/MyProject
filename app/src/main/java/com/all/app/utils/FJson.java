package com.all.app.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import java.util.List;
import java.util.Map;
public class FJson {
	/**
	 * 
	 * @param jsonStr
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(String jsonStr, String key){
		return JSON.parseObject(jsonStr).getBooleanValue(key);
	}

	/**
	 * 
	 * @param jsonStr
	 * @param key
	 * @return
	 */
	public static String getString(String jsonStr, String key){
		return JSON.parseObject(jsonStr).getString(key);
	}
	/**
	 * 
	 * @param jsonStr
	 * @param key
	 * @return
	 */
	public static double getDouble(String jsonStr, String key){
		return JSON.parseObject(jsonStr).getDoubleValue(key);
	}
	/**
	 * 
	 * @param jsonStr
	 * @param key
	 * @return
	 */
	public static int getInt(String jsonStr, String key){
		return JSON.parseObject(jsonStr).getIntValue(key);
	}
	/**
	 * 
	 * @param jsonStr
	 * @param cls
	 * @return
	 */
	public static <T> T getObject(String jsonStr, Class<T> cls) {
		return JSON.parseObject(jsonStr, cls);
	}

	/**
	 * 
	 * @param jsonStr
	 * @param cls
	 * @return
	 */
	public static <T> List<T> getObjects(String jsonStr, Class<T> cls) {
		return JSON.parseArray(jsonStr, cls);
	}

	/**
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static List<Map<String, String>> getKeyMapsList(String jsonStr) {
		List<Map<String, String>> list;
		list = JSON.parseObject(jsonStr, new TypeReference<List<Map<String, String>>>() {
		});
		return list;
	}
	public static void main(String[] args) {
////		System.out.println(formatStringtoDate("2015年01月06日 08:18"));
//		
		String v = "18629096585341445234124324";
		String newstr = "";
		int size = ((v.length()) % 4 == 0) ? ((v.length()) / 4) : ((v.length()) / 4 + 1);
		for (int i = 0; i < size; i++) {
			int endIndex = (i + 1) * 4;
			if ((i + 1) == size) {
				endIndex = v.length();
			}
			if (i == 0) {
				newstr += v.substring(i, endIndex);
			} else {
				newstr += " " + v.substring(i *4, endIndex);
			}
		}
		// String s = newstr.substring(0,newstr.length()-4);
		// s+="****";

//		System.out.println(newstr);
		
		String pcode="18629096585";
		pcode=pcode.substring(0,3)+"****"+pcode.substring(7);
		System.out.println(pcode);
//	}
//	String str = "1233456312321323435";
//    String tempStr = str.substring(str.length()-4,str.length());
//    str = str.replaceAll("[\\w\\W]","*");
//    str = str.substring(0, str.length()-4)+tempStr;
//    System.out.println(str);
	}
	
}
