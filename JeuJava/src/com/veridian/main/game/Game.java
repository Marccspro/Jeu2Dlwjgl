package com.veridian.main.game;

import org.lwjgl.opengl.GL11;

import com.veridian.main.Component;
import com.veridian.main.entities.particles.Particle;
import com.veridian.main.game.level.Level;
import com.veridian.main.graphics.Color;
import com.veridian.main.graphics.Renderer;

import static org.lwjgl.opengl.GL11.*;

public class Game {
	
	Level level;
	Particle particle = new Particle(null, 64, 64);
	
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
		
		glBegin(GL_QUADS);
		particle.render();
		glEnd();
	}
}