package com.joonsoft.joonengine.level.gameobject;

import static org.lwjgl.opengl.GL11.*;

public class Cube extends GameObject {
	private int displayList;

	public Cube(double posX, double posY, double posZ, double scaleX, double scaleY, double scaleZ, double rotX, double rotY, double rotZ) {
		super(posX, posY, posZ, scaleX, scaleY, scaleZ, rotX, rotY, rotZ);
	}

	public void init() {
		displayList = glGenLists(1);
		glNewList(displayList, GL_COMPILE);
		glBegin(GL_QUADS);
		glVertex3f(1f, -1f, -1f); // 1
		glVertex3f(-1f, -1f, -1f); // 2
		glVertex3f(-1f, 1f, -1f); // 3
		glVertex3f(1f, 1f, -1f); // 4
		// right face
		glVertex3f(-1f, -1f, 1f); // 1
		glVertex3f(1f, -1f, 1f); // 2
		glVertex3f(1f, 1f, 1f); // 3
		glVertex3f(-1f, 1f, 1f); // 4
		// top face
		glVertex3f(1f, 1f, -1f); // 1
		glVertex3f(-1f, 1f, -1f); // 2
		glVertex3f(-1f, 1f, 1f); // 3
		glVertex3f(1f, 1f, 1f); // 4
		// bottom face
		glVertex3f(1f, -1f, 1f); // 1
		glVertex3f(-1f, -1f, 1f); // 2
		glVertex3f(-1f, -1f, -1f); // 3
		glVertex3f(1f, -1f, -1f); // 4
		// left face
		glVertex3f(1f, -1f, 1f); // 1
		glVertex3f(1f, -1f, -1f); // 2
		glVertex3f(1f, 1f, -1f); // 3
		glVertex3f(1f, 1f, 1f); // 4
		// right face
		glVertex3f(-1f, -1f, -1f); // 1
		glVertex3f(-1f, -1f, 1f); // 2
		glVertex3f(-1f, 1f, 1f); // 3
		glVertex3f(-1f, 1f, -1f); // 4
		glEnd();
		glEndList();
	}

	public void render() {
		glCallList(displayList);
	}
}
