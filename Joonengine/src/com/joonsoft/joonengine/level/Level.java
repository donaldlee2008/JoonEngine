package com.joonsoft.joonengine.level;

import com.joonsoft.joonengine.JoonengineGame;
import com.joonsoft.joonengine.level.gameobject.GameObject;
import com.joonsoft.joonengine.math.MathUtil;

import java.util.HashMap;

public class Level {

	private int width, height;

	public HashMap<Long, GameObject> gameObjectMap = new HashMap<Long, GameObject>();
	private HashMap<GameObject, Long> reverseMap = new HashMap<GameObject, Long>();

	private JoonengineGame game;

	/**
	 * Creates a new level with the specific size.
	 *
	 * @param width
	 *            Width in units
	 * @param height
	 *            Height in units
	 */
	public Level(JoonengineGame game, int width, int height) {
		this.game = game;
		this.width = width;
		this.height = height;
	}

	/**
	 * Creates a new level with 4096 as width and height.
	 */
	public Level(JoonengineGame game) {
		this(game, 4096, 4096);
	}

	public Level(JoonengineGame game, String name) {
		this(game, 0, 0);
	}

	public long addGameObject(GameObject gameObject) {
		long id = MathUtil.getRandomLong();
		this.gameObjectMap.put(id, gameObject);
		this.reverseMap.put(gameObject, id);
		System.out.println("Added gameobject with id " + id);
		return id;
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
}
