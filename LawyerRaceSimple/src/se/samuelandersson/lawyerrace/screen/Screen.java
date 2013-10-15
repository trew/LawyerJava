package se.samuelandersson.lawyerrace.screen;

import com.badlogic.gdx.ApplicationListener;

/**
 * <p>
 * Represents one of many application screens, such as a main menu, a settings menu, the game screen and so on.
 * </p>
 * <p>
 * Note that {@link #dispose()} is not called automatically.
 * </p>
 * 
 * @see Game
 */
public interface Screen {

	public void update(float delta);

	/** Called when the screen should render itself. */
	public void render();

	/** @see ApplicationListener#resize(int, int) */
	public void resize(int width, int height);

	/** Called when this screen becomes the current screen for a {@link Game}. */
	public void show();

	/** Called when this screen is no longer the current screen for a {@link Game}. */
	public void hide();

	/** @see ApplicationListener#pause() */
	public void pause();

	/** @see ApplicationListener#resume() */
	public void resume();

	/** Called when this screen should release all resources. */
	public void dispose();
}
