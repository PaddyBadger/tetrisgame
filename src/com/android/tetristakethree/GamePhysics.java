package com.android.tetristakethree;

public class GamePhysics {
	public static final int DIRECTION_DOWN = 1;
	public static final int DIRECTION_LEFT = -1;
	public static final int DIRECTION_RIGHT = 1;
	
	private float yv = 1;
	private float xv = 0;
	
	private int xDirection = DIRECTION_RIGHT;
	private int yDirection = DIRECTION_DOWN;
	
	public GamePhysics() {
		this.xv = 1;
		this.yv = 1;
	}
	
	public GamePhysics(float xv, float yv) {
		this.xv = xv;
		this.yv = yv;
	}
	
	public float getYv() {
		return yv;
	}
	
	public void setYv(float yv) {
		this.yv = yv;
	}
	
	public float getXv() {
		return xv;
	}
	
	public void setXv(float xv) {
		this.xv = xv;
	}
	
	public int getxDirection() {
		return xDirection;
	}
	
	public void setxDirection(int xDirection) {
		this.xDirection = xDirection;
	}
	
	public int getyDirection() {
		return yDirection;
	}
	
	public void setyDirection(int yDirection) {
		this.yDirection = yDirection;
	}
}
