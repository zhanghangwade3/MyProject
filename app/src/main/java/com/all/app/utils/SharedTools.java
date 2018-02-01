package com.all.app.utils;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * 
 * @ClassName: LShared
 * @Description: TODO(描述: SharedPreferences 工具类)
 * @author Lee
 * @date 2016年4月11日 上午10:00:27
 * @version V1.0
 */
public class SharedTools {

	Context context;
	String name;
	public static final String SP_NAME = "tsxn";
	SharedPreferences sp;
	Editor editor;

	public SharedTools(Context context) {
		this.context = context;
		sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		editor = sp.edit();
	}

	public SharedTools(Context context, String name) {
		this.context = context;
		this.name = name;
		sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
		editor = sp.edit();
	}

	public void setBoolean(String key, boolean bool) {
		editor.putBoolean(key, bool);
		editor.commit();
	}

	public boolean getBoolean(String key) {
		return sp.getBoolean(key, false);
	}

	public void setString(String key, String val) {
		editor.putString(key, val);
		editor.commit();
	}

	public String getString(String key) {
		return sp.getString(key, "");
	}

	/**
	 * 根据key和预期的value类型获取value的值
	 *
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T> T getValue(String key, Class<T> clazz) {
		if (context == null) {
			throw new RuntimeException("请先调用带有context,name参数的构造!");
		}

		SharedPreferences sp = this.context.getSharedPreferences(this.name,
				Context.MODE_PRIVATE);
		return getValue(key, clazz, sp);
	}

	/**
	 * 针对复杂类型存储<对象>
	 *
	 * @param key
	 * @param val
	 */
	public void setObject(String key, Object object) {
		SharedPreferences sp = this.context.getSharedPreferences(this.name,
				Context.MODE_PRIVATE);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream out = null;
		try {

			out = new ObjectOutputStream(baos);
			out.writeObject(object);
			String objectVal = new String(Base64.encode(baos.toByteArray(),
					Base64.DEFAULT));
			Editor editor = sp.edit();
			editor.putString(key, objectVal);
			editor.commit();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T getObject(String key, Class<T> clazz) {
		SharedPreferences sp = this.context.getSharedPreferences(this.name,
				Context.MODE_PRIVATE);
		if (sp.contains(key)) {
			String objectVal = sp.getString(key, null);
			byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
			ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
			ObjectInputStream ois = null;
			try {
				ois = new ObjectInputStream(bais);
				T t = (T) ois.readObject();
				return t;
			} catch (StreamCorruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					if (bais != null) {
						bais.close();
					}
					if (ois != null) {
						ois.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 对于外部不可见的过渡方法
	 *
	 * @param key
	 * @param clazz
	 * @param sp
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> T getValue(String key, Class<T> clazz, SharedPreferences sp) {
		T t;
		try {

			t = clazz.newInstance();

			if (t instanceof Integer) {
				return (T) Integer.valueOf(sp.getInt(key, 0));
			} else if (t instanceof String) {
				return (T) sp.getString(key, "");
			} else if (t instanceof Boolean) {
				return (T) Boolean.valueOf(sp.getBoolean(key, false));
			} else if (t instanceof Long) {
				return (T) Long.valueOf(sp.getLong(key, 0L));
			} else if (t instanceof Float) {
				return (T) Float.valueOf(sp.getFloat(key, 0L));
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
			Log.e("system", "类型输入错误或者复杂类型无法解析[" + e.getMessage() + "]");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			Log.e("system", "类型输入错误或者复杂类型无法解析[" + e.getMessage() + "]");
		}
		Log.e("system", "无法找到" + key + "对应的值");
		return null;
	}
}
