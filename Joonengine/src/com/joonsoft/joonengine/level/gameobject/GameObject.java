package com.joonsoft.joonengine.level.gameobject;

public abstract class GameObject extends Transform {

	private Material material;
	private float renderColorR = 1,renderColorG = 1,renderColorB = 1;

	public GameObject(double posX, double posY, double posZ, double scaleX, double scaleY, double scaleZ, double rotX, double rotY, double rotZ) {
		super(posX, posY, posZ, scaleX, scaleY, scaleZ, rotX, rotY, rotZ);

		init();
	}

	public GameObject() {
		this(0, 0, 0, 1, 1, 1, 0, 0, 0);
	}

	public Material getMaterial() {
		return material;
	}

	public void init() {
	}

	public void render() {
	}

	public void setRenderColor(float r, float g, float b) {
		this.renderColorR = r;
		this.renderColorG = g;
		this.renderColorB = b;
	}

	public float getRenderColorR() {
		return renderColorR;
	}
	
	public float getRenderColorG() {
		return renderColorG;
	}
	
	public float getRenderColorB() {
		return renderColorB;
	}
}
