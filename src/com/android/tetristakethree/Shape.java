package com.android.tetristakethree;

import java.util.ArrayList;
import java.util.List;

public class Shape {
	Coordinate a,b,c,d;
	public int id;
	GameSurfaceView display;
	Boolean isFalling = true;
	
	private Shape(Coordinate a, Coordinate b, Coordinate c, Coordinate d, int id, GameSurfaceView display) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.id = id;
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
				         new Coordinate(0,-100),
				         new Coordinate(100,-100),
				         new Coordinate(200,-100),
				         1,
				         display);
	}
	
	public static Shape l(GameSurfaceView display) {
		return new Shape(new Coordinate(300,-400),
		         		 new Coordinate(300,-300),
		         		 new Coordinate(300,-200),
		         		 new Coordinate(300,-100),
						 2,
						 display);
	}
	
	public static Shape z(GameSurfaceView display) {
		return new Shape(new Coordinate(200,-200),
		         		 new Coordinate(300,-200),
		         		new Coordinate(300,-100),
		         		 new Coordinate(400,-100),
		         		 3,
		         		 display);
	}
	
	public static Shape s(GameSurfaceView display) {
		return new Shape(new Coordinate(400,-200),
		         		 new Coordinate(400,-100),
		         		 new Coordinate(500,-200),
		         		 new Coordinate(500,-100),
		         		 4,
		         		 display);
	}
	
	public static Shape ll(GameSurfaceView display) {
		return new Shape(new Coordinate(500,-300),
		         		 new Coordinate(500,-200),
		         		 new Coordinate(500,-100),
		         		 new Coordinate(600,-100),
		         		 5,
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
		} else { 
			isFalling = false;
			
		}
	}
	
	public void rotate() {
		this.a.y = 
	}
	
	public void left() {
		if (a.x > 0 && b.x > 0 && c.x > 0 && d.x > 0 && a.y < dH() && b.y < dH() && c.y < dH() && d.y < dH()) {
			this.a.x -= 100;
			this.b.x -= 100;
			this.c.x -= 100;
			this.d.x -= 100;
		}
	}
	
	public void right() {
		if (a.x < dW() && b.x < dW() && c.x < dW() && d.x < dW() && a.y < dH() && b.y < dH() && c.y < dH() && d.y < dH()) {
			this.a.x += 100;
			this.b.x += 100;
			this.c.x += 100;
			this.d.x += 100;
		}
	}
}
	

