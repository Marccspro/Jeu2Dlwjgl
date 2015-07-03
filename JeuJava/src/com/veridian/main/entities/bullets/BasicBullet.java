package com.veridian.main.entities.bullets;

import com.veridian.main.game.level.Level;
import com.veridian.main.math.Vector2f;

public class BasicBullet extends Bullet {

	public BasicBullet(float x, float y, Vector2f dir) {
		super(x, y, 16, dir);
		
		tex = 0;
		speed = 0.2f;
		dammage = 1;
		mass = 0.001f;
	}

	public void init(Level level) {
		this.level = level;
	}
	
	int time = 0;
	float xa, ya, g;
	public void update() {
		time++;
		if (time > 10 * 60) removed = true;
		
		g += level.gravity * mass;
		ya += g;
		
		xa += dir.x * speed;
		ya += dir.y * speed;
		
		x += xa;
		y += ya;
		
		xa *= 0.9f;
		ya *= 0.9f;
		
		angle = (float) Math.toDegrees(Math.atan2(ya, xa));
		
		System.out.println(ya);
	}
}
