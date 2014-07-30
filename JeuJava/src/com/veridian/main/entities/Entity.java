package com.veridian.main.entities;

import com.veridian.main.game.level.Level;
import com.veridian.main.graphics.Texture;

public abstract class Entity {
	
	protected int x, y;
	protected boolean removed = false;
	protected Texture texture;
	protected Level level; 
	
	public Entity(int x, int y) {
		this.x = x * 16;
		this.y = y * 16;
	}
	
	public abstract void init(Level level);
	public abstract void update();
	public abstract void render();
	
	public boolean isSolidTile(float xa, float ya) {
		
		int x0 = (int) (x + xa + 3) / 16;
		int x1 = (int) (x + xa + 12) / 16;
		int y0 = (int) (y + ya + 2) / 16;
		int y1 = (int) (y + ya + 13) / 16;
		
		if (level.getSolidTile(x0, y0) != null) return true;
		if (level.getSolidTile(x1, y0) != null) return true;
		if (level.getSolidTile(x1, y1) != null) return true;
		if (level.getSolidTile(x0, y1) != null) return true;
		
		return false;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean getRemoved() {
		return removed;
	}
	
	public Texture getTexture() {
		return texture;
	}
}
