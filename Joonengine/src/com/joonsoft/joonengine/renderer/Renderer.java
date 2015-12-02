package com.joonsoft.joonengine.renderer;

import static org.lwjgl.opengl.GL11.*;

import com.joonsoft.joonengine.level.Level;
import com.joonsoft.joonengine.level.gameobject.GameObject;

public class Renderer {

	public void renderGameObject(Level level, long id) {
		GameObject object = level.getGameObject(id);
		glPushMatrix();
		glColor3f(object.getRenderColorR(), object.getRenderColorG(), object.getRenderColorB());
		glTranslated(object.getPosX(), object.getPosY(), object.getPosZ());
		glScaled(object.getScaleX(), object.getScaleY(), object.getScaleZ());
		glRotated(object.getRotX(), 1, 0, 0);
		glRotated(object.getRotY(), 0, 1, 0);
		glRotated(object.getRotZ(), 0, 0, 1);
		object.render();
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
