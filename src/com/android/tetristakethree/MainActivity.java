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
	FrameLayout game;
	RelativeLayout GameButtons;
	Button left;
	Button right;
	Button down;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		gameSurfaceView = new GameSurfaceView(this);
		game = new FrameLayout(this);
		GameButtons = new RelativeLayout(this);
		
		left = new Button(this);
		left.setText("Left");
		left.setId(111);
		
		right = new Button(this);
		right.setText("Right");
		right.setId(222);
		
		down = new Button(this);
		down.setText("Rotate");
		down.setId(333);

		
		RelativeLayout.LayoutParams rl = new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		RelativeLayout.LayoutParams leftButton = new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		RelativeLayout.LayoutParams rightButton = new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		RelativeLayout.LayoutParams downButton = new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		GameButtons.setLayoutParams(rl);
		GameButtons.addView(left);
		GameButtons.addView(right);
		GameButtons.addView(down);
		
		leftButton.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		leftButton.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		
		rightButton.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		rightButton.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		
		downButton.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		downButton.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		
		left.setLayoutParams(leftButton);
		right.setLayoutParams(rightButton);
		down.setLayoutParams(downButton);
		
		game.addView(gameSurfaceView);
		game.addView(GameButtons);
		setContentView(game);
		
		View leftButtonListener = findViewById(111);
		leftButtonListener.setOnClickListener(this);
		
		View rightButtonListener = findViewById(222);
		rightButtonListener.setOnClickListener(this);
		
		View downButtonListener = findViewById(333);
		downButtonListener.setOnClickListener(this);
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
		if (v == left) {
		   gameSurfaceView.game.userPressedLeft();
		} else if (v == right) {
			gameSurfaceView.game.userPressedRight();
		} else if (v == down) {
			gameSurfaceView.game.userPressedRotate();
		}
	}
}
