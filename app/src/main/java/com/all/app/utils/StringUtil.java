package com.all.app.utils;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类
 *
 * @author
 * @version 1.0.0
 * @date 2014-4-17
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class StringUtil {

    /**
     * 价格保留小数位数
     *
     * @param d   传入double类型
     * @param len 保留小数位数
     */
    public static double round(double d, int len) { // 进行四舍五入 操作
        BigDecimal b1 = new BigDecimal(d);
        BigDecimal b2 = new BigDecimal(1);
        // 任何一个数字除以1都是原数字
        // ROUND_HALF_UP是BigDecimal的一个常量， 表示进行四舍五入的操作
        // return b1.divide(b2, len,BigDecimal.ROUND_HALF_UP).doubleValue();
        return b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

    }


    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
     *
     * @param s 需要得到长度的字符串
     * @return int 得到的字符串长度
     */
    public static int length(String s) {
        if (s == null)
            return 0;
        char[] c = s.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i])) {
                len++;
            }
        }
        return len;
    }

    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }

    /**
     * 获取MD5加密,32位小写
     *
     * @param str
     * @return
     */
    public static String getMd5String(String str) {
        MessageDigest mMessageDigest = null;
        StringBuffer mStringBuffer;
        byte[] digest;
        try {
            mMessageDigest = MessageDigest.getInstance("MD5");
            mMessageDigest.reset();
            mMessageDigest.update(str.getBytes("UTF-8"));
            digest = mMessageDigest.digest();
            mStringBuffer = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                if (Integer.toHexString(0xFF & digest[i]).length() == 1) {
                    mStringBuffer.append("0").append(
                            Integer.toHexString(0xFF & digest[i]));
                } else {
                    mStringBuffer.append(Integer.toHexString(0xFF & digest[i]));
                }
            }
            return mStringBuffer.toString();
        } catch (Exception exception) {
            Log.e("StringUtil", exception.getMessage());
            return null;
        }
    }

    public static boolean isEmpty(CharSequence str) {
        return (str == null || str.length() == 0);
    }

    /**
     * 判断字符串是否没有内容
     *
     * @param string
     * @return 空返回true<br>
     * 非空返回false
     */
    public static boolean isExistsData(String string) {
        return (string == null) || (string.trim().equals(""))
                || (string.equalsIgnoreCase("null"));
    }

    public static final String[] getKeys(Map<String, String> map) {
        String keys[] = new String[map.size()];
        int i = 0;
        for (Iterator iter = map.entrySet().iterator(); iter.hasNext(); ) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            keys[i] = key;
            i++;
        }
        return keys;
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    public static boolean isNotEmpty(Collection<?> c) {
        return !isEmpty(c);
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    public static boolean isEmpty(Collection<?> c) {
        return c == null || c.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static <T> boolean contains(final T[] array, final T v) {
        for (final T e : array)
            if (e == v || v != null && v.equals(e))
                return true;
        return false;
    }

    public static String isContentSameKey(String[] s1, String[] s2) {
        java.util.Arrays.sort(s1);
        String sfz = "";
        for (int i = 0; i < s2.length; i++) {
            if (java.util.Arrays.binarySearch(s1, s2[i]) >= 0)

                return sfz = s2[i];
            ;
        }
        return sfz;
    }

    public static String isContentSameKey(List<String> list, String[] s2) {
        String[] s1 = new String[list.size()];
        for (int i = 0; i < s1.length; i++) {
            s1[i] = list.get(i).split("-")[0];
        }
        java.util.Arrays.sort(s1);
        String sfz = "";
        for (int i = 0; i < s2.length; i++) {
            if (java.util.Arrays.binarySearch(s1, s2[i]) >= 0)

                return sfz = s2[i];
            ;
        }
        return sfz;
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (null != listItem) {
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static String formatStringDate(String date) {
        Date d = null;
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
        try {
            d = format.parse(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return format(d);
    }

    public static String format(Object date) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        return dateformat.format(date);
    }

    public static String formatStringtoDate(String date) {
        String s = date.replace("年", "-").replace("月", "-").replace("日", "-")
                .substring(0, 10);
        return s;
    }

    public static String getCurrentTime(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        String currentTime = sdf.format(date);
        return currentTime;
    }

    public static String getCurrentTime() {
        return getCurrentTime("yyyy-MM-dd  HH:mm:ss");
    }

    /**
     * 图片路径去除下划线
     */
    public static String cancel_(String img_url) {
        String picurl = img_url.substring(0, img_url.lastIndexOf('.') - 1)
                + img_url.substring(img_url.lastIndexOf('.'));
        return picurl;
    }

    public static String getRealToken(String token) {
        return token.substring(0, token.indexOf(".") - 2)
                + token.substring(token.indexOf(".") + 3);
    }

    // /**生成随机appcode*/
    // public static String getNewToken() {
    // String data1 = RandomStringUtils.random(5,
    // "abcdefghijklmnopqrstuvwxyz1234567890");
    // String data2 = RandomStringUtils.random(5,
    // "abcdefghijklmnopqrstuvwxyz1234567890");
    // String xxx = data1+"92"+data2+"4";
    // return xxx;
    // }

    // /**RSA加密方法*/
    // public static String getRsa(String source){
    // byte[] data = source.getBytes();
    // try {
    // byte[] encodedData = RSAUtils.encryptByPublicKey(data, CodeKey.RSA);
    // return Base64Utils.encode(encodedData);
    // } catch (Exception e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // return null;
    // }

    /**
     * 验证 是否是 字母加数字
     *
     * @param min max : 最小 最大位
     * @return true : 匹配 false : 不匹配
     */
    public static boolean isNumWord(String value, int min, int max) {

        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]" + "{" + min
                + "," + max + "}$";
        return value.matches(regex);
    }

    /**
     * 显示卡号最后4位
     *
     * @param cardNumber
     * @return
     */
    public static String getCardNumber(String cardNumber) {
        if (!TextUtils.isEmpty(cardNumber) && (cardNumber.length() > 4)) {
            String tempStr = cardNumber.substring(cardNumber.length() - 4,
                    cardNumber.length());
            cardNumber = cardNumber.replaceAll("[\\w\\W]", "*");
            cardNumber = cardNumber.substring(0, cardNumber.length() - 4)
                    + tempStr;
            System.out.println(cardNumber);
            return cardNumber;
        } else {
            return cardNumber;
        }
    }

    /**
     * 获取手机号
     *
     * @param phone
     * @return
     */
    public static String getPhone(String phone) {
        return phone.substring(0, 3) + "****" + phone.substring(7);
    }

    /**
     * 获取身份证号码
     *
     * @param idCard
     * @return
     */
    public static String getIdCard(String idCard) {
        if (!TextUtils.isEmpty(idCard) && idCard.length() > 4) {
            String prefixStr = idCard.substring(0, 4);
            String tempStr = idCard.substring(idCard.length() - 2,
                    idCard.length());
            idCard = idCard.replaceAll("[\\w\\W]", "*");
            idCard = prefixStr + idCard.substring(4, idCard.length() - 2)
                    + tempStr;
            System.out.println(idCard);
            return idCard;
        } else {
            return idCard;
        }
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
//
//	/**
//	 * 判断电话号码是否正确
//	 */
//	public static Boolean isPhonenumber(String phoneString) {
//
//		if (TextUtils.isEmpty(phoneString)) {
//			return false;
//		} else {
//			String regex = "^(13|15|18|14|17|10|11|12|16|19)[0-9]\\d{8}$";
//			return match(regex, phoneString);
//		}
//	}

    /**
     * @Title: match @Description: TODO @param @param regex @param @param str @param @return @return
     * boolean @throws
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static String List2String(ArrayList<String> mDatas) {
        if (mDatas.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : mDatas) {
            sb.append(str + ",");
        }
        return sb.toString();
    }

    public static ArrayList<String> String2List(String str) {
        String[] strs = str.split(",");
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(strs));
        return arrayList;

    }

}
