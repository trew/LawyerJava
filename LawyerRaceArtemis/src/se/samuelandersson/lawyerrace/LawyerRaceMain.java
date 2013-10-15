package se.samuelandersson.lawyerrace;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class LawyerRaceMain {

	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Lawyer Race";
		cfg.width = 800;
		cfg.height = 600;
		new LwjglApplication(new LawyerRace(), cfg);
	}

}
