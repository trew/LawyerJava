package se.samuelandersson.lawyerrace;

import se.samuelandersson.lawyerrace.screen.Screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

public abstract class Game implements ApplicationListener {
	private Screen screen;
	private float accumulator;
	public static final float TIMESTEP = 1 / 60f;
	public static final float MAX_FRAMETIME = 1 / 4f;

	@Override
	public void dispose() {
		if (screen != null) screen.hide();
	}

	@Override
	public void pause() {
		if (screen != null) screen.pause();
	}

	@Override
	public void resume() {
		if (screen != null) screen.resume();
	}

	@Override
	public void render() {
		Screen screen = getScreen();
		if (screen != null) {
			Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);
			Gdx.gl10.glClearColor(0, 0, 0, 0);

			float delta = Gdx.graphics.getRawDeltaTime();
			GlobalTime.setDelta(delta);
			if (delta > MAX_FRAMETIME) delta = MAX_FRAMETIME;
			accumulator += delta;
			while (accumulator >= TIMESTEP) {
				screen.update(delta);
				accumulator -= TIMESTEP;
			}
			screen.render();
		}
	}

	@Override
	public void resize(int width, int height) {
		if (screen != null) screen.resize(width, height);
	}

	/**
	 * Sets the current screen. {@link Screen#hide()} is called on any old screen, and {@link Screen#show()} is called on the new
	 * screen, if any.
	 * 
	 * @param screen may be {@code null}
	 */
	public void setScreen(Screen screen) {
		if (this.screen != null) this.screen.hide();
		this.screen = screen;
		if (this.screen != null) {
			this.screen.show();
			this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	}

	/** @return the currently active {@link Screen}. */
	public Screen getScreen() {
		return screen;
	}

}
