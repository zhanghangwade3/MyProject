package com.all.app.http;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
public class OkHttpParam {
	private final HashMap<String, String> headers = new HashMap<String, String>();
	private final List<Part> params = new ArrayList<Part>();
	private final List<Part> files = new ArrayList<Part>();

	public OkHttpParam() {
		// TODO Auto-generated constructor stub
		params.clear();
		files.clear();
	}

	public List<Part> getParams() {
		return params;
	}

	public List<Part> getFiles() {
		return files;
	}

	// /////////////////////////////////////
	public OkHttpParam addHeader(String key, String value) {
		headers.put(key, value);
		return this;
	}

	public HashMap<String, String> getHeaders() {
		return headers;
	}

	// /////////////////////////////////////
	public OkHttpParam add(String key, String value) {
		if (value == null) {
			value = "";
		}
		Part part = new Part(key, value);
		if (null != key && !"".equals(key) && !params.contains(part)) {
			params.add(part);
		}
		return this;
	}

	public OkHttpParam add(String key, int value) {
		add(key, String.valueOf(value));
		return this;
	}

	public OkHttpParam add(String key, double value) {
		add(key, String.valueOf(value));
		return this;
	}

	public OkHttpParam add(String key, float value) {
		add(key, String.valueOf(value));
		return this;
	}

	public OkHttpParam add(String key, long value) {
		add(key, String.valueOf(value));
		return this;
	}

	public OkHttpParam add(String key, boolean value) {
		add(key, String.valueOf(value));
		return this;
	}

	// //////////////////////////////////////////////////

	/**
	 * @param key
	 * @param file
	 */
	public void add(String key, File file) {
		if (file == null || !file.exists() || file.length() == 0) {
			return;
		}

		boolean isPng = file.getName().lastIndexOf("png") > 0 || file.getName().lastIndexOf("PNG") > 0;
		if (isPng) {
			add(key, file, "image/png; charset=UTF-8");
			return;
		}

		boolean isJpg = file.getName().lastIndexOf("jpg") > 0 || file.getName().lastIndexOf("JPG") > 0 || file.getName().lastIndexOf("jpeg") > 0 || file.getName().lastIndexOf("JPEG") > 0;
		if (isJpg) {
			add(key, file, "image/jpeg; charset=UTF-8");
			return;
		}

		if (!isPng && !isJpg) {
			add(key, new FileWrapper(file, null));
		}
	}

	public void add(String key, File file, String contentType) {
		if (file == null || !file.exists() || file.length() == 0) {
			return;
		}

		MediaType mediaType = null;
		try {
			mediaType = MediaType.parse(contentType);
		} catch (Exception e) {

		}

		add(key, new FileWrapper(file, mediaType));
	}

	public void add(String key, File file, MediaType mediaType) {
		if (file == null || !file.exists() || file.length() == 0) {
			return;
		}

		add(key, new FileWrapper(file, mediaType));
	}

	public void add(String key, FileWrapper fileWrapper) {
		if (!TextUtils.isEmpty(key) && fileWrapper != null) {
			File file = fileWrapper.getFile();
			if (file == null || !file.exists() || file.length() == 0) {
				return;
			}
			files.add(new Part(key, fileWrapper));
		}
	}

	// ///////////////////////////////////////////////

	public String getUrl() {
		if (params.size() <= 0)
			return "";
		StringBuffer sb = new StringBuffer("");
		sb.append("?");
		for (int i = 0; i < params.size(); i++) {
			String key = params.get(i).getKey();
			String value = params.get(i).getValue();
			sb.append(key).append("=").append(value);
			if (i < params.size() - 1)
				sb.append("&");
		}
		return sb.toString();
	}

	boolean isMultipart = false;
	/***/
	public void setMultipart(boolean isMultipart) {
		this.isMultipart = isMultipart;
	}

	public RequestBody getRequsetBody() {
		RequestBody body = null;
		if (files.size() > 0||isMultipart) {
			MultipartBody.Builder builder = new MultipartBody.Builder();
			builder.setType(MultipartBody.FORM);
			for (Part part : params) {
				String key = part.getKey();
				String value = part.getValue();
				builder.addFormDataPart(key, value);
			}
			if(files.size() > 0)
			for (Part part : files) {
				String key = part.getKey();
				FileWrapper file = part.getFileWrapper();
				if (file != null) {
					builder.addFormDataPart(key, file.getFileName(), RequestBody.create(file.getMediaType(), file.getFile()));
				}
			}
			body = builder.build();

		} else {
			FormBody.Builder builder = new FormBody.Builder();
			for (Part part : params) {
				String key = part.getKey();
				String value = part.getValue();
				builder.add(key, value);
			}
			body = builder.build();
		}
		return body;
	}

	public final class Part {
		private String key;
		private String value;
		private FileWrapper fileWrapper;

		public Part(String key, String value) {
			setKey(key);
			setValue(value);
		}

		public Part(String key, FileWrapper fileWrapper) {
			setKey(key);
			this.fileWrapper = fileWrapper;
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public FileWrapper getFileWrapper() {
			return fileWrapper;
		}

		protected void setKey(String key) {
			if (key == null) {
				this.key = "";
			} else {
				this.key = key;
			}
		}

		protected void setValue(String value) {
			if (value == null) {
				this.value = "";
			} else {
				this.value = value;
			}
		}

		@Override
		public boolean equals(Object o) {
			if (o == null || !(o instanceof Part)) {
				return false;
			}
			Part part = (Part) o;
			if (part == null) {
				return false;
			}
			if (TextUtils.equals(part.getKey(), getKey()) && TextUtils.equals(part.getValue(), getValue())) {
				return true;
			}
			return false;
		}
	}

	public class FileWrapper {
		public File file;
		public String fileName;
		public MediaType mediaType;
		private long fileSize;

		public FileWrapper(File file, MediaType mediaType) {
			this.file = file;
			this.fileName = file.getName();
			this.mediaType = mediaType;
			this.fileSize = file.length();
		}

		public String getFileName() {
			if (fileName != null) {
				return fileName;
			} else {
				return "nofilename";
			}
		}

		public File getFile() {
			return file;
		}

		public MediaType getMediaType() {
			return mediaType;
		}

		public long getFileSize() {
			return fileSize;
		}
	}

}
