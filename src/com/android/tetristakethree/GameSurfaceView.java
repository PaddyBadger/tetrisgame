package com.android.tetristakethree;

import java.util.ArrayList;
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
	ArrayList<GameObjects> gameObjects;
	GamePhysics gamePhysics;
	int w, h;
	int y = 0;
	int x = 0;
	int squareSize = 100;
	
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
		
		gameObjects = new ArrayList<GameObjects>();
		gameObjects.add(new GameObjects());
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
				
				for (GameObjects gameObject : this.gameObjects) {
					Rect rect = new Rect(gameObject.x, gameObject.y, squareSize, squareSize);
					canvas.drawRect(rect, backgroundPaint);
				}

				surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
	}
}
