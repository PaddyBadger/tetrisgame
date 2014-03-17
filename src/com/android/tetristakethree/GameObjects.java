package com.android.tetristakethree;

import android.graphics.Rect;

public class GameObjects {
	GameSurfaceView surfaceView;
	
	public void scale() {
		int w = canvas.getWidth();
		int h = canvas.getHeight();
		
		canvas.drawRect(0, 0, w, h, backgroundPaint);
		
//		gameObject.rectangle();
		
		
		Rect rect = new Rect();
		rect.set(x, y, w/12, h/22);
		canvas.drawRect(rect, paint);

	}
	
	
}
