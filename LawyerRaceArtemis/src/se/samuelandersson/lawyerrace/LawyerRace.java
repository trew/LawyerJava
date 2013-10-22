package se.samuelandersson.lawyerrace;

import se.samuelandersson.lawyerrace.screen.GameScreen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class LawyerRace extends Game {

	private Screen currentScreen;
	public static final boolean DEBUG = true;

	@Override
	public void create() {
		CoreRegistry.put(this);
		CoreRegistry.put(new SpriteBatch());
		Assets.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		Assets.manager.dispose();
	}

}
