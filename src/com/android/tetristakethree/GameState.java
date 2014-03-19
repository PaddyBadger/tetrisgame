package com.android.tetristakethree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameState {
	List<Shape> shapes = new ArrayList<Shape>();
	Shape fallingShape;
	int ground;
	GameSurfaceView surface;
	
	
	public GameState(GameSurfaceView surface) {
		Shape tShape = Shape.t(surface);
		shapes.add(tShape);
		shapes.add(Shape.l(surface));
		shapes.add(Shape.ll(surface));
		shapes.add(Shape.z(surface));
		shapes.add(Shape.s(surface));	
		shapes.add(Shape.t(surface));
		shapes.add(Shape.l(surface));
		shapes.add(Shape.ll(surface));
		shapes.add(Shape.z(surface));
		shapes.add(Shape.s(surface));	
		shapes.add(Shape.t(surface));
		shapes.add(Shape.l(surface));
		shapes.add(Shape.ll(surface));
		shapes.add(Shape.z(surface));
		shapes.add(Shape.s(surface));
		
		fallingShape = tShape;
		
		this.surface = surface;
	}	
	
	public int randomNumber() {
		Random rand = new Random();
		int chosenShape = rand.nextInt(shapes.size());
		return chosenShape;
	}
	
	public List<Shape> getShapes() {
		if (!fallingShape.isFalling) {
			fallingShape = shapes.get(randomNumber());	
			fallingShape.isFalling = true;
		} 
		return shapes;
	}
	
	public void compareCoordinates(List<int[]> getFalling, List<int[]> getCoordinates) {
		for (int[] shapeCoords : getCoordinates ) {
			String coordString = Arrays.toString(shapeCoords);
			for (int[] fallingCoords : getFalling ) {
				String fallingString = Arrays.toString(fallingCoords);
				if (coordString.contains(fallingString))  {
					fallingShape.isFalling = false;
				}
			}	
		}
	}
	
	public void userPressedLeft() {
		fallingShape.left();
	}
	
	public void userPressedRight() {
		fallingShape.right();
	}
	
	public void userPressedDown() {
		fallingShape.fall();
	}
	
	
}
