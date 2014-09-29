package com.veridian.main.math;

public class Vector2f {
	public float x, y;
	
	public Vector2f() {
		this(0, 0);
	}
	
	public Vector2f(Vector2f v) {
		this(v.x, v.y);
	}
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
//	public Vector2f rotate(float angle) {
//		x = (float) Math.cos(Math.toRadians(angle));
//		y = (float) Math.sin(Math.toRadians(angle));
//		return this;
//	}
}
