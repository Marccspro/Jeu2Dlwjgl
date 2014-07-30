package com.veridian.main.entities;

import org.lwjgl.input.Keyboard;

import com.veridian.main.game.level.Level;
import com.veridian.main.graphics.Color;
import com.veridian.main.graphics.Renderer;
import com.veridian.main.graphics.Texture;
import com.veridian.main.utiles.Animation;

public class Player extends Entity{
	
	Animation anim;
	int dir = 0;
	
	public Player(int x, int y) {
		super(x, y);
		texture = Texture.player;
		anim = new Animation(3, 10, true);
	}
	
	float xa, ya;
	public void update() {
		anim.update();
		anim.pause();
		if (Keyboard.isKeyDown(Keyboard.KEY_Z) || Keyboard.isKeyDown(Keyboard.KEY_W)) {
			ya = -1;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			ya = 1;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q) || Keyboard.isKeyDown(Keyboard.KEY_A)) {
			xa = -1;
			dir = 1;
			anim.play();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			xa = 1;
			dir = 0;
			anim.play();
		}
		
		if (!isSolidTile(xa, 0)) {
			x += xa;
			xa = 0;
		}
		if (!isSolidTile(0, ya)) {
			y += ya;
			ya = 0;
		}
		
		xa = 0;
		ya = 0;
	}

	public void render() {
		texture.bind();
		Renderer.renderEntity(x, y, 16, 16, Color.WHITE, 4, 1 + dir, anim.getCurrentFrame());
		texture.unbind();
	}

	public void init(Level level) {
		this.level = level;
	}

}
