package se.samuelandersson.lawyerrace;

import se.samuelandersson.lawyerrace.screen.GameScreen;

import com.badlogic.gdx.Screen;

public class LawyerRace extends Game {

	private Screen currentScreen;

	@Override
	public void create() {
		CoreRegistry.put(this);
		Assets.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		Assets.manager.dispose();
	}

}
