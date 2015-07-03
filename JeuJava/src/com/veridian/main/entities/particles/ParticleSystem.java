package com.veridian.main.entities.particles;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import com.veridian.main.entities.Entity;
import com.veridian.main.game.level.Level;
import com.veridian.main.graphics.Texture;

public class ParticleSystem extends Entity{
	private Texture texture = Texture.loadTexture("/textures/fire.png");
	private List<Particle> particles = new ArrayList<Particle>();
	
	public ParticleSystem(int x, int y, int num, Particle p, Level level) {
		super(x, y);
		for (int i = 0; i < num; i++) {
			particles.add(new Particle(p, x, y, level));
		}
	}

	public void init(Level level) {
		this.level = level;
	}

	public void update() {
		for (int i = 0; i < particles.size(); i++) {
			Particle p = particles.get(i);
			if (p.removed) particles.remove(p);
			p.update();
		}
	}

	public void render() {
		texture.bind();
		glBegin(GL_QUADS);
		for (Particle p : particles) {
			p.render();
		}
		glEnd();
		texture.unbind();
	}
}
