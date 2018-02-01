package com.all.app.ui.activity;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.ImageView;

/**
 * �Զ����GifDecoderView�̳���ImageView����չʾGifͼƬ
 * 
 * @author liaoningyutu
 * 
 */
public class GifDecoderView extends ImageView {

	private boolean isAni;
	private GifPlayer gp;
	private Bitmap bm;
	private Handler handler = new Handler();
	private Runnable playFrame = new Runnable() {

		@Override
		public void run() {
			if (null != bm && !bm.isRecycled()) {
				GifDecoderView.this.setImageBitmap(bm);
			}
		}
	};

	public GifDecoderView(Context context, InputStream is) {
		super(context);
		playGif(is);
	}

	private void playGif(InputStream is) {

		gp = new GifPlayer();
		gp.read(is);
		isAni = true;

		new Thread() {
			public void run() {
				final int frameCount = gp.getFrameCount();
				final int loopCount = gp.getLoopCount();
				int repetitionCounter = 0;
				do {
					for (int i = 0; i < frameCount; i++) {
						bm = gp.getFrame(i);
						int t = gp.getDelay(i);
						handler.post(playFrame);
						try {
							Thread.sleep(t);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (0 != loopCount) {
							repetitionCounter++;
						}

					}
				} while (true);

			};
		}.start();
	}

}
