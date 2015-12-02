package com.joonsoft.joonengine.renderer;

import static org.lwjgl.opengl.GL11.*;

import com.joonsoft.joonengine.renderer.texture.Texture;

public class SkyBox {

	private Texture texture;

	private int frontDisplayList;
	private int leftDisplayList;
	private int backDisplayList;
	private int rightDisplayList;
	private int bottomDisplayList;
	private int topDisplayList;

	public SkyBox(Texture texture) {
		this.texture = texture;

		glEnable(GL_CULL_FACE);
		glCullFace(GL_FRONT);
		// Render the front quad
		frontDisplayList = glGenLists(1);
		glNewList(frontDisplayList, GL_COMPILE);
		glBegin(GL_QUADS);
		glTexCoord2d(0.50, 0.50);
		glVertex3d(1, -1, -1);
		glTexCoord2d(0.25, 0.50);
		glVertex3d(-1, -1, -1);
		glTexCoord2d(0.25, 0.25);
		glVertex3d(-1, 1, -1);
		glTexCoord2d(0.50, 0.25);
		glVertex3d(1, 1, -1);
		glEnd();
		glEndList();

		// Render the left quad
		leftDisplayList = glGenLists(1);
		glNewList(leftDisplayList, GL_COMPILE);
		glBegin(GL_QUADS);
		glTexCoord2d(0.75, 0.5);
		glVertex3d(1, -1, 1);
		glTexCoord2d(0.5, 0.5);
		glVertex3d(1, -1, -1);
		glTexCoord2d(0.5, 0.25);
		glVertex3d(1, 1, -1);
		glTexCoord2d(0.75, 0.25);
		glVertex3d(1, 1, 1);
		glEnd();
		glEndList();

		// Render the back quad
		backDisplayList = glGenLists(1);
		glNewList(backDisplayList, GL_COMPILE);
		glBegin(GL_QUADS);
		glTexCoord2d(1, 0.5);
		glVertex3d(-1, -1, 1);
		glTexCoord2d(0.75, 0.5);
		glVertex3d(1, -1, 1);
		glTexCoord2d(0.75, 0.25);
		glVertex3d(1, 1, 1);
		glTexCoord2d(1, 0.25);
		glVertex3d(-1, 1, 1);
		glEnd();
		glEndList();

		// Render the right quad
		rightDisplayList = glGenLists(1);
		glNewList(rightDisplayList, GL_COMPILE);
		glBegin(GL_QUADS);
		glTexCoord2d(0.25, 0.5);
		glVertex3d(-1, -1, -1);
		glTexCoord2d(0, 0.5);
		glVertex3d(-1, -1, 1);
		glTexCoord2d(0, 0.25);
		glVertex3d(-1, 1, 1);
		glTexCoord2d(0.25, 0.25);
		glVertex3d(-1, 1, -1);
		glEnd();
		glEndList();

		// Render the bottom quad
		bottomDisplayList = glGenLists(1);
		glNewList(bottomDisplayList, GL_COMPILE);
		glBegin(GL_QUADS);
		glTexCoord2d(0.25, 0.50);
		glVertex3d(-1, -1, -1);
		glTexCoord2d(0.25, 0.75);
		glVertex3d(-1, -1, 1);
		glTexCoord2d(0.50, 0.75);
		glVertex3d(1, -1, 1);
		glTexCoord2d(0.50, 0.50);
		glVertex3d(1, -1, -1);
		glEnd();
		glEndList();

		// Render the top quad
		topDisplayList = glGenLists(1);
		glNewList(topDisplayList, GL_COMPILE);
		glBegin(GL_QUADS);
		glTexCoord2d(0.50, 0);
		glVertex3d(-1, 1, -1);
		glTexCoord2d(0.50, 0.25);
		glVertex3d(-1, 1, 1);
		glTexCoord2d(0.25, 0.25);
		glVertex3d(1, 1, 1);
		glTexCoord2d(0.25, 0);
		glVertex3d(1, 1, -1);
		glEnd();
		glEndList();
	}

	public void render(double xOffset, double yOffset, double zOffset) {
		glTranslated(0, 0.2, 0);
		glPushMatrix();
		glDepthRange(1, 1);
		glColor3d(1, 1, 1);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_CULL_FACE);
		glCullFace(GL_FRONT);
		texture.bind();
		glTranslated(-xOffset, -yOffset, -zOffset);
		glCallList(frontDisplayList);
		glCallList(leftDisplayList);
		glCallList(backDisplayList);
		glCallList(rightDisplayList);
		glPushMatrix();
		glRotated(180, 0, 0, 1);
		glTranslated(0, 2, 0);
		glCallList(bottomDisplayList);
		glPopMatrix();
		glRotated(180, 0, 1, 0);
		glCallList(topDisplayList);
		glPopMatrix();
		glDepthRange(0, 1);
		glDisable(GL_CULL_FACE);
	}
}
