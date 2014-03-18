package com.android.tetristakethree;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends Activity implements OnClickListener{
	GameSurfaceView gameSurfaceView;
	FrameLayout gameFrame;
	RelativeLayout GameButtons;
	Button left;
	Button right;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		gameSurfaceView = new GameSurfaceView(this);
		
		gameFrame = new FrameLayout(this);
		GameButtons = new RelativeLayout(this);
		
		left = new Button(this);
		left.setText("Left");
		left.setId(111);
		
		right = new Button(this);
		right.setText("Right");
		right.setId(222);
		
		RelativeLayout.LayoutParams firstButton = new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		RelativeLayout.LayoutParams button = new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		GameButtons.setLayoutParams(button);
		GameButtons.addView(left);
		GameButtons.addView(right);
		
		firstButton.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		firstButton.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		
		button.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		button.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		
		left.setLayoutParams(button);
		right.setLayoutParams(firstButton);
		
		gameFrame.addView(gameSurfaceView);
		gameFrame.addView(GameButtons);
		setContentView(gameFrame);
		
		View leftButtonListener = findViewById(111);
		leftButtonListener.setOnClickListener(this);
		
		View rightButtonListener = findViewById(222);
		rightButtonListener.setOnClickListener(this);
		Log.i("this is the end", "of onCreate!");
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

	@Override
	public void onClick(View v) {
		Log.i("button is", "pressed");
		if (v == left) {
			gameSurfaceView.game.userPressedLeft();
			Log.i("button is", "left");
		} else if (v == right) {
			gameSurfaceView.game.userPressedRight();
			Log.i("button is", "right");
		}
	}
}
