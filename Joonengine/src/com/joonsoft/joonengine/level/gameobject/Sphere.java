package com.joonsoft.joonengine.level.gameobject;

public class Sphere extends GameObject {

	private com.joonsoft.joonengine.glu.Sphere sphere;

	public Sphere(double posX, double posY, double posZ, double scaleX, double scaleY, double scaleZ, double rotX, double rotY, double rotZ) {
		super(posX, posY, posZ, scaleX, scaleY, scaleZ, rotX, rotY, rotZ);
	}

	public void init() {
		this.sphere = new com.joonsoft.joonengine.glu.Sphere();
	}

	public void render() {
		sphere.draw(1, 16, 16);
	}
}
