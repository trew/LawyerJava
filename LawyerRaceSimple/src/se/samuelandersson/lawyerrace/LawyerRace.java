package se.samuelandersson.lawyerrace;

import se.samuelandersson.lawyerrace.screen.GameScreen;

import com.badlogic.gdx.Screen;

public class LawyerRace extends Game {

	private Screen currentScreen;

	@Override
	public void create() {
		Assets.load();
		setScreen(new GameScreen(this));
	}

	@Override
	public void dispose() {
		Assets.manager.dispose();
	}

}
