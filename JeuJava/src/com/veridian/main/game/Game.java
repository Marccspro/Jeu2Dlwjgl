package com.veridian.main.game;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.veridian.main.Component;
import com.veridian.main.game.level.Level;

public class Game {
	
	Level level;
	
	public static float xScroll, yScroll;
	
	public Game() {
		level = new Level(64, 64);
		xScroll = level.getBounds(0);
		yScroll = level.getBounds(1);
	}
	
	public void init() {
		level.init();
	}
	
	public void translateView(float xa, float ya) {
		xScroll = xa;
		yScroll = ya;
		if (xScroll > level.getBounds(0)) xScroll = level.getBounds(0);
		if (xScroll < level.getBounds(2)) xScroll = level.getBounds(2);
		if (yScroll > level.getBounds(1)) yScroll = level.getBounds(1);
		if (yScroll < level.getBounds(3)) yScroll = level.getBounds(3);
	}
	
	float xa = 0, ya = 0;
	public void update() {
		level.update();
		
		xa = -Level.getPlayer().getX() + Component.width / 2 - 8;
		ya = -Level.getPlayer().getY() + Component.height / 2 - 8;
		
		translateView(xa, ya);
	}
	
	public void render() {
		GL11.glTranslatef(xScroll, yScroll, 0);
		level.render();
	}
	
	public static float getMouseX() {
		return Mouse.getX() / Component.scale - xScroll;
	}
	
	public static float getMouseY() {
		return (Display.getHeight() - Mouse.getY()) / Component.scale - yScroll;
	}
}