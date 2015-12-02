package com.joonsoft.joonengine.level.gameobject;

public class Cylinder extends GameObject {

	private com.joonsoft.joonengine.glu.Cylinder cylinder;

	public Cylinder(double posX, double posY, double posZ, double scaleX, double scaleY, double scaleZ, double rotX, double rotY, double rotZ) {
		super(posX, posY, posZ, scaleX, scaleY, scaleZ, rotX, rotY, rotZ);
	}

	public void init() {
		this.cylinder = new com.joonsoft.joonengine.glu.Cylinder();
	}

	public void render() {
		cylinder.draw(1, 1, 1, 16, 16);
	}
}
