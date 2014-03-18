package com.android.tetristakethree;

import java.util.ArrayList;
import java.util.List;

public class GameState {
	List<Shape> shapes = new ArrayList<Shape>();
	Shape fallingShape;
	int ground;
	
	
	public GameState() {
		Shape tShape = Shape.t();
		shapes.add(tShape);
		
		Shape lShape = Shape.l();
		shapes.add(lShape);
		
		Shape llShape = Shape.ll();
		shapes.add(llShape);
		
		Shape zShape = Shape.z();
		shapes.add(zShape);
		
		Shape sShape = Shape.s();
		shapes.add(sShape);	
		
		tShape = Shape.t();
		shapes.add(tShape);
		
		lShape = Shape.l();
		shapes.add(lShape);
		
		llShape = Shape.ll();
		shapes.add(llShape);
	
		shapes.add(Shape.z());
		
		sShape = Shape.s();
		shapes.add(sShape);	
		
		tShape = Shape.t();
		shapes.add(tShape);
		
		lShape = Shape.l();
		shapes.add(lShape);
		
		llShape = Shape.ll();
		shapes.add(llShape);
		
		zShape = Shape.z();
		shapes.add(zShape);
		
		sShape = Shape.s();
		shapes.add(sShape);	
		
		fallingShape = zShape;
		fallingShape.right();
		fallingShape.right();
		fallingShape.right();
		fallingShape.right();
		fallingShape.right();
		
	}	
	
	public List<Shape> getShapes() {
		return shapes;
	}
	
	public void userPressedLeft() {
		fallingShape.left();
	}
	
	public void userPressedRight() {
		fallingShape.right();
	}
	

}
