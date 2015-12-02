package com.test;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;

import java.awt.Color;
import java.io.*;

import javax.imageio.ImageIO;

import com.joonsoft.joonengine.JoonengineGame;
import com.joonsoft.joonengine.input.Input;
import com.joonsoft.joonengine.level.Level;
import com.joonsoft.joonengine.level.gameobject.*;
import com.joonsoft.joonengine.renderer.SkyBox;
import com.joonsoft.joonengine.renderer.texture.*;

public class Test extends JoonengineGame {

	private Level level;
	private double x, y = 2, z;
	private double rotY, rotX;

	private GameObject ground, cube;

	int planeRotation = 0;

	@Override
	public String getTitle() {
		return "TestGame";
	}

	@Override
	public String getVersion() {
		return "0.1";
	}

	@Override
	public void init() {
		TextureLoader textureLoader = new TextureLoader();
		level = new Level();
		try {
			Texture skyBoxTexture = new Texture(GL_TEXTURE_2D, textureLoader.loadTexture(ImageIO.read(new File("assets/skybox_texture.jpg"))));
			SkyBox skyBox = new SkyBox(skyBoxTexture);
			level.setSkyBox(skyBox);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ground = new Plane(0, 0, 0, 512, 0, 512, 90, 0, 0);
		ground.setRenderColor(0.2f, 1, 0.2f);
		level.addGameObject(ground);

		cube = new Cube(0, 3, 0, 1, 1, 1, 0, 0, 0);
		level.addGameObject(cube);

		Sphere sphere = new Sphere(0, 6, 0, 1, 1, 1, 0, 0, 0);
		level.addGameObject(sphere);

		Cylinder cylinder = new Cylinder(0, 9, 0, 1, 1, 1, 0, 0, 0);
		level.addGameObject(cylinder);
	}

	@Override
	public void update(int delta) {
		if (input.isMouseGrabbed()) {
			double mouseDX = input.getMouseDX() * 0.022 * 3;
			double mouseDY = input.getMouseDY() * 0.022 * 3;

			if (rotY + mouseDX >= 360) {
				rotY = rotY + mouseDX - 360;
			} else if (rotY + input.getMouseDX() < 0) {
				rotY = 360 - rotY + mouseDX;
			} else {
				rotY += mouseDX;
			}

			if (rotX - mouseDY >= -89 && rotX - mouseDY <= 89) {
				rotX += -mouseDY;
			} else if (rotX - mouseDY < -89) {
				rotX = -89;
			} else if (rotX - mouseDY > 89) {
				rotX = 89;
			}

			if (input.isKeyPressed(Input.GLFW_KEY_W)) {
				x -= Math.sin(rotY * Math.PI / 180) * 0.3f;
				z -= -Math.cos(rotY * Math.PI / 180) * 0.3f;
			}
			if (input.isKeyPressed(Input.GLFW_KEY_S)) {
				x += Math.sin(rotY * Math.PI / 180) * 0.3f;
				z += -Math.cos(rotY * Math.PI / 180) * 0.3f;
			}
			if (input.isKeyPressed(Input.GLFW_KEY_A)) {
				x -= Math.sin((rotY - 90) * Math.PI / 180) * 0.3f;
				z -= -Math.cos((rotY - 90) * Math.PI / 180) * 0.3f;
			}
			if (input.isKeyPressed(Input.GLFW_KEY_D)) {
				x -= Math.sin((rotY + 90) * Math.PI / 180) * 0.3f;
				z -= -Math.cos((rotY + 90) * Math.PI / 180) * 0.3f;
			}
			if (input.isKeyPressed(Input.GLFW_KEY_Q)) {
				y -= 0.5f;
			}
			if (input.isKeyPressed(Input.GLFW_KEY_E)) {
				y += 0.5f;
			}
		}
		if (input.isKeyPressed(Input.GLFW_KEY_LEFT_ALT)) {
			input.setMouseGrabbed(false);
		}

		if (input.isKeyPressed(Input.GLFW_KEY_T)) {
			level.addGameObject(new Cube(-x, -y - 4, -z, 1, 1, 1, 0, 0, 0));
		}
	}

	@Override
	public void render() {
		renderer.rotate(rotX, rotY, 0);
		renderer.translate(x, y, z);
		renderer.renderLevel(level);
		level.getSkyBox().render(x, y, z);

		make2D();
		font.drawText(0, 0, "FPS: " + getFPS(), Color.white);
		font.drawText(0, 24, "TPS: " + getTPS(), Color.white);
		make3D();
	}

	@Override
	public void close() {
	}

	public Test(String[] args) {
		super(args);
	}

	public static void main(String[] args) {
		new Test(args);
	}
}
