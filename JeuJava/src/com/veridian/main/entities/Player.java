package com.veridian.main.entities;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.veridian.main.entities.bullets.BasicBullet;
import com.veridian.main.entities.particles.Particle;
import com.veridian.main.entities.particles.ParticleSystem;
import com.veridian.main.game.Game;
import com.veridian.main.game.level.Level;
import com.veridian.main.graphics.Color;
import com.veridian.main.graphics.Renderer;
import com.veridian.main.graphics.Texture;
import com.veridian.main.math.Vector2f;
import com.veridian.main.utiles.Animation;

public class Player extends Entity {

	Animation anim;
	int dir = 0;

	public Player(int x, int y) {
		super(x, y);
		texture = Texture.player;
		anim = new Animation(3, 10, true);
		mass = 0.1f;
		drag = 0.95f;
		
		shootPoint = new Vector2f(8, 8);
		mouseDirection = new Vector2f();
	}

	float xa, ya;
	float speed = 0.1f;

	public void update() {
		ya += level.gravity * mass;
		if (isGrounded()) {
			drag = 0.85f;
		} else {
			drag = 0.95f;
		}

		anim.update();
		anim.pause();

		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			ya -= 0.3;
			Particle particle = new Particle();
			particle.setDirection(new Vector2f(0, 4));
			particle.setSize(10);
			particle.setSpeed(0.5f);
			particle.setLifeTime(20);
			level.addEntity(new ParticleSystem((int) x + 8 - 5, (int) y + 4, 2, particle, level));
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_Z) || Keyboard.isKeyDown(Keyboard.KEY_W)) {
			if (isGrounded()) {
				ya -= 3.8;
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q) || Keyboard.isKeyDown(Keyboard.KEY_A)) {
			xa -= speed;
			dir = 1;
			anim.play();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			xa += speed;
			dir = 0;
			anim.play();
		}
		
		if (Mouse.isButtonDown(0)) {
			level.addEntity(new BasicBullet(x + shootPoint.x, y + shootPoint.y, mouseDirection));
		}
		
		if (Game.getMouseX() > x + 8) dir = 0;
		else if (Game.getMouseX() < x + 8) dir = 1;
		
		mouseDirection.set(Game.getMouseX() - (x + shootPoint.x), Game.getMouseY() - (y + shootPoint.y)).normalize();
		
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

		xa *= drag;
		ya *= drag;
	}

	public void render() {
		texture.bind();
		Renderer.renderEntity(x, y, 16, 16, Color.WHITE, 4, 1 + dir, anim.getCurrentFrame());
		texture.unbind();

		glLineWidth(5);
		glBegin(GL_LINES);
			glVertex2f(x + shootPoint.x, y + shootPoint.y);
			glVertex2f(x + shootPoint.x + mouseDirection.x * 16, y + shootPoint.y + mouseDirection.y * 16);
		glEnd();
	}

	public void init(Level level) {
		this.level = level;
	}
}
