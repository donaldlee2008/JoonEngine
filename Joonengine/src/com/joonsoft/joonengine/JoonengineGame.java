package com.joonsoft.joonengine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.io.*;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import com.joonsoft.joonengine.glu.*;
import com.joonsoft.joonengine.input.*;
import com.joonsoft.joonengine.renderer.*;

public abstract class JoonengineGame {

	protected Renderer renderer;

	private int width = 1280;
	private int height = width / 16 * 9;

	private GLFWErrorCallback errorCallback;
	private GLFWKeyCallback keyCallback;
	public GLFWCursorPosCallback cursorPosCallback;

	public long windowHandle;

	protected Input input;

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
		if (windowHandle == NULL) throw new RuntimeException("Failed to create the GLFW window");

		input = new Input(this);

		glfwSetKeyCallback(windowHandle, keyCallback = new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				if (action == GLFW_PRESS) {
					input.keys[key] = true;
				} else if (action == GLFW_RELEASE) {
					input.keys[key] = false;
				}
				if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) glfwSetWindowShouldClose(window, GL_TRUE);
			}
		});

		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		glfwSetWindowPos(windowHandle, (vidmode.getWidth() - width) / 2, (vidmode.getHeight() - height) / 2);

		glfwMakeContextCurrent(windowHandle);

		glfwSwapInterval(1);

		glfwShowWindow(windowHandle);

		glfwSetInputMode(windowHandle, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

		GL.createCapabilities();

		renderer = new Renderer();

		init();

		initGL3();

		while (glfwWindowShouldClose(windowHandle) == GL_FALSE) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			glLoadIdentity();

			update();
			render();

			glfwSwapBuffers(windowHandle);

			glfwPollEvents();
		}

		close();

		glfwDestroyWindow(windowHandle);
		keyCallback.release();

		glfwTerminate();
		errorCallback.release();
	}

	private void initGL3() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();

		GLU.gluPerspective(70f, (float) width / (float) height, 0.3f, 2555);
		glMatrixMode(GL_MODELVIEW);
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

	public abstract void update();

	public abstract void render();

	public abstract void close();
}
