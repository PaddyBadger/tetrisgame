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
	List<int[]> deleteMe;
	
	
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
		isThisRowFull(getCoordinates());
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
		isThisRowFull(getCoordinates());
	}
	
	public void isThisRowFull(List<int[]> getCoordinates) {
		List<int[]> nine = new ArrayList<int[]>();
		List<int[]> eight = new ArrayList<int[]>();
		List<int[]> seven = new ArrayList<int[]>();
		List<int[]> six = new ArrayList<int[]>();
		List<int[]> five = new ArrayList<int[]>();
		 for (int i = 0; i < getCoordinates().size(); i++) {
			int[] coordPair = getCoordinates.get(i);	 	
				switch (coordPair[1]) {
				case 900: nine.add(coordPair);
						  break;
				case 800: eight.add(coordPair);
						  break;
				case 700: seven.add(coordPair);
						  break;
				case 600: six.add(coordPair);
						  break;
				case 500: five.add(coordPair);
						  break;
				default:
				}
				
		}
		deleteMe = new ArrayList<int[]>();
		
		if (nine.size() == 7) { 
			for (int i = 0; i < nine.size(); i++) {
				int[] addToDeleteMe = nine.get(i);
				Log.i("nine 1 " + nine.get(i)[0], "nine 2 "  + nine.get(i)[1]);
				deleteMe.add(addToDeleteMe);
			}
		}
		
		if (eight.size() == 7) {
			for (int i = 0; i < eight.size(); i++) {
				int[] addToDeleteMe = eight.get(i);
				Log.i("eight 1 " + eight.get(i)[0], "eight 2 "  + eight.get(i)[1]);
				deleteMe.add(addToDeleteMe);
			}
		}
		if (seven.size() == 7) {
			for (int i = 0; i < seven.size(); i++) {
				int[] addToDeleteMe = seven.get(i);
				deleteMe.add(addToDeleteMe);
			}
		}
		if (six.size() == 7) {
			for (int i = 0; i < six.size(); i++) {
				int[] addToDeleteMe = six.get(i);
				deleteMe.add(addToDeleteMe);
			}
		}
		if (five.size() == 7) {
			for (int i = 0; i < five.size(); i++) {
				int[] addToDeleteMe = five.get(i);
				deleteMe.add(addToDeleteMe);
			}
		}
	}
	
	public List<Coordinate> deleteThisRow() {
		List<Coordinate> coordsToDelete = new ArrayList<Coordinate>();
		for (int i = 0; i < deleteMe.size(); i++) {
			int x = deleteMe.get(i)[0];
			int y = deleteMe.get(i)[1];
			Coordinate coord = new Coordinate(x,y);		
			coordsToDelete.add(coord);
		}
		return coordsToDelete;
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
