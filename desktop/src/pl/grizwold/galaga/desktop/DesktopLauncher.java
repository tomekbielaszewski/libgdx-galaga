package pl.grizwold.galaga.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pl.grizwold.galaga.GalagaGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		int width = 800;
		int height = 600;

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Galaga by Grizwold";
		config.width = width;
		config.height = height;

		new LwjglApplication(new GalagaGame(width, height), config);
	}
}
