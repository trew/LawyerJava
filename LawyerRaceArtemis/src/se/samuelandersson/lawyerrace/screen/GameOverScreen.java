package se.samuelandersson.lawyerrace.screen;

import se.samuelandersson.lawyerrace.LawyerRace;
import se.samuelandersson.lawyerrace.utils.GdxUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverScreen implements Screen {

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private BitmapFont font;

	public GameOverScreen(LawyerRace game) {
		camera = new OrthographicCamera();
		batch = new SpriteBatch();
		font = new BitmapFont();
	}

	public void update(float delta) {
	}

	@Override
	public void render() {
		camera.update();
		batch.begin();
		GdxUtils.drawCenteredAt(batch, font, "Game Over!", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, width, height);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
	}

}
