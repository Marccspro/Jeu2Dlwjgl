package com.veridian.main.game.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.Display;

import com.veridian.main.Component;
import com.veridian.main.entities.Entity;
import com.veridian.main.entities.Player;
import com.veridian.main.game.level.tiles.Tile;
import com.veridian.main.game.level.tiles.Tile.Tiles;

public class Level {
	
	public int width, height;
	
	List<Tile> tiles = new ArrayList<Tile>();
	Tile[][] solidTile;
	Tile[][] bgTile;
	
	private int[] bounds = new int[4];
	
	List<Entity> entities = new ArrayList<Entity>();
	private static Player player = new Player(5, 5);
	
	public Level(int width, int height) {
		loadLevel("level_metal");
		spawner();
	}
	
	public void spawner() {
		addEntity(player);
	}
	
	public void loadLevel(String name) {
		int[] pixels;
		BufferedImage image = null;
		try {
			image = ImageIO.read(Level.class.getResource("/levels/" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = image.getWidth();
		height = image.getHeight();
		
		bounds[0] = -16;
		bounds[1] = -16;
		bounds[2] = -width * 16 + 16 + Display.getWidth() / Component.scale;
		bounds[3] = -height * 16 + 16 + Display.getHeight() / Component.scale;
		
		pixels = new int[width * height];
		image.getRGB(0, 0, width, height, pixels, 0, width);
		
		solidTile = new Tile[width][height];
		bgTile = new Tile[width][height];
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (pixels[x + y * width] == 0xFFffffff) {
					solidTile[x][y] = new Tile(x, y, Tiles.SOLID_METAL);
				}
				if (pixels[x + y * width] == 0xFF000000) {
					bgTile[x][y] = new Tile(x, y, Tiles.BG_METAL);
				}
				if (pixels[x + y * width] == 0xFFffff00 ||
					pixels[x + y * width] == 0xFFffff01 ||
					pixels[x + y * width] == 0xFFffff02 ||
					pixels[x + y * width] == 0xFFffff03 ||
					pixels[x + y * width] == 0xFFffff04 ||
					pixels[x + y * width] == 0xFFffff05) {
					
					bgTile[x][y] = new Tile(x, y, Tiles.BG_METAL);
				}
			}	
		}
		setTiles();
	}
	
	public void setTiles() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (x - 1 < 0 || y - 1 < 0 || x + 1 >= width || y + 1 >= height) continue;
				boolean vu = false, vd = false, vl = false, vr = false;
				boolean vur = false, vdr = false, vul = false, vdl = false;
				
				if (solidTile[x + 1][y] == null) {
					vr = true;
				}
				if (solidTile[x - 1][y] == null) {
					vl = true;
				}
				if (solidTile[x][y + 1] == null) {
					vd = true;
				}
				if (solidTile[x][y - 1] == null) {
					vu = true;
				}
				//
				if (solidTile[x + 1][y + 1] == null) {
					vdr = true;
				}
				if (solidTile[x - 1][y - 1] == null) {
					vul = true;
				}
				if (solidTile[x - 1][y + 1] == null) {
					vdl = true;
				}
				if (solidTile[x + 1][y - 1] == null) {
					vur = true;
				}
				if (solidTile[x][y] != null) {
					solidTile[x][y].setTiles(vr, vl, vd, vu, vur, vul, vdr, vdl);
				}
				addTiles(x, y);
			}	
		}
	}
	
	public void addTiles(int x, int y) {
		if (solidTile[x][y] != null) {
			tiles.add(solidTile[x][y]);
		}else if (bgTile[x][y] != null) {
			tiles.add(bgTile[x][y]);
		}
	}
	
	public void init() {
		
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void removeEntity(Entity e) {
		entities.remove(e);
	}
	
	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if (e.getRemoved()) entities.remove(e);
			e.update();
		}
	}
	
	public void render() {
		for (Tile tile : tiles) {
			tile.render();
		}
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render();
		}
	}
	
	public int getBounds(int index) {
		return bounds[index];
	}
	
	public static Player getPlayer() {
		return player;
	}
}
