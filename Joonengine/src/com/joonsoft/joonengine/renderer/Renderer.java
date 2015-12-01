package com.joonsoft.joonengine.renderer;

import com.joonsoft.joonengine.JoonengineGame;
import com.joonsoft.joonengine.level.Level;
import com.joonsoft.joonengine.level.gameobject.GameObject;

import static org.lwjgl.opengl.GL11.*;

import java.util.HashMap;

public class Renderer {

	private JoonengineGame game;

	public void initRenderer(JoonengineGame game) {
		this.game = game;
	}

	public HashMap<Long, Integer> displayLists = new HashMap<Long, Integer>();

	public void initForRendering(Level level, long id) {
		int displayList = glGenLists(1);
		glNewList(displayList, GL_COMPILE);
		glBegin(GL_QUADS);
		glTexCoord2f(0f, 1f);
		glVertex3f(1f, 0f, 0f); // 1
		glTexCoord2f(1f, 1f);
		glVertex3f(0f, 0f, 0f); // 2
		glTexCoord2f(1f, 0f);
		glVertex3f(0f, 1f, 0f); // 3
		glTexCoord2f(0f, 0f);
		glVertex3f(1f, 1f, 0f); // 4
		// right face
		glTexCoord2f(0f, 1f);
		glVertex3f(0f, 0f, 1f); // 1
		glTexCoord2f(1f, 1f);
		glVertex3f(1f, 0f, 1f); // 2
		glTexCoord2f(1f, 0f);
		glVertex3f(1f, 1f, 1f); // 3
		glTexCoord2f(0f, 0f);
		glVertex3f(0f, 1f, 1f); // 4
		// top face
		glTexCoord2f(0f, 1f);
		glVertex3f(1f, 1f, 0f); // 1
		glTexCoord2f(1f, 1f);
		glVertex3f(0f, 1f, 0f); // 2
		glTexCoord2f(1f, 0f);
		glVertex3f(0f, 1f, 1f); // 3
		glTexCoord2f(0f, 0f);
		glVertex3f(1f, 1f, 1f); // 4
		// bottom face
		glTexCoord2f(0f, 1f);
		glVertex3f(1f, 0f, 1f); // 1
		glTexCoord2f(1f, 1f);
		glVertex3f(0f, 0f, 1f); // 2
		glTexCoord2f(1f, 0f);
		glVertex3f(0f, 0f, 0f); // 3
		glTexCoord2f(0f, 0f);
		glVertex3f(1f, 0f, 0f); // 4
		// left face
		glTexCoord2f(0f, 1f);
		glVertex3f(1f, 0f, 1f); // 1
		glTexCoord2f(1f, 1f);
		glVertex3f(1f, 0f, 0f); // 2
		glTexCoord2f(1f, 0f);
		glVertex3f(1f, 1f, 0f); // 3
		glTexCoord2f(0f, 0f);
		glVertex3f(1f, 1f, 1f); // 4
		// right face
		glTexCoord2f(0f, 1f);
		glVertex3f(0f, 0f, 0f); // 1
		glTexCoord2f(1f, 1f);
		glVertex3f(0f, 0f, 1f); // 2
		glTexCoord2f(1f, 0f);
		glVertex3f(0f, 1f, 1f); // 3
		glTexCoord2f(0f, 0f);
		glVertex3f(0f, 1f, 0f); // 4
		glEnd();
		glEndList();
		displayLists.put(id, displayList);
	}

	public void renderGameObject(Level level, long id) {
		GameObject object = level.getGameObject(id);
		glPushMatrix();
		glColor3f(1, 1, 0);
		glTranslated(object.getPosX(), object.getPosY(), object.getPosZ());
		glRotated(object.getRotX(), 1, 0, 0);
		glRotated(object.getRotY(), 0, 1, 0);
		glRotated(object.getRotZ(), 0, 0, 1);
		glScaled(object.getScaleX(), object.getScaleY(), object.getScaleZ());
		glCallList(displayLists.get(id));
		glPopMatrix();
	}

	public void renderLevel(Level level) {
		for (GameObject object : level.gameObjectMap.values()) {
			renderGameObject(level, level.getID(object));
		}
	}

	public void clear(float r, float g, float f) {
		glClearColor(r, g, f, 1);
	}

	public void translate(double x, double y, double z) {
		glTranslated(x, y, z);
	}

	public void rotate(double x, double y, double z) {
		glRotated(x, 1, 0, 0);
		glRotated(y, 0, 1, 0);
		glRotated(z, 0, 0, 1);
	}
}
