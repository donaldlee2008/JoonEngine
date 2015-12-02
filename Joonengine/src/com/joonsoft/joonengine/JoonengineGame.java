package com.joonsoft.joonengine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.awt.Font;
import java.io.File;

import org.lwjgl.Sys;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import com.digiturtle.ui.GLFont;
import com.joonsoft.joonengine.glu.GLU;
import com.joonsoft.joonengine.input.Input;
import com.joonsoft.joonengine.renderer.Renderer;

public abstract class JoonengineGame {

	protected Renderer renderer;

	private int width = 1280;
	private int height = width / 16 * 9;

	private GLFWErrorCallback errorCallback;
	private GLFWKeyCallback keyCallback;
	public GLFWCursorPosCallback cursorPosCallback;
	public GLFWMouseButtonCallback mouseButtonCallback;

	public long windowHandle;

	protected Input input;

	private long lastFrame;
	private int fps, tps;
	private long lastFPS;

	private int currentFPS, currentTPS;

	protected GLFont font;

	public JoonengineGame(String[] args) {
		File file = new File("libraries/native");
		System.setProperty("org.lwjgl.librarypath", file.getAbsolutePath());

		System.out.println("Running " + getTitle() + " " + getVersion() + " on Joonengine 0.0.0 with LWJGL " + Sys.getVersion());

		glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));
		glfwSetErrorCallback(errorCallback);

		if (glfwInit() != GL_TRUE) {
			throw new IllegalStateException("Unable to initialize GLFW!");
		}

		glfwDefaultWindowHints();

		windowHandle = glfwCreateWindow(width, height, getTitle(), NULL, NULL);
		if (windowHandle == NULL)
			throw new RuntimeException("Failed to create the GLFW window");

		input = new Input(this);

		glfwSetKeyCallback(windowHandle, keyCallback = new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				if (action == GLFW_PRESS) {
					input.keys[key] = true;
				} else if (action == GLFW_RELEASE) {
					input.keys[key] = false;
				}
				if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
					glfwSetWindowShouldClose(window, GL_TRUE);
			}
		});

		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		glfwSetWindowPos(windowHandle, (vidmode.getWidth() - width) / 2, (vidmode.getHeight() - height) / 2);

		glfwMakeContextCurrent(windowHandle);

		glfwSwapInterval(0);

		glfwShowWindow(windowHandle);

		input.setMouseGrabbed(true);

		GL.createCapabilities();

		renderer = new Renderer();

		font = new GLFont(new Font("Tahoma", Font.PLAIN, 0), 24);

		init();

		initGL3();

		getDelta();
		lastFPS = getTime();

		float maxTPS = 1.0f / 60f;

		double nextUpdateTime = (double) System.nanoTime() / 1000000000.0;
		double maxUpdateTimeDiff = 0.5;

		while (glfwWindowShouldClose(windowHandle) == GL_FALSE) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			glLoadIdentity();

			int delta = getDelta();

			double currUpdateTime = (double) System.nanoTime() / 1000000000.0;
			if ((currUpdateTime - nextUpdateTime) > maxUpdateTimeDiff)
				nextUpdateTime = currUpdateTime;
			if (currUpdateTime >= nextUpdateTime) {
				nextUpdateTime += maxTPS;
				tps++;
				update(delta);
			}

			render();

			updateFPS();

			glBindTexture(GL_TEXTURE_2D, 0);

			glfwSwapBuffers(windowHandle);

			glfwPollEvents();
		}

		close();

		glfwDestroyWindow(windowHandle);
		keyCallback.release();

		glfwTerminate();
		errorCallback.release();
	}

	public int getFPS() {
		return currentFPS;
	}

	public int getTPS() {
		return currentTPS;
	}

	protected void make2D() {
		glEnable(GL_BLEND);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		glOrtho(0.0f, getWidth(), getHeight(), 0.0f, 0.0f, 1.0f);

		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
	}

	protected void make3D() {
		glDisable(GL_BLEND);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity(); // Reset The Projection Matrix
		GLU.gluPerspective(90f, (float) width / (float) height, 0.05f, 1024);

		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		glLoadIdentity();
	}

	private long variableYieldTime, lastTime;

	protected void sync(int fps) {
		if (fps <= 0)
			return;

		long sleepTime = 1000000000 / fps;
		long yieldTime = Math.min(sleepTime, variableYieldTime + sleepTime % (1000 * 1000));
		long overSleep = 0;

		try {
			while (true) {
				long t = System.nanoTime() - lastTime;

				if (t < sleepTime - yieldTime) {
					Thread.sleep(1);
				} else if (t < sleepTime) {
					Thread.yield();
				} else {
					overSleep = t - sleepTime;
					break;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lastTime = System.nanoTime() - Math.min(overSleep, sleepTime);

			if (overSleep > variableYieldTime) {
				variableYieldTime = Math.min(variableYieldTime + 200 * 1000, sleepTime);
			} else if (overSleep < variableYieldTime - 200 * 1000) {
				variableYieldTime = Math.max(variableYieldTime - 2 * 1000, 0);
			}
		}
	}

	private int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;

		return delta;
	}

	private long getTime() {
		return (System.nanoTime() / 1000000);
	}

	public void updateFPS() {
		long d = getTime() - lastFPS;
		if (d > 1000) {
			currentFPS = fps;
			currentTPS = tps;
			System.out.println("FPS: " + currentFPS);
			System.out.println("TPS: " + currentTPS);
			fps = 0;
			tps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	private void initGL3() {
		make3D();
		glEnable(GL_TEXTURE_2D);
		glShadeModel(GL_SMOOTH);
		glClearDepth(1.0f);

		glEnable(GL_DEPTH_TEST);
		glDepthFunc(GL_LEQUAL);
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
		glEnable(GL_CULL_FACE);
		glEnable(GL_LINE_SMOOTH);

		glEnable(GL_ALPHA);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glEnable(GL_TEXTURE_2D);
	}

	public abstract String getTitle();

	public abstract String getVersion();

	public abstract void init();

	public abstract void update(int delta);

	public abstract void render();

	public abstract void close();

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
