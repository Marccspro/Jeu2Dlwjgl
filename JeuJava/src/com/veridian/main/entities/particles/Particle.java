package com.veridian.main.entities.particles;

import java.util.Random;

import com.veridian.main.graphics.Color;
import com.veridian.main.graphics.Renderer;
import com.veridian.main.graphics.Texture;

public class Particle {
	
	public boolean removed = false;
	
	float x, y;
	double rx, ry;
	Random rand = new Random();
	
	public Particle() {
		
	}
	
	public Particle(Particle p, int x, int y) {
		this.x = x;
		this.y = y;
		rx = rand.nextGaussian();
		ry = rand.nextGaussian();
	}
	
	public Particle(Color color, int size, float speed, int lifeTime, int[] randomness) {}
	public Particle(Texture texture, int size, float speed, int lifeTime, int[] randomness) {}
	
	public void update() {
//		x += rx;
//		y += ry;
	}
	
	public void render() {
		Renderer.quadData(x, y, 2, 2, Color.WHITE, 0, 0);
	}
}
