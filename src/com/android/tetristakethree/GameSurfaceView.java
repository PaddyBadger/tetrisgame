package com.android.tetristakethree;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView implements Runnable {
	SurfaceHolder surfaceHolder;
	volatile boolean running = false;
	Random random;
	Thread thread = null;
	private Paint paint;
	private Paint backgroundPaint;
	GameObjects gameObject;
	GamePhysics gamePhysics;
	int w, h;
	int y = 0;
	int x = 0;
	
	private int randomColor() {
		int r = random.nextInt(256);
		int g = random.nextInt(256);
		int b = random.nextInt(256);
		
		return Color.argb(255, r, g, b);
}

	public GameSurfaceView(Context context) {
		super(context);
		surfaceHolder = getHolder();
		random = new Random();
		
		backgroundPaint = new Paint();
		backgroundPaint.setColor(Color.WHITE);
		
		paint = new Paint();
		paint.setColor(randomColor());
		
		gameObject = new GameObjects();
		}
	
	public void onResumeGameSurfaceView() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void onPauseGameSurfaceView() {
		boolean retry = true;
		running = false;
		while(retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		while (running) {
			if (surfaceHolder.getSurface().isValid()) {
				Canvas canvas = surfaceHolder.lockCanvas();
				
				this.gameObject.update();
				this.gameObject.render(canvas);

			surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
		
	}
}
