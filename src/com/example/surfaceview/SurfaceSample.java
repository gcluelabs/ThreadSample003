package com.example.surfaceview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceSample extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MySurfaceView mSurfaceView = new MySurfaceView(this);
		setContentView(mSurfaceView);
	}
}

class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable {

	public MySurfaceView(Context context) {
		super(context);
		Log.i("SURFACE","MySurfaceView()");
		
		// Callbackを登録する
		getHolder().addCallback(this);
		
		// Threadを起動する
		Thread mThread = new Thread(this);
		mThread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		Log.i("SURFACE","surfaceChanged()");
	}


	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.i("SURFACE","surfaceDestroyed()");
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.i("SURFACE","surfaceCreaded()");
		
		// Canvasを取得する
		Canvas canvas = holder.lockCanvas();

		// 背景を青にする
		canvas.drawColor(Color.BLUE);
		
		// 描画するための線の色を設定
		Paint mainPaint = new Paint();
		mainPaint.setStyle(Paint.Style.FILL);
		mainPaint.setARGB(255, 255, 255, 100);
		
		canvas.drawLine(0, 0, 50, 50, mainPaint);

		// 画面に描画をする
		holder.unlockCanvasAndPost(canvas);
	}

	@Override
	public void run() {
		Log.i("SURFACE","run()");
		while(true){
			Log.i("SURFACE","loop");
			try {
				Thread.sleep(100);
			} catch (Exception e) {}
		}
	}
}