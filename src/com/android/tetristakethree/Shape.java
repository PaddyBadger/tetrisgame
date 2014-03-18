package com.android.tetristakethree;

import java.util.ArrayList;
import java.util.List;

public class Shape {
	Coordinate a,b,c,d;
	public int id;
	
	private Shape(Coordinate a, Coordinate b, Coordinate c, Coordinate d, int id) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.id = id;
	}
	
	public static Shape t() {
		return new Shape(new Coordinate(100,0),
				         new Coordinate(0,100),
				         new Coordinate(100,100),
				         new Coordinate(100,200),
				         1);
	}
	
	public static Shape l() {
		return new Shape(new Coordinate(0,0),
		         		 new Coordinate(0,100),
		         		 new Coordinate(0,200),
		         		 new Coordinate(0,300),
						 2);
	}
	
	public static Shape z() {
		return new Shape(new Coordinate(0,0),
		         		 new Coordinate(0,100),
		         		new Coordinate(100,100),
		         		 new Coordinate(200,100),
		         		 3);
	}
	
	public static Shape s() {
		return new Shape(new Coordinate(0,0),
		         		 new Coordinate(0,100),
		         		 new Coordinate(100,0),
		         		 new Coordinate(100,100),
		         		 4);
	}
	
	public static Shape ll() {
		return new Shape(new Coordinate(0,0),
		         		 new Coordinate(0,100),
		         		 new Coordinate(0,200),
		         		 new Coordinate(100,200),
		         		 5);
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
		this.a.y += 100;
		this.b.y += 100;
		this.c.y += 100;
		this.d.y += 100;
	}
	
	public void left() {
		this.a.x -= 100;
		this.b.x -= 100;
		this.c.x -= 100;
		this.d.x -= 100;
	}
	
	public void right() {
		this.a.x += 100;
		this.b.x += 100;
		this.c.x += 100;
		this.d.x += 100;
	}
}
	

