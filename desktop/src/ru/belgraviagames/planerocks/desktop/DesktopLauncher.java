package ru.belgraviagames.planerocks.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.belgraviagames.planerocks.PlaneRocks;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = PlaneRocks.WIDTH;
		config.height = PlaneRocks.HEIGHT;
		config.title = PlaneRocks.TITLE;
		new LwjglApplication(new PlaneRocks(), config);
	}
}
