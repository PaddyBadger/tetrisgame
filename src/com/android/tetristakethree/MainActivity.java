package com.android.tetristakethree;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
	GameSurfaceView gameSurfaceView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		gameSurfaceView = new GameSurfaceView(this);
		setContentView(gameSurfaceView);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		gameSurfaceView.onResumeGameSurfaceView();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		gameSurfaceView.onPauseGameSurfaceView();
	}
}
