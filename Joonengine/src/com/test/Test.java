package com.test;

import com.joonsoft.joonengine.JoonengineGame;
import com.joonsoft.joonengine.input.*;
import com.joonsoft.joonengine.level.gameobject.GameObject;
import com.joonsoft.joonengine.level.Level;

public class Test extends JoonengineGame {

	private Level level;
	private double x, y, z;
	private double rotY, rotX;

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
		level = new Level(this);
		GameObject object = new GameObject(0, 0, 0, 1, 1, 1, 0, 0, 0);

		long id = level.addGameObject(object);

		renderer.initForRendering(level, id);
	}

	@Override
	public void update() {
		double mouseDX = input.getMouseDX();
		double mouseDY = input.getMouseDY();
		
		System.out.println(mouseDX);

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

		if (input.isMousePressed(Input.GLFW_KEY_W)) {
			x -= Math.sin(rotY * Math.PI / 180) * 1;
			z -= -Math.cos(rotY * Math.PI / 180) * 1;
		}
		if (input.isMousePressed(Input.GLFW_KEY_S)) {
			x += Math.sin(rotY * Math.PI / 180) * 1;
			z += -Math.cos(rotY * Math.PI / 180) * 1;
		}
		if (input.isMousePressed(Input.GLFW_KEY_A)) {
			x -= Math.sin((rotY - 90) * Math.PI / 180) * 1;
			z -= -Math.cos((rotY - 90) * Math.PI / 180) * 1;
		}
		if (input.isMousePressed(Input.GLFW_KEY_D)) {
			x -= Math.sin((rotY + 90) * Math.PI / 180) * 1;
			z -= -Math.cos((rotY + 90) * Math.PI / 180) * 1;
		}
	}

	@Override
	public void render() {
		renderer.rotate(rotX, rotY, 0);
		renderer.translate(x, y, z);
		renderer.renderLevel(level);
	}

	@Override
	public void close() {
		System.out.println("Closing");
	}

	public Test(String[] args) {
		super(args);
	}

	public static void main(String[] args) {
		new Test(args);
	}
}
