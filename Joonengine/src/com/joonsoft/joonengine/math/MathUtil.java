package com.joonsoft.joonengine.math;

public class MathUtil {
	private static RandomXS128 rng = new RandomXS128();

	public static long getRandomLong(long max) {
		return rng.nextLong(max);
	}

	public static long getRandomLong() {
		return rng.nextLong();
	}
}
