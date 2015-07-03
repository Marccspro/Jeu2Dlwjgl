package com.veridian.main.graphics;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {
	public static void quadData(float x, float y, int w, int h, float[] color, int xo, int yo) {
		glColor4f(color[0], color[1], color[2], color[3]);
		glTexCoord2f((0 + xo) / 32.0f, (0 + yo) / 32.0f); glVertex2f(x, y);
		glTexCoord2f((1 + xo) / 32.0f, (0 + yo) / 32.0f); glVertex2f(x + w, y);
		glTexCoord2f((1 + xo) / 32.0f, (1 + yo) / 32.0f); glVertex2f(x + w, y + h);
		glTexCoord2f((0 + xo) / 32.0f, (1 + yo) / 32.0f); glVertex2f(x, y + h);
	}
	
	public static void renderQuad(float x, float y, int w, int h, float[] color, int xo, int yo) {
		glBegin(GL_QUADS);
			Renderer.quadData(x, y, w, h, color, xo, yo);
		glEnd();
	}
	
	public static void renderEntity(float x, float y, int w, int h, float[] color, float size, int xo, int yo) {
		glBegin(GL_QUADS);
			glColor4f(color[0], color[1], color[2], color[3]);
			glTexCoord2f((0 + xo) / size, (0 + yo) / size); glVertex2f(x, y);
			glTexCoord2f((1 + xo) / size, (0 + yo) / size); glVertex2f(x + w, y);
			glTexCoord2f((1 + xo) / size, (1 + yo) / size); glVertex2f(x + w, y + h);
			glTexCoord2f((0 + xo) / size, (1 + yo) / size); glVertex2f(x, y + h);
		glEnd();
	}
	
	public static void renderBullet(float x, float y, int size, int tex, float angle) {
		int xo = tex % 8;
		int yo = tex / 8;
		
		glPushMatrix();
		
			glTranslatef(x + size / 2, y + size / 2, 0);
			glRotatef(angle, 0, 0, 1);
			glTranslatef(-x - size / 2, -y - size / 2, 0);
			
			glBegin(GL_QUADS);
			
				glColor4f(1, 1, 1, 1);
				glTexCoord2f((0 + xo) / 8.0f, (0 + yo) / 8.0f); glVertex2f(x, y);
				glTexCoord2f((1 + xo) / 8.0f, (0 + yo) / 8.0f); glVertex2f(x + size, y);
				glTexCoord2f((1 + xo) / 8.0f, (1 + yo) / 8.0f); glVertex2f(x + size, y + size);
				glTexCoord2f((0 + xo) / 8.0f, (1 + yo) / 8.0f); glVertex2f(x, y + size);
				
			glEnd();
		
		glPopMatrix();
	}
	
	public static void particleData(float x, float y, int size, float[] color) {
		glColor4f(color[0], color[1], color[2], color[3]);
		glTexCoord2f(0, 0); glVertex2f(x, y);
		glTexCoord2f(1, 0); glVertex2f(x + size, y);
		glTexCoord2f(1, 1); glVertex2f(x + size, y + size);
		glTexCoord2f(0, 1); glVertex2f(x, y + size);
	}
}
