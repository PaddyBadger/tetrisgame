package com.android.tetristakethree;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class Shape {
	Coordinate a,b,c,d;
	public Piece id;
	public int down, right, rotate;
	GameSurfaceView display;
	Boolean isFalling = true;
	
	private Shape(Coordinate a, Coordinate b, Coordinate c, Coordinate d, Piece id, GameSurfaceView display) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.id = id;
		this.down = -2;
		this.right = 0;
		this.display = display;
	}
	
	private int dH() {
		int dh = display.displayHeight()-300;
		return dh;
	}
	
	
	private int dW() {
		int dw = display.displayWidth()-200;
		return dw;
	}
	
	
	public static Shape t(GameSurfaceView display) {
		return new Shape(new Coordinate(100,-200),
						 new Coordinate(200,-100),
						 new Coordinate(100,-100),
						 new Coordinate(0,-100),
				         Piece.T,
				         display);
	}
	
	public static Shape l(GameSurfaceView display) {
		return new Shape(new Coordinate(300,-400),
		         		 new Coordinate(300,-300),
		         		 new Coordinate(300,-200),
		         		 new Coordinate(300,-100),
						 Piece.L,
						 display);
	}
	
	public static Shape z(GameSurfaceView display) {
		return new Shape(new Coordinate(0,-200),
		         		 new Coordinate(100,-200),
		         		new Coordinate(100,-100),
		         		 new Coordinate(200,-100),
		         		 Piece.Z,
		         		 display);
	}
	
	public static Shape s(GameSurfaceView display) {
		return new Shape(new Coordinate(400,-200),
		         		 new Coordinate(400,-100),
		         		 new Coordinate(500,-200),
		         		 new Coordinate(500,-100),
		         		 Piece.S,
		         		 display);
	}
	
	public static Shape ll(GameSurfaceView display) {
		return new Shape(new Coordinate(0,-300),
		         		 new Coordinate(0,-200),
		         		 new Coordinate(0,-100),
		         		 new Coordinate(100,-100),
		         		 Piece.LL,
		         		 display);
	}
	
	public List<Coordinate> shapeCoordinates() {
		List<Coordinate> coords = new ArrayList<Coordinate>();
		coords.add(a);
		coords.add(b);
		coords.add(c);
		coords.add(d);
		return coords;
	}
	
	public void fall() {
		 if (a.y < dH() && b.y < dH() && c.y < dH() && d.y < dH()) {
			this.a.y += 100;
			this.b.y += 100;
			this.c.y += 100;
			this.d.y += 100;
			isFalling = true;
			down++;
		} else { 
			isFalling = false;
			
		}
	}
	
	public void rotate(Piece id) {

			switch(id) {
				case T:
				case Z:
				case LL: for (int i = 0; i < shapeCoordinates().size(); i++) {
							int tempY = shapeCoordinates().get(i).y-(down*100);
							int tempX = shapeCoordinates().get(i).x-(right*100);
							
							shapeCoordinates().get(i).x = (200-(tempY))+(right*100);
							shapeCoordinates().get(i).y = tempX+(down*100);
						} break;
				case L: rotate++;
						if ((rotate&1) == 0) {
							int y = shapeCoordinates().get(1).y;
							int x = shapeCoordinates().get(1).x;
							
							for (int i = 0; i < shapeCoordinates().size(); i++) {
								shapeCoordinates().get(i).x = x;
								shapeCoordinates().get(i).y = y+(i*100);
							}
						} else {
							int y = shapeCoordinates().get(1).y;
							int x = shapeCoordinates().get(1).x;
							
							for (int i = 0; i < shapeCoordinates().size(); i++) {
								shapeCoordinates().get(i).y = y;
								shapeCoordinates().get(i).x = x+(i*100);
							}
						}		
				default:				
			}
	}
	
	public void left() {
		if (a.x > 0 && b.x > 0 && c.x > 0 && d.x > 0 && a.y < dH() && b.y < dH() && c.y < dH() && d.y < dH()) {
			this.a.x -= 100;
			this.b.x -= 100;
			this.c.x -= 100;
			this.d.x -= 100;
			right--;
		}
	}
	
	public void right() {
		if (a.x < dW() && b.x < dW() && c.x < dW() && d.x < dW() && a.y < dH() && b.y < dH() && c.y < dH() && d.y < dH()) {
			this.a.x += 100;
			this.b.x += 100;
			this.c.x += 100;
			this.d.x += 100;
			right ++;
		}
	}
}
	

