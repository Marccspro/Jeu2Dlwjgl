package com.veridian.main.entities;

import org.lwjgl.input.Keyboard;

import com.veridian.main.graphics.Color;
import com.veridian.main.graphics.Renderer;
import com.veridian.main.graphics.Texture;

public class Player extends Entity{

	public Player(int x, int y) {
		super(x, y);
		texture = Texture.player;
	}

	public void update() {
		if (Keyboard.isKeyDown(Keyboard.KEY_Z) || Keyboard.isKeyDown(Keyboard.KEY_W)) {
			y--;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			y++;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q) || Keyboard.isKeyDown(Keyboard.KEY_A)) {
			x--;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			x++;
		}
	}

	public void render() {
		texture.bind();
		Renderer.renderEntity(x, y, 16, 16, Color.WHITE, 8.0f, 0, 0);
		texture.unbind();
	}

}
