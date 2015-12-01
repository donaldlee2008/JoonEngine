package com.joonsoft.joonengine.level.gameobject;

public class GameObject extends Transform {

	private Material material;

	public GameObject(double posX, double posY, double posZ, double scaleX, double scaleY, double scaleZ, double rotX, double rotY, double rotZ) {
		super(posX, posY, posZ, scaleX, scaleY, scaleZ, rotX, rotY, rotZ);
	}

	public GameObject() {
		this(0, 0, 0, 1, 1, 1, 0, 0, 0);
	}
}
