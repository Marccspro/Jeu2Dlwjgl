package com.veridian.main.game.level.tiles;

import java.util.Random;

import com.veridian.main.Component;
import com.veridian.main.game.Game;
import com.veridian.main.graphics.Renderer;
import com.veridian.main.graphics.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Tile {
	
	public int x, y;
	int xo = 0, yo = 0;
	boolean hasTileSet = false;
	public int size = 16;
	public int halfSize = size / 2;
	
	public int[] tileSprite = new int[8];
	
	float[] color = new float[]{1, 1, 1, 1};

	Tiles tile;
	
	public enum Tiles {
		SOLID_METAL, BG_METAL
	}
	
	public Tile(int x, int y, Tiles tile) {
		this.x = x;
		this.y = y;
		this.tile = tile;
		
		if (tile == Tiles.SOLID_METAL) {
			xo = 0;
			yo = 0;
			hasTileSet = true;
		}else if (tile == Tiles.BG_METAL) {
			xo = 3;
			yo = 2;
		}
		
		if (hasTileSet) tileSprite = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
	}
	
	public void setTiles(boolean vr, boolean vl, boolean vd, boolean vu, boolean vur, boolean vul, boolean vdr, boolean vdl) {
		if (!hasTileSet) return;
		if (vl) {
			tileSprite[0] = 0;
			tileSprite[1] = 1;
			tileSprite[6] = 0;
			tileSprite[7] = 1;
		}
		if (vr) {
			tileSprite[2] = 2;
			tileSprite[3] = 1;
			tileSprite[4] = 2;
			tileSprite[5] = 1;
		}
		if (vu) {
			tileSprite[0] = 1;
			tileSprite[1] = 0;
			tileSprite[2] = 1;
			tileSprite[3] = 0;
			if (vr) {
				tileSprite[2] = 2;
				tileSprite[3] = 0;
			}
			if (vl) {
				tileSprite[0] = 0;
				tileSprite[1] = 0;
			}
		}
		if (vd) {
			tileSprite[4] = 1;
			tileSprite[5] = 2;
			tileSprite[6] = 1;
			tileSprite[7] = 2;
			if (vr) {
				tileSprite[4] = 2;
				tileSprite[5] = 2;
			}
			if (vl) {
				tileSprite[6] = 0;
				tileSprite[7] = 2;
			}
		}

		if (vd && vr) {
			tileSprite[4] = 2;
			tileSprite[5] = 2;
		}
		if (vd && vl) {
			tileSprite[6] = 0;
			tileSprite[7] = 2;
		}

		if (vur && !vu && !vr) {
			tileSprite[2] = 3;
			tileSprite[3] = 1;
		}
		if (vdr && !vd && !vr) {
			tileSprite[4] = 3;
			tileSprite[5] = 0;
		}
		if (vul && !vu && !vl) {
			tileSprite[0] = 4;
			tileSprite[1] = 1;
		}
		if (vdl && !vd && !vl) {
			tileSprite[6] = 4;
			tileSprite[7] = 0;
		}
	}
	
	public void render() {
		float x0 = x + Game.xScroll / 16;
		float y0 = y + Game.yScroll / 16;
		
		float x1 = x + 1 + Game.xScroll / 16;
		float y1 = y + 1 + Game.yScroll / 16;
		
		if (x1 < 0 || y1 < 0 || x0 > Component.width / 16 || y0 > Component.height / 16) return;
		Texture.tiles.bind();
			glBegin(GL_QUADS);	
Renderer.quadData(x * size, y * size, halfSize, halfSize, color, xo + tileSprite[0], yo + tileSprite[1]);
Renderer.quadData(x * size + 8, y * size, halfSize, halfSize, color, xo + tileSprite[2], yo + tileSprite[3]);
Renderer.quadData(x * size + 8, y * size + 8, halfSize, halfSize, color, xo + tileSprite[4], yo + tileSprite[5]);
Renderer.quadData(x * size, y * size + 8, halfSize, halfSize, color, xo + tileSprite[6], yo + tileSprite[7]);
			glEnd();
		Texture.tiles.unbind();
	}
}
