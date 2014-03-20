package com.android.tetristakethree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import android.util.Log;

public class GameState {
	List<Shape> shapes = new ArrayList<Shape>();
	Shape fallingShape;
	int ground;
	GameSurfaceView surface;
	
	
	public GameState(GameSurfaceView surface) {
		Shape tShape = Shape.l(surface);
		shapes.add(tShape);
		
		fallingShape = tShape;
		this.surface = surface;
	}	
	
	public Shape makeNewShape(int randomNumber, GameSurfaceView surface) {
		switch(randomNumber) {
		case 0: return Shape.t(surface);		
		case 1: return Shape.l(surface);
		case 2: return Shape.z(surface);
		case 3: return Shape.s(surface);
		case 4: return Shape.ll(surface);
		default: return Shape.ll(surface);	
		}
	}
	
	public int randomNumber() {
		Random rand = new Random();
		int newShape = rand.nextInt(5);
		return newShape;
	}
	
	public List<Shape> getShapes() {
		compareCoordinates(getFalling(), getCoordinates());
		if (!fallingShape.isFalling) {
			int shapeNumber = randomNumber();
			Shape addShape = makeNewShape(shapeNumber, surface);
			shapes.add(addShape);
			fallingShape = shapes.get(shapes.size() - 1);
			fallingShape.isFalling = true;
		} 
		return shapes;
	}
	
	public List<int[]> getFalling() {
		List<int[]> getFalling = new ArrayList<int[]>();
		int[] aPair = {fallingShape.a.x, fallingShape.a.y+100};
		int[] bPair = {fallingShape.b.x, fallingShape.b.y+100};
		int[] cPair = {fallingShape.c.x, fallingShape.c.y+100};
		int[] dPair = {fallingShape.d.x, fallingShape.d.y+100};
		getFalling.add(aPair);
		getFalling.add(bPair);
		getFalling.add(cPair);
		getFalling.add(dPair);
	
		return getFalling;
	}
	
	public List<int[]> getCoordinates() {
		List<int[]> coordinateList = new ArrayList<int[]>();
		
		for (int i = 0; i < shapes.size(); i++ ) {
			if (shapes.get(i) != fallingShape) {
				Shape shape = shapes.get(i);
				List<Coordinate> coords = shape.shapeCoordinates();
				
				for (int j = 0; j < coords.size(); j++) {
					int[] pair = {coords.get(j).x, coords.get(j).y};
					coordinateList.add(pair);
				}
			}
		}
		return coordinateList;
	}
	
	public void compareCoordinates(List<int[]> getFalling, List<int[]> getCoordinates) {
		for (int[] shapeCoords : getCoordinates ) {
			String coordString = Arrays.toString(shapeCoords);
			for (int[] fallingCoords : getFalling) {
				String fallingString = Arrays.toString(fallingCoords);
				if (coordString.equals(fallingString)) {
					fallingShape.isFalling = false;				}
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
	
	public void userPressedRotate() {
		fallingShape.rotate(fallingShape.id);
	}
}
