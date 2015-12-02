package com.joonsoft.joonengine.level.gameobject;

public abstract class Transform {
	protected double posX, posY, posZ, scaleX, scaleY, scaleZ, rotX, rotY, rotZ;

	public Transform(double posX, double posY, double posZ, double scaleX, double scaleY, double scaleZ, double rotX, double rotY, double rotZ) {
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.scaleZ = scaleZ;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
	}

	public double getPosX() {
		return posX;
	}

	public double getPosY() {
		return posY;
	}

	public double getPosZ() {
		return posZ;
	}

	public double getScaleX() {
		return scaleX;
	}

	public double getScaleY() {
		return scaleY;
	}

	public double getScaleZ() {
		return scaleZ;
	}

	public double getRotX() {
		return rotX;
	}

	public double getRotY() {
		return rotY;
	}

	public double getRotZ() {
		return rotZ;
	}

	public double getCenterX() {
		return (getPosX() * getScaleX()) / 2;
	}

	public double getCenterY() {
		return (getPosY() * getScaleY()) / 2;
	}

	public double getCenterZ() {
		return (getPosZ() * getScaleZ()) / 2;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public void setPosZ(double posZ) {
		this.posZ = posZ;
	}

	public void setScaleX(double scaleX) {
		this.scaleX = scaleX;
	}

	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
	}

	public void setScaleZ(double scaleZ) {
		this.scaleZ = scaleZ;
	}

	public void setRotX(double rotX) {
		this.rotX = rotX;
	}

	public void setRotY(double rotY) {
		this.rotY = rotY;
	}

	public void setRotZ(double rotZ) {
		this.rotZ = rotZ;
	}
}
