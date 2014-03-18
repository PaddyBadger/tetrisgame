package com.android.tetristakethree;

import java.util.List;
import java.util.Random;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView implements Runnable {
	SurfaceHolder surfaceHolder;
	volatile boolean running = false;
	Random random;
	Thread thread = null;
	private Paint backgroundPaint;
	public GameState game;
	int w, h;
	int y = 0;
	int x = 0;
	int squareSize = 100;

	public GameSurfaceView(Context context) {
		super(context);
		surfaceHolder = getHolder();
		random = new Random();
		
		backgroundPaint = new Paint();
		backgroundPaint.setColor(Color.WHITE);
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
	
	public int getColor(int id) {
		switch(id) {
		case 1: return Color.argb(255, 245, 10, 170);
		case 2: return Color.argb(255, 67, 186, 85);
		case 3: return Color.argb(255, 60, 51, 242);
		case 4: return Color.argb(255, 245, 10, 10);
		case 5: return Color.argb(255, 231, 250, 27);
		default: return Color.argb(255, 60, 51, 242);
		}	
	}
	
	@Override
	public void run() {
		while (running) {
			if (surfaceHolder.getSurface().isValid()) {
				Canvas canvas = surfaceHolder.lockCanvas();
				
				int w = canvas.getWidth();
				int h = canvas.getHeight();
				canvas.drawRect(0,0,w,h, backgroundPaint);
				
				game = new GameState();
				List<Shape> gameShapes = game.getShapes();
				
				for (int i = 0; i < gameShapes.size(); i++ ) {
					Shape shape = gameShapes.get(i);
					List<Coordinate> coords = shape.shapeCoordinates();
					Paint paint = new Paint();
					paint.setColor(getColor(shape.id));
					
					for (int j = 0; j < coords.size(); j++) {
						Rect rect = new Rect(coords.get(j).x, coords.get(j).y, coords.get(j).x + squareSize, coords.get(j).y + squareSize);
						canvas.drawRect(rect, paint);
					}
				}

				surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
	}
}
