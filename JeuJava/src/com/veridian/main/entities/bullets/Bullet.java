package com.veridian.main.entities.bullets;

import com.veridian.main.entities.Entity;
import com.veridian.main.game.level.Level;
import com.veridian.main.graphics.Renderer;
import com.veridian.main.graphics.Texture;
import com.veridian.main.math.Vector2f;

public abstract class Bullet extends Entity {

	protected int size;
	protected int tex;
	protected Vector2f dir;
	protected float speed;
	protected int dammage;
	protected float angle;
	
	public Bullet(float x, float y, int size, Vector2f dir) {
		super(x / 16, y / 16);
		
		this.size = size;
		this.dir = new Vector2f(dir);
		this.angle = (float) Math.toDegrees(Math.atan2(dir.y, dir.x));
	}
	
	public abstract void init(Level level);
	public abstract void update();
	
	public void render() {
		Texture.bullets.bind();
		Renderer.renderBullet(x - size / 2, y - size / 2, size, tex, angle);
	}
}
