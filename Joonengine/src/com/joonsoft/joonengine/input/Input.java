package com.joonsoft.joonengine.input;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.*;

import com.joonsoft.joonengine.JoonengineGame;

public class Input {
	public boolean[] keys = new boolean[512];

	public static final int GLFW_KEY_SPACE = 0x20, GLFW_KEY_APOSTROPHE = 0x27, GLFW_KEY_COMMA = 0x2C, GLFW_KEY_MINUS = 0x2D, GLFW_KEY_PERIOD = 0x2E, GLFW_KEY_SLASH = 0x2F, GLFW_KEY_0 = 0x30, GLFW_KEY_1 = 0x31, GLFW_KEY_2 = 0x32, GLFW_KEY_3 = 0x33, GLFW_KEY_4 = 0x34, GLFW_KEY_5 = 0x35, GLFW_KEY_6 = 0x36, GLFW_KEY_7 = 0x37, GLFW_KEY_8 = 0x38, GLFW_KEY_9 = 0x39, GLFW_KEY_SEMICOLON = 0x3B, GLFW_KEY_EQUAL = 0x3D, GLFW_KEY_A = 0x41, GLFW_KEY_B = 0x42, GLFW_KEY_C = 0x43, GLFW_KEY_D = 0x44, GLFW_KEY_E = 0x45, GLFW_KEY_F = 0x46, GLFW_KEY_G = 0x47, GLFW_KEY_H = 0x48, GLFW_KEY_I = 0x49, GLFW_KEY_J = 0x4A, GLFW_KEY_K = 0x4B, GLFW_KEY_L = 0x4C, GLFW_KEY_M = 0x4D, GLFW_KEY_N = 0x4E, GLFW_KEY_O = 0x4F, GLFW_KEY_P = 0x50, GLFW_KEY_Q = 0x51, GLFW_KEY_R = 0x52, GLFW_KEY_S = 0x53, GLFW_KEY_T = 0x54, GLFW_KEY_U = 0x55, GLFW_KEY_V = 0x56, GLFW_KEY_W = 0x57, GLFW_KEY_X = 0x58, GLFW_KEY_Y = 0x59, GLFW_KEY_Z = 0x5A, GLFW_KEY_LEFT_BRACKET = 0x5B, GLFW_KEY_BACKSLASH = 0x5C, GLFW_KEY_RIGHT_BRACKET = 0x5D, GLFW_KEY_GRAVE_ACCENT = 0x60, GLFW_KEY_WORLD_1 = 0xA1, GLFW_KEY_WORLD_2 = 0xA2;

	public static final int GLFW_KEY_ESCAPE = 0x100, GLFW_KEY_ENTER = 0x101, GLFW_KEY_TAB = 0x102, GLFW_KEY_BACKSPACE = 0x103, GLFW_KEY_INSERT = 0x104, GLFW_KEY_DELETE = 0x105, GLFW_KEY_RIGHT = 0x106, GLFW_KEY_LEFT = 0x107, GLFW_KEY_DOWN = 0x108, GLFW_KEY_UP = 0x109, GLFW_KEY_PAGE_UP = 0x10A, GLFW_KEY_PAGE_DOWN = 0x10B, GLFW_KEY_HOME = 0x10C, GLFW_KEY_END = 0x10D, GLFW_KEY_CAPS_LOCK = 0x118, GLFW_KEY_SCROLL_LOCK = 0x119, GLFW_KEY_NUM_LOCK = 0x11A, GLFW_KEY_PRINT_SCREEN = 0x11B, GLFW_KEY_PAUSE = 0x11C, GLFW_KEY_F1 = 0x122, GLFW_KEY_F2 = 0x123, GLFW_KEY_F3 = 0x124, GLFW_KEY_F4 = 0x125, GLFW_KEY_F5 = 0x126, GLFW_KEY_F6 = 0x127, GLFW_KEY_F7 = 0x128, GLFW_KEY_F8 = 0x129, GLFW_KEY_F9 = 0x12A, GLFW_KEY_F10 = 0x12B, GLFW_KEY_F11 = 0x12C, GLFW_KEY_F12 = 0x12D, GLFW_KEY_F13 = 0x12E, GLFW_KEY_F14 = 0x12F, GLFW_KEY_F15 = 0x130, GLFW_KEY_F16 = 0x131, GLFW_KEY_F17 = 0x132, GLFW_KEY_F18 = 0x133, GLFW_KEY_F19 = 0x134, GLFW_KEY_F20 = 0x135, GLFW_KEY_F21 = 0x136, GLFW_KEY_F22 = 0x137, GLFW_KEY_F23 = 0x138, GLFW_KEY_F24 = 0x139, GLFW_KEY_F25 = 0x13A, GLFW_KEY_KP_0 = 0x140, GLFW_KEY_KP_1 = 0x141, GLFW_KEY_KP_2 = 0x142, GLFW_KEY_KP_3 = 0x143, GLFW_KEY_KP_4 = 0x144, GLFW_KEY_KP_5 = 0x145, GLFW_KEY_KP_6 = 0x146, GLFW_KEY_KP_7 = 0x147, GLFW_KEY_KP_8 = 0x148, GLFW_KEY_KP_9 = 0x149, GLFW_KEY_KP_DECIMAL = 0x14A, GLFW_KEY_KP_DIVIDE = 0x14B, GLFW_KEY_KP_MULTIPLY = 0x14C, GLFW_KEY_KP_SUBTRACT = 0x14D, GLFW_KEY_KP_ADD = 0x14E, GLFW_KEY_KP_ENTER = 0x14F, GLFW_KEY_KP_EQUAL = 0x150, GLFW_KEY_LEFT_SHIFT = 0x154, GLFW_KEY_LEFT_CONTROL = 0x155, GLFW_KEY_LEFT_ALT = 0x156, GLFW_KEY_LEFT_SUPER = 0x157, GLFW_KEY_RIGHT_SHIFT = 0x158, GLFW_KEY_RIGHT_CONTROL = 0x159, GLFW_KEY_RIGHT_ALT = 0x15A, GLFW_KEY_RIGHT_SUPER = 0x15B, GLFW_KEY_MENU = 0x15C, GLFW_KEY_LAST = GLFW_KEY_MENU;

	private int mouseX, mouseY;
	private int mouseDX, mouseDY;

	private boolean mouseGrabbed;

	private JoonengineGame game;

	public Input(JoonengineGame game) {
		this.game = game;
		mouseX = mouseY = mouseDX = mouseDY = 0;
		glfwSetCursorPosCallback(game.windowHandle, game.cursorPosCallback = new GLFWCursorPosCallback() {
			public void invoke(long window, double xpos, double ypos) {
				mouseDX += (int) xpos - mouseX;
				mouseDY -= (int) ypos - mouseY;
				mouseX = (int) xpos;
				mouseY = (int) ypos;
			}
		});

		glfwSetMouseButtonCallback(game.windowHandle, game.mouseButtonCallback = new GLFWMouseButtonCallback() {

			@Override
			public void invoke(long window, int button, int action, int mods) {
				System.out.println(button);
				if (action == GLFW.GLFW_PRESS)
					if (!isMouseGrabbed())
						setMouseGrabbed(true);
			}

		});
	}

	public int getMouseDX() {
		return mouseDX | (mouseDX = 0);
	}

	public int getMouseDY() {
		return mouseDY | (mouseDY = 0);
	}

	public boolean isKeyPressed(int key) {
		return keys[key];
	}

	public boolean isMouseGrabbed() {
		return mouseGrabbed;
	}

	public void setMouseGrabbed(boolean mouseGrabbed) {
		this.mouseGrabbed = mouseGrabbed;

		GLFW.glfwSetCursorPos(game.windowHandle, game.getWidth() / 2, game.getHeight() / 2);
		if (mouseGrabbed)
			glfwSetInputMode(game.windowHandle, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
		else
			glfwSetInputMode(game.windowHandle, GLFW_CURSOR, GLFW_CURSOR_NORMAL);

	}
}
