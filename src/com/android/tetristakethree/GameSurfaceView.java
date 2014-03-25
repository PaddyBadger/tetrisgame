package com.android.tetristakethree;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView implements Runnable {
	SurfaceHolder surfaceHolder;
	volatile boolean running = false;
	Thread thread = null;
	private Paint backgroundPaint;
	public GameState game;
	int w, h;
	int y = 0;
	int x = 0;
	int squareSize = 95;
	String compareDeleteCoordString;
	List<Coordinate> coords;

	public GameSurfaceView(Context context) {
		super(context);
		surfaceHolder = getHolder();
		
		backgroundPaint = new Paint();
		backgroundPaint.setColor(Color.WHITE);
		
		displayHeight();
		displayWidth();
		
		game = new GameState(this);
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
	
	public int getColor(Piece id) {
		switch(id) {
		case T: return Color.argb(255, 245, 10, 170);
		case L: return Color.argb(255, 67, 186, 85);
		case Z: return Color.argb(255, 60, 51, 242);
		case S: return Color.argb(255, 245, 10, 10);
		case LL: return Color.argb(255, 231, 250, 27);
		default: return Color.argb(255, 60, 51, 242);
		}	
	}
	
	public final int displayHeight() {
		DisplayMetrics d = this.getResources().getDisplayMetrics();
		int screenHeight = d.heightPixels;
		return screenHeight;
	}
	public final int displayWidth() {
		DisplayMetrics d = this.getResources().getDisplayMetrics();
		int screenWidth = d.widthPixels;
		return screenWidth;
	}
	
	private int h(int dH) {
		int h = dH / 22;
		return h;
	}
	
	private int w(int dW) {
		int w = dW / 13;
		return w;
	}
	
	@Override
	public void run() {
		while (running) {
			if (surfaceHolder.getSurface().isValid()) {
				Canvas canvas = surfaceHolder.lockCanvas();
				
				int w = canvas.getWidth();
				int h = canvas.getHeight();
				canvas.drawRect(0,0,w,h, backgroundPaint);
				
				List<Shape> gameShapes = game.getShapes();
				
				for (int i = 0; i < gameShapes.size(); i++ ) {
					Shape shape = gameShapes.get(i);
					
					Paint paint = new Paint();
					paint.setColor(getColor(shape.id));
					
					List<Coordinate> coordinatesToDelete = game.deleteThisRow();
					coords = shape.shapeCoordinates();
						
					for (int k = 0; k < coordinatesToDelete.size(); k++) {
						int[] compareCoordToDelete = {coordinatesToDelete.get(k).x, coordinatesToDelete.get(k).y};
						compareDeleteCoordString = Arrays.toString(compareCoordToDelete);
						
						for (int j = 0; j < coords.size(); j++) {
	
							int[] compareCoord = {coords.get(j).x, coords.get(j).y};
							String compareCoordString = Arrays.toString(compareCoord);
							
							if(compareCoordString.equals(compareDeleteCoordString)) {
								coords.remove(j);
							}
						}
					}
					
					for (int k = 0; k < coords.size(); k++) {
						Rect rect = new Rect(coords.get(k).x, coords.get(k).y, coords.get(k).x + squareSize, coords.get(k).y + squareSize);
						canvas.drawRect(rect, paint);
					}
				}
				
				surfaceHolder.unlockCanvasAndPost(canvas);
				try {
					Thread.sleep(1000);
					game.fallingShape.fall();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
