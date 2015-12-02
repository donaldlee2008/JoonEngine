package com.joonsoft.joonengine.level;

import java.util.HashMap;

import com.joonsoft.joonengine.level.gameobject.GameObject;
import com.joonsoft.joonengine.math.MathUtil;
import com.joonsoft.joonengine.renderer.SkyBox;

public class Level {

	public HashMap<Long, GameObject> gameObjectMap = new HashMap<Long, GameObject>();

	private HashMap<GameObject, Long> reverseMap = new HashMap<GameObject, Long>();
	private int width, height;
	private SkyBox skyBox;

	/**
	 * Creates a new level with the specific size.
	 *
	 * @param width
	 *            Width in units
	 * @param height
	 *            Height in units
	 */
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Creates a new level with 4096 as width and height.
	 */
	public Level() {
		this(4096, 4096);
	}

	public Level(String name) {
		this(0, 0);
	}

	public long addGameObject(GameObject gameObject) {
		long id = MathUtil.getRandomLong();
		this.gameObjectMap.put(id, gameObject);
		this.reverseMap.put(gameObject, id);
		System.out.println("Added gameobject with id " + id);
		return id;
	}

	public void setSkyBox(SkyBox skyBox) {
		this.skyBox = skyBox;
	}
	
	public SkyBox getSkyBox() {
		return skyBox;
	}

	public GameObject getGameObject(long id) {
		return this.gameObjectMap.get(id);
	}

	public void deleteGameObject(long id) {
		this.gameObjectMap.remove(id);
		this.reverseMap.remove(getGameObject(id));
	}

	public long getID(GameObject object) {
		return reverseMap.get(object);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
