package pl.grizwold.space.invaders.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pl.grizwold.space.invaders.SpaceInvadersGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		int width = 800;
		int height = 600;

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Space Invaders by Grizwold";
		config.width = width;
		config.height = height;

		new LwjglApplication(new SpaceInvadersGame(width, height), config);
	}
}
