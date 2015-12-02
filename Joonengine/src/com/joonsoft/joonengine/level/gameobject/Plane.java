package com.joonsoft.joonengine.level.gameobject;

import static org.lwjgl.opengl.GL11.*;

public class Plane extends GameObject {

	private int displayList;

	public Plane(double posX, double posY, double posZ, double scaleX, double scaleY, double scaleZ, double rotX, double rotY, double rotZ) {
		super(posX, posY, posZ, scaleX, scaleY, scaleZ, rotX, rotY, rotZ);
	}

	public void init() {
		displayList = glGenLists(1);
		glNewList(displayList, GL_COMPILE);
		glBegin(GL_QUADS);
		glVertex2d(-1, 1);
		glVertex2d(-1, -1);
		glVertex2d(1, -1);
		glVertex2d(1, 1);
		glEnd();
		glEndList();
	}

	public void render() {
		glEnable(GL_CULL_FACE);
		glCullFace(GL_FRONT);
		glCallList(displayList);
		glDisable(GL_CULL_FACE);
	}
}
