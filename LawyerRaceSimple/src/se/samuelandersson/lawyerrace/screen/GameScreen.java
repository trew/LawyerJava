package se.samuelandersson.lawyerrace.screen;

import se.samuelandersson.lawyerrace.Assets;
import se.samuelandersson.lawyerrace.LawyerRace;
import se.samuelandersson.lawyerrace.entity.Dollar;
import se.samuelandersson.lawyerrace.entity.Enemy;
import se.samuelandersson.lawyerrace.entity.Entity;
import se.samuelandersson.lawyerrace.entity.MovingEntity;
import se.samuelandersson.lawyerrace.entity.Player;
import se.samuelandersson.lawyerrace.tools.Intersector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen {

	private OrthographicCamera camera;
	private Player player;
	private Stage stage;
	private SpriteBatch batch;
	private Array<Dollar> dollars;
	private Array<Enemy> enemies;
	private EventListener playerInput;
	
	private final LawyerRace game;

	public GameScreen(LawyerRace game) {
		this.game = game;
		dollars = new Array<Dollar>();
		enemies = new Array<Enemy>();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(true);
		stage = new Stage();
		stage.setCamera(camera);
		player = new Player();
		player.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		stage.addActor(player);

		Gdx.input.setInputProcessor(stage);
		playerInput = new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if (keycode == Keys.LEFT)
					player.setDirection(MovingEntity.LEFT);
				else if (keycode == Keys.RIGHT)
					player.setDirection(MovingEntity.RIGHT);
				else if (keycode == Keys.UP)
					player.setDirection(MovingEntity.UP);
				else if (keycode == Keys.DOWN)
					player.setDirection(MovingEntity.DOWN);
				else if (keycode == Keys.SPACE)
					player.setMoving(!player.isMoving());
				else
					return false;
				return true;
			}
		};
		stage.addCaptureListener(playerInput);

		Enemy e = new Enemy();
		enemies.add(e);
		e.setTarget(player);
		stage.addActor(e);
	}

	public void update(float delta) {
		stage.act(delta);
		checkCollision();
		createDollar();
	}

	private void checkCollision() {
		for (int i = 0; i < enemies.size; i++) {
			Enemy enemy = enemies.get(i);
			if (Intersector.intersects(player, enemy)) {
				player.setDead(true);
				stage.removeCaptureListener(playerInput);
				game.setScreen(new GameOverScreen(game));
			}
		}
		
		for (int i = 0; i < dollars.size; i++) {
			Dollar dollar = dollars.get(i);
			if (!dollar.isActive()) continue;
			if (Intersector.intersects(player, dollar)) {
				player.setScore(player.getScore() + 1);
				dollar.setActive(false);
				dollar.addAction(Actions.sequence(Actions.scaleTo(1.2f, 1.2f, 0.1f), Actions.scaleTo(0, 0, 0.1f), Actions.removeActor()));
				dollars.removeIndex(i--);
			}
		}
	}

	private void createDollar() {
		if (dollars.size <= 0) {
			Dollar dollar = new Dollar();
			float dollarX = MathUtils.random(0, Gdx.graphics.getWidth() - dollar.getWidth());
			float dollarY = MathUtils.random(0, Gdx.graphics.getHeight() - dollar.getHeight());
			dollar.setPosition(dollarX, dollarY);
			dollars.add(dollar);
			stage.addActor(dollar);
		}
	}

	@Override
	public void render() {
		camera.update();
		stage.draw();
		if (LawyerRace.DEBUG) renderDebug();
		batch.begin();
		Assets.getFont().draw(batch, Integer.toString(player.getScore()), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 10);
		batch.end();
	}
	
	public void renderDebug() {
		for (Actor e : stage.getActors()) {
			if (e instanceof Entity) {
				((Entity)e).drawDebug();
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(true, width, height);
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
