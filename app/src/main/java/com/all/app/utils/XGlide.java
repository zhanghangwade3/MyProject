package com.all.app.utils;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.util.concurrent.ExecutionException;
public class XGlide {

	private static XGlide singleton;
	private static Context mCtx;
	private static RequestManager glideRequest;
	private boolean isDiskCache = true;

	public static XGlide init(Context ctx) {
		glideRequest = Glide.with(ctx);
		// TODO 双重校验锁
		if (null == singleton)
			synchronized (XGlide.class) {
				if (null == singleton) {
					singleton = new XGlide();
				}
			}
		return singleton;
	}

	public void setDiskCache(boolean isCache) {
		this.isDiskCache = isCache;
	}

	/**
	 * url
	 * @param iv
	 * @param url
	 */
	public void display(ImageView iv, String url) {
		display(iv, url, -1);
	}



	/**
	 * url
	 * 
	 * @param iv
	 * @param url
	 * @param defaultResId
	 */
	public void display(ImageView iv, String url, int defaultResId) {
		if (iv == null)
			return;
		DrawableRequestBuilder<String> builder = glideRequest.load(url);
		if (defaultResId != -1)
			builder.placeholder(defaultResId);
		if (isDiskCache)
			builder.diskCacheStrategy(DiskCacheStrategy.ALL);
		builder.into(iv);
	}

	/**
	 * Uri
	 * 
	 * @param iv
	 * @param uri
	 */
	public void display(ImageView iv, Uri uri) {
		display(iv, uri, -1);
	}

	/**
	 * Uri
	 * 
	 * @param iv
	 * @param uri
	 * @param defaultResId
	 */
	public void display(ImageView iv, Uri uri, int defaultResId) {
		if (iv == null)
			return;
		DrawableRequestBuilder<Uri> builder = glideRequest.load(uri);
		if (defaultResId != -1)
			builder.placeholder(defaultResId);
		if (isDiskCache)
			builder.diskCacheStrategy(DiskCacheStrategy.ALL);
		builder.into(iv);
	}

	/**
	 * drawableId
	 * 
	 * @param iv
	 * @param drawableId
	 */
	public void display(ImageView iv, int drawableId) {
		display(iv, drawableId, -1);
	}

	/**
	 * drawableId
	 * 
	 * @param iv
	 * @param drawableId
	 * @param defaultResId
	 */
	public void display(ImageView iv, int drawableId, int defaultResId) {
		if (iv == null)
			return;
		DrawableRequestBuilder<Integer> builder = glideRequest.load(drawableId);
		if (defaultResId != -1)
			builder.placeholder(defaultResId);
		if (isDiskCache)
			builder.diskCacheStrategy(DiskCacheStrategy.ALL);
		builder.into(iv);
	}

	/**
	 * File
	 *
	 * @param iv
	 * @param imgFile
	 */
	public void display(ImageView iv, File imgFile) {
		display(iv, imgFile, -1);
	}

	/**
	 * File
	 * 
	 * @param iv
	 * @param imgFile
	 * @param defaultResId
	 */
	public void display(ImageView iv, File imgFile, int defaultResId) {
		if (iv == null)
			return;
		DrawableRequestBuilder<File> builder = glideRequest.load(imgFile);
		if (defaultResId != -1)
			builder.placeholder(defaultResId);
		if (isDiskCache)
			builder.diskCacheStrategy(DiskCacheStrategy.ALL);
		builder.into(iv);
	}

	/**
	 * byte[]
	 * 
	 * @param iv
	 * @param modle
	 */
	public void display(ImageView iv, byte[] modle) {
		display(iv, modle, -1);
	}

	/**
	 * byte[]
	 * 
	 * @param iv
	 * @param modle
	 * @param defaultResId
	 */
	public void display(ImageView iv, byte[] modle, int defaultResId) {
		if (iv == null)
			return;
		DrawableRequestBuilder<byte[]> builder = glideRequest.load(modle);
		if (defaultResId != -1)
			builder.placeholder(defaultResId);
		if (isDiskCache)
			builder.diskCacheStrategy(DiskCacheStrategy.ALL);
		builder.into(iv);
	}

	// ///圆角图片--------------------------------------------------------------------
	/**
	 * 圆角-Url
	 * 
	 * @param iv
	 * @param url
	 * @param roundDip
	 */
	public void displayRound(ImageView iv, String url, int roundDip) {
		displayRound(iv, url, roundDip, -1);
	}

	/**
	 * 圆角-Url
	 * 
	 * @param iv
	 * @param url
	 * @param roundDip
	 * @param defaultResId
	 */
	public void displayRound(ImageView iv, String url, int roundDip, int defaultResId) {
		if (iv == null)
			return;
		DrawableRequestBuilder<String> builder = glideRequest.load(url).transform(new GlideRoundTransform(mCtx, roundDip));
		if (defaultResId != -1)
			builder.placeholder(defaultResId);
		if (isDiskCache)
			builder.diskCacheStrategy(DiskCacheStrategy.ALL);
		builder.into(iv);
	}

    /**
     * 圆角-Url
     *
     * @param iv
     * @param url
     * @param roundDip
     */
    public void displayRound2(ImageView iv, String url, int roundDip) {
        if (iv == null)
            return;
        DrawableRequestBuilder<String> builder = glideRequest.load(url).transform(new CornersTransform(mCtx, roundDip));

        if (isDiskCache)
            builder.diskCacheStrategy(DiskCacheStrategy.ALL);
        builder.into(iv);
    }

	/**
	 * 圆角-drawableId
	 * 
	 * @param iv
	 * @param drawableId
	 * @param roundDip
	 */
	public void displayRound(ImageView iv, int drawableId, int roundDip) {
		displayRound(iv, drawableId, roundDip, -1);
	}

	/**
	 * 圆角-drawableId
	 * 
	 * @param iv
	 * @param drawableId
	 * @param roundDip
	 * @param defaultResId
	 */
	public void displayRound(ImageView iv, int drawableId, int roundDip, int defaultResId) {
		if (iv == null)
			return;
		DrawableRequestBuilder<Integer> builder = glideRequest.load(drawableId).transform(new GlideRoundTransform(mCtx, roundDip));
		if (defaultResId != -1)
			builder.placeholder(defaultResId);
		if (isDiskCache)
			builder.diskCacheStrategy(DiskCacheStrategy.ALL);
		builder.into(iv);
	}

	/**
	 * 圆角-Uri
	 * 
	 * @param iv
	 * @param uri
	 * @param roundDip
	 */
	public void displayRound(ImageView iv, Uri uri, int roundDip) {
		displayRound(iv, uri, roundDip, -1);
	}

	/**
	 * 圆角-Uri
	 * 
	 * @param iv
	 * @param uri
	 * @param roundDip
	 * @param defaultResId
	 */
	public void displayRound(ImageView iv, Uri uri, int roundDip, int defaultResId) {
		if (iv == null)
			return;
		DrawableRequestBuilder<Uri> builder = glideRequest.load(uri).transform(new GlideRoundTransform(mCtx, roundDip));
		if (defaultResId != -1)
			builder.placeholder(defaultResId);
		if (isDiskCache)
			builder.diskCacheStrategy(DiskCacheStrategy.ALL);
		builder.into(iv);
	}

	/**
	 * 圆角-File
	 * 
	 * @param iv
	 * @param imgFile
	 * @param roundDip
	 */
	public void displayRound(ImageView iv, File imgFile, int roundDip) {
		displayRound(iv, imgFile, roundDip, -1);
	}

	/**
	 * 圆角-File
	 * 
	 * @param iv
	 * @param imgFile
	 * @param roundDip
	 * @param defaultResId
	 */
	public void displayRound(ImageView iv, File imgFile, int roundDip, int defaultResId) {
		if (iv == null)
			return;
		DrawableRequestBuilder<File> builder = glideRequest.load(imgFile).transform(new GlideRoundTransform(mCtx, roundDip));
		if (defaultResId != -1)
			builder.placeholder(defaultResId);
		if (isDiskCache)
			builder.diskCacheStrategy(DiskCacheStrategy.ALL);
		builder.into(iv);
	}

	// 圆图片
	/**
	 * 圆图片-drawableId
	 * 
	 * @param iv
	 * @param drawableId
	 */
	public void displayCircle(ImageView iv, int drawableId) {
		displayCircle(iv, drawableId, -1);
	}

	/**
	 * 圆图片-drawableId
	 * 
	 * @param iv
	 * @param drawableId
	 * @param defaultResId
	 */
	public void displayCircle(ImageView iv, int drawableId, int defaultResId) {
		if (iv == null)
			return;
		DrawableRequestBuilder<Integer> builder = glideRequest.load(drawableId).transform(new GlideCircleTransform(mCtx));
		if (defaultResId != -1)
			builder.placeholder(defaultResId);
		if (isDiskCache)
			builder.diskCacheStrategy(DiskCacheStrategy.ALL);
		builder.into(iv);
	}

	/**
	 * 圆图片-url
	 * 
	 * @param iv
	 * @param url
	 */
	public void displayCircle(ImageView iv, String url) {
		displayCircle(iv, url, -1);
	}

	/**
	 * 圆图片-url
	 * 
	 * @param iv
	 * @param url
	 * @param defaultResId
	 */
	public void displayCircle(ImageView iv, String url, int defaultResId) {
		if (iv == null)
			return;
		DrawableRequestBuilder<String> builder = glideRequest.load(url).transform(new GlideCircleTransform(mCtx));
		if (defaultResId != -1)
			builder.placeholder(defaultResId);
		if (isDiskCache)
			builder.diskCacheStrategy(DiskCacheStrategy.ALL);
		builder.into(iv);
	}

	/**
	 * 圆图片-Uri
	 * 
	 * @param iv
	 * @param uri
	 */
	public void displayCircle(ImageView iv, Uri uri) {
		displayCircle(iv, uri, -1);
	}

	/**
	 * 圆图片-Uri
	 * 
	 * @param iv
	 * @param uri
	 * @param defaultResId
	 */
	public void displayCircle(ImageView iv, Uri uri, int defaultResId) {
		if (iv == null)
			return;
		DrawableRequestBuilder<Uri> builder = glideRequest.load(uri).transform(new GlideCircleTransform(mCtx));
		if (defaultResId != -1)
			builder.placeholder(defaultResId);
		if (isDiskCache)
			builder.diskCacheStrategy(DiskCacheStrategy.ALL);
		builder.into(iv);
	}

	/**
	 * 圆图片-File
	 * 
	 * @param iv
	 * @param imgFile
	 */
	public void displayCircle(ImageView iv, File imgFile) {
		displayCircle(iv, imgFile, -1);
	}

	/**
	 * 圆图片-File
	 * 
	 * @param iv
	 * @param imgFile
	 * @param defaultResId
	 */
	public void displayCircle(ImageView iv, File imgFile, int defaultResId) {
		if (iv == null)
			return;
		DrawableRequestBuilder<File> builder = glideRequest.load(imgFile).transform(new GlideCircleTransform(mCtx));
		if (defaultResId != -1)
			builder.placeholder(defaultResId);
		if (isDiskCache)
			builder.diskCacheStrategy(DiskCacheStrategy.ALL);
		builder.into(iv);
	}

	/** 下载图片获取地址 */
	public String downloadPath(String url) {
		try {
			File file = glideRequest.load(url).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
			return file.getPath();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			return "";
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			return "";
		}
	}

	/** 下载图片 */
	public File downloadFile(String url) {
		try {
			File file = glideRequest.load(url).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
			return file;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			return null;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	/**
	 * 清除图片缓存
	 */
	public void Clear() {
		Glide.get(mCtx).clearDiskCache();
		Glide.get(mCtx).clearMemory();
	}

	/**
	 * 圆图片
	 */
	public class GlideCircleTransform extends BitmapTransformation {
		public GlideCircleTransform(Context context) {
			super(context);
		}

		@Override
		protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
			return circleCrop(pool, toTransform);
		}

		private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
			if (source == null)
				return null;

			int size = Math.min(source.getWidth(), source.getHeight());
			int x = (source.getWidth() - size) / 2;
			int y = (source.getHeight() - size) / 2;

			// TODO this could be acquired from the pool too
			Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

			Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
			if (result == null) {
				result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
			}

			Canvas canvas = new Canvas(result);
			Paint paint = new Paint();
			paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
			paint.setAntiAlias(true);
			float r = size / 2f;
			canvas.drawCircle(r, r, r, paint);
			return result;
		}

		@Override
		public String getId() {
			return getClass().getName();
		}
	}

	/**
	 * 圆角头像
	 */
	public class GlideRoundTransform extends BitmapTransformation {

		private float radius = 0f;

		public GlideRoundTransform(Context context) {
			this(context, 4);
		}

		public GlideRoundTransform(Context context, int dp) {
			super(context);
			this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
		}

		@Override
		protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
			return roundCrop(pool, toTransform);
		}

		private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
			if (source == null)
				return null;

			Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
			if (result == null) {
				result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
			}

			Canvas canvas = new Canvas(result);
			Paint paint = new Paint();
			paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
			paint.setAntiAlias(true);
			RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
			canvas.drawRoundRect(rectF, radius, radius, paint);
			return result;
		}

		@Override
		public String getId() {
			return getClass().getName() + Math.round(radius);
		}
	}

    /**
     * 圆角效果的Transform
     * Created by Raye on 2016/5/10.
     */
    public class CornersTransform  extends BitmapTransformation {
        private float radius;
        public CornersTransform(Context context) {
            super(context);
            radius = 10;
        }

        public CornersTransform(Context context, float radius){
            super(context);
            this.radius = radius;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return cornersCrop(pool,toTransform);
        }
        private Bitmap cornersCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName();
        }
    }

}
