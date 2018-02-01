package com.all.app.utils;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class StringUtils {
    private static Pattern emailer = Pattern.compile(
            "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
    private static SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final static Pattern phone = Pattern.compile(
            "^13[0-9]{1}[0-9]{8}$|15[0-9]{1}[0-9]{8}$|18[0-9]{1}[0-9]{8}$|17[0-9]{1}[0-9]{8}$|14[57]{1}[0-9]{8}$");

    public static Date toDate(String sdate) {
        Date date = new Date();
        try {
            date = dateFormater.parse(sdate);
        } catch (ParseException e) {
        }
        return date;
    }

    /**
     * 判断是不是一个合法的手机号码
     */
    public static boolean isPhone(CharSequence phoneNum) {
        if (isEmpty(phoneNum))
            return false;
        return phone.matcher(phoneNum).matches();
    }

    /**
     * 判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     */
    public static boolean isEmpty(CharSequence input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * @Title: isEmpty @Description: TODO @param @param
     * input @param @return @return boolean @throws
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input)) {
            return true;
        }
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * @Title: isEmail @Description: TODO @param @param
     * email @param @return @return boolean @throws
     */
    public static boolean isEmail(String email) {
        if (email == null || email.trim().length() == 0) {
            return false;
        }
        return emailer.matcher(email).matches();
    }

    /**
     * @Title: toInt @Description: TODO @param @param str @param @param
     * defValue @param @return @return int @throws
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * @Title: toInt @Description: TODO @param @param
     * object @param @return @return int @throws
     */
    public static int toInt(Object object) {
        if (object == null)
            return 0;
        return toInt(object.toString(), 0);
    }

    /**
     * @Title: toLong @Description: TODO @param @param
     * object @param @return @return long @throws
     */
    public static long toLong(String object) {
        try {
            return Long.parseLong(object);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * @Title: toBool @Description: TODO @param @param
     * string @param @return @return boolean @throws
     */
    public static boolean toBool(String string) {
        try {
            return Boolean.parseBoolean(string);
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * @Title: regx @Description: TODO @param @param
     * string @param @return @return boolean @throws
     */
    public static boolean regx(String string) {
        Pattern pattern = Pattern.compile("([a-z0-9]+)");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    /**
     * @Title: reg @Description: TODO @param @param
     * string @param @return @return boolean @throws
     */
    public static boolean reg(String string) {
        Pattern pattern = Pattern.compile("([0-9]+)");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    /**
     * @Title: formatLength @Description: TODO @param @param
     * input @param @return @return String @throws
     */
    public static String formatLength(int input) {
        String result = String.valueOf(input);
        result = String.format("%02d", result);
        return result;
    }

    /**
     * @Title: getHomePages @Description: TODO @param @param
     * homepage @param @return @return String @throws
     */
    public static String getHomePages(String homepage) {
        String result = "";
        if (StringUtils.isEmpty(homepage)) {
            return result;
        }
        boolean flag = homepage.contains("http://");
        if (flag) {
            result = homepage.replaceFirst("http://", "");
        } else {
            {
                result = homepage;
            }
        }
        return result;
    }

    /**
     * 不能全是相同的数字或者字母（如：000000、111111、aaaaaa） 全部相同返回true
     *
     * @param numOrStr
     * @return
     */
    public static boolean equalStr(String numOrStr) {
        boolean flag = true;
        char str = numOrStr.charAt(0);
        for (int i = 0; i < numOrStr.length(); i++) {
            if (str != numOrStr.charAt(i)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * 不能是连续的数字--递增（如：123456、12345678）连续数字返回true
     *
     * @param numOrStr
     * @return
     */
    public static boolean isOrderNumeric(String numOrStr) {
        boolean flag = true;// 如果全是连续数字返回true
        boolean isNumeric = true;// 如果全是数字返回true
        for (int i = 0; i < numOrStr.length(); i++) {
            if (!Character.isDigit(numOrStr.charAt(i))) {
                isNumeric = false;
                break;
            }
        }
        if (isNumeric) {// 如果全是数字则执行是否连续数字判断
            for (int i = 0; i < numOrStr.length(); i++) {
                if (i > 0) {// 判断如123456
                    int num = Integer.parseInt(numOrStr.charAt(i) + "");
                    int num_ = Integer.parseInt(numOrStr.charAt(i - 1) + "") + 1;
                    if (num != num_) {
                        flag = false;
                        break;
                    }
                }
            }
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * 不能是连续的数字--递减（如：987654、876543）连续数字返回true
     *
     * @param numOrStr
     * @return
     */
    public static boolean isOrderNumeric_(String numOrStr) {
        boolean flag = true;// 如果全是连续数字返回true
        boolean isNumeric = true;// 如果全是数字返回true
        for (int i = 0; i < numOrStr.length(); i++) {
            if (!Character.isDigit(numOrStr.charAt(i))) {
                isNumeric = false;
                break;
            }
        }
        if (isNumeric) {// 如果全是数字则执行是否连续数字判断
            for (int i = 0; i < numOrStr.length(); i++) {
                if (i > 0) {// 判断如654321
                    int num = Integer.parseInt(numOrStr.charAt(i) + "");
                    int num_ = Integer.parseInt(numOrStr.charAt(i - 1) + "") - 1;
                    if (num != num_) {
                        flag = false;
                        break;
                    }
                }
            }
        } else {
            flag = false;
        }
        return flag;

    }

    /**
     * 金额转换 10000->10,000
     *
     * @param str1
     * @return
     */
    public static String amountOfConversion(String str1) {
        str1 = new StringBuilder(str1).reverse().toString(); // 先将字符串颠倒顺序
        String str2 = "";
        for (int i = 0; i < str1.length(); i++) {
            if (i * 3 + 3 > str1.length()) {
                str2 += str1.substring(i * 3, str1.length());
                break;
            }
            str2 += str1.substring(i * 3, i * 3 + 3) + ",";
        }
        if (str2.endsWith(",")) {
            str2 = str2.substring(0, str2.length() - 1);
        }
        // 最后再将顺序反转过来
        System.err.println(new StringBuilder(str2).reverse().toString());

        return new StringBuilder(str2).reverse().toString() + ".00";

    }

    /**
     * 获取完成的图片地址
     */
    public static String getImage(String url) {
        if (url == null) {
            return url;
        } else {
            if (url.contains("http")) {
                return url;
            } else {
                return "http:" + url;
            }
        }

    }

    ;

    public static String getRandomNumber(int num, Boolean isDigital) {
        String digital = "0,1,2,3,4,5,6,7,8,9";
        String letter = "0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
        String str2[] = null;
        if (isDigital) {
            str2 = digital.split(",");// 将字符串以,分割
        } else {
            str2 = letter.split(",");// 将字符串以,分割
        }
        Random rand = new Random();// 创建Random类的对象rand
        int index = 0;
        String randStr = "";// 创建内容为空字符串对象randStr
        for (int i = 0; i < num; ++i) {
            index = rand.nextInt(str2.length - 1);// 在0到str2.length-1生成一个伪随机数赋值给index
            randStr += str2[index];// 将对应索引的数组与randStr的变量值相连接
        }
        System.out.println("验证码：" + randStr);// 输出所求的验证码的值
        return randStr;
    }

    /**
     * 去掉小数点后的.00
     *
     * @param floatVal
     * @return
     */
    public static String cancelZero(String floatVal) {
        if (TextUtils.isEmpty(floatVal) || null == floatVal) {
            return "";
        }
        int intVal = (int) (Float.parseFloat(floatVal) * 100);
        if (intVal % 100 == 0) {
            return (intVal / 100) + "";
        }
        return floatVal;
    }

    /***
     * 验证车牌号
     *
     * @param carNo 车牌号
     */
    public static boolean isVehicleCarNumber(String carNo) {
        boolean result = false;
        Pattern pattern = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$");
        Matcher matcher = pattern.matcher(carNo);
        result = matcher.matches();
        return result;

    }
    /*
       * 设置gridview高度
       */
    public static void setGridViewHeight(GridView gridView, int rowNum) {
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        // int num = gridView.getNumColumns();
        int num = rowNum;
        int singleHight = 0;

        if (listAdapter.getCount() > 0) {
            View listItem = (View) listAdapter.getView(0, null, gridView);
            listItem.measure(0, 0);
            singleHight = listItem.getMeasuredHeight();
            int lines = listAdapter.getCount() / num;
            if (listAdapter.getCount() % num > 0) {
                lines += 1;
            }
            totalHeight = singleHight * lines;
        }
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);
    }
}