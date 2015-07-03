package com.veridian.main.entities.particles;

import java.util.Random;

import com.veridian.main.game.level.Level;
import com.veridian.main.graphics.Color;
import com.veridian.main.graphics.Renderer;
import com.veridian.main.graphics.Texture;
import com.veridian.main.math.Vector2f;

public class Particle {
	
	public boolean removed = false;
	
	float x, y;
	double rx, ry;
	Random rand = new Random();
	private Level level;
	private float alpha = 1;
	
	private float[] color = Color.WHITE;
	private Vector2f direction = new Vector2f(0, 0);
	private int size = 1;
	private float speed = 1;
	private int lifeTime = 20;

	public Particle() {};
	
	public Particle setColor(float[] color) {
		this.color = color;
		return this;
	}
	public Particle setDirection(Vector2f direction) {
		this.direction = direction;
		return this;
	}
	public Particle setSize(int size) {
		this.size = size;
		return this;
	}
	public Particle setSpeed(float speed) {
		this.speed = speed;
		return this;
	}
	public Particle setLifeTime(int lifeTime) {
		this.lifeTime = lifeTime;
		return this;
	}
	
	public Particle(Particle p, int x, int y, Level level) {
		this.x = x;
		this.y = y;
		this.level = level;
		
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
	float xa, ya;
	public void update() {
		time++;
		if (lifeTime - 10 > time) {
			alpha -= 0.1f;
		}
		if (time > lifeTime) {
			removed = true;
			return;
		}
		xa = (float) ((rx + direction.x) * speed);
		ya = (float) ((ry + direction.y) * speed);
		
		int xStep = (int) Math.abs(xa * 100);
		for (int i = 0; i < xStep; i++) {
			if (!isSolidTile(xa / xStep, 0)) {
				x += xa / xStep;
			} else {
				xa = 0;
			}
		}
		int yStep = (int) Math.abs(ya * 100);
		for (int i = 0; i < yStep; i++) {
			if (!isSolidTile(0, ya / yStep)) {
				y += ya / yStep;
			} else {
				ya = 0;
			}
		}
	}
	
	public void render() {
		Renderer.particleData(x, y, size, new float[]{color[0], color[1], color[2], color[3] * alpha});
	}
	
	public boolean isSolidTile(float xa, float ya) {
		
		int x0 = (int) (x + xa + 3) / 16;
		int x1 = (int) (x + xa + 8) / 16;
		int y0 = (int) (y + ya + 2) / 16;
		int y1 = (int) (y + ya + 8) / 16;
		
		if (level.getSolidTile(x0, y0) != null) return true;
		if (level.getSolidTile(x1, y0) != null) return true;
		if (level.getSolidTile(x1, y1) != null) return true;
		if (level.getSolidTile(x0, y1) != null) return true;
		
		return false;
	}
}
