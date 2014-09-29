package com.veridian.main.entities.particles;

import java.util.Random;

import com.veridian.main.graphics.Renderer;
import com.veridian.main.graphics.Texture;
import com.veridian.main.math.Vector2f;

public class Particle {
	
	public boolean removed = false;
	
	float x, y;
	double rx, ry;
	Random rand = new Random();
	
	private float[] color;
	private Vector2f direction;
	private int size;
	private float speed;
	private int lifeTime;
	
	public Particle(float[] color, Vector2f direction, int size, float speed, int lifeTime) {
		this.direction = direction;
		this.size = size;
		this.color = color;
		this.speed = speed;
		this.lifeTime = lifeTime;
	}
	
	public Particle(Particle p, int x, int y) {
		this.x = x;
		this.y = y;
		
		this.direction = p.direction;
		this.size = p.size;
		this.color = p.color;
		this.speed = p.speed;
		this.lifeTime = p.lifeTime;
		
		rx = rand.nextGaussian();
		ry = rand.nextGaussian();
	}
	
	public Particle(Texture texture, int size, float speed, int lifeTime, int[] randomness) {}
	
	int time = 0;
	public void update() {
		time++;
		if (time > lifeTime) {
			removed = true;
			return;
		}
		x += (rx + direction.x) * speed;
		y += (ry + direction.y) * speed;
	}
	
	public void render() {
		Renderer.quadData(x, y, size, size, color, 0, 0);
	}
}
