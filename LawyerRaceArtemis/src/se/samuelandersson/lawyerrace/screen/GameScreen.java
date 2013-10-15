package se.samuelandersson.lawyerrace.screen;

import se.samuelandersson.lawyerrace.LawyerRace;
import se.samuelandersson.lawyerrace.entity.EntityFactory;
import se.samuelandersson.lawyerrace.system.MovementSystem;
import se.samuelandersson.lawyerrace.system.PlayerInputSystem;
import se.samuelandersson.lawyerrace.system.RenderSystem;
import se.samuelandersson.lawyerrace.system.TargetMovementSystem;

import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen {

	private World world;

	private Array<EntitySystem> updateSystems;
	private Array<EntitySystem> renderSystems;

	private PlayerInputSystem playerInput;

	private final LawyerRace game;

	public GameScreen(LawyerRace game) {
		this.game = game;
		updateSystems = new Array<EntitySystem>();
		renderSystems = new Array<EntitySystem>();
		
		world = new World();

		updateSystems.add(world.setSystem(new MovementSystem(), true));
		updateSystems.add(world.setSystem(new TargetMovementSystem(), true));
		renderSystems.add(world.setSystem(new RenderSystem(), true));

		PlayerInputSystem input = world.setSystem(new PlayerInputSystem());
		Gdx.input.setInputProcessor(input);
		
		world.initialize();
		
		Entity player = EntityFactory.createPlayer(world);
		player.addToWorld();
		EntityFactory.createEnemy(world, player).addToWorld();
	}

	public void update(float delta) {
		world.process();
		
		for (EntitySystem system : updateSystems) {
			system.process();
		}
	}

	private void checkCollision() {
		// for (int i = 0; i < enemies.size; i++) {
		// Enemy enemy = enemies.get(i);
		// if (Intersector.intersects(player, enemy)) {
		// player.setDead(true);
		// stage.removeCaptureListener(playerInput);
		// game.setScreen(new GameOverScreen(game));
		// }
		// }
		//
		// for (int i = 0; i < dollars.size; i++) {
		// Dollar dollar = dollars.get(i);
		// if (Intersector.intersects(player, dollar)) {
		// player.setScore(player.getScore() + 1);
		// dollar.remove();
		// dollars.removeIndex(i--);
		// }
		// }
	}

	private void createDollar() {
		// if (dollars.size <= 0) {
		// Dollar dollar = new Dollar();
		// float dollarX = MathUtils.random(0, Gdx.graphics.getWidth() - dollar.getWidth());
		// float dollarY = MathUtils.random(0, Gdx.graphics.getHeight() - dollar.getHeight());
		// dollar.setPosition(dollarX, dollarY);
		// dollars.add(dollar);
		// stage.addActor(dollar);
		// }
	}

	@Override
	public void render() {
		for (EntitySystem system : renderSystems) {
			system.process();
		}
	}

	@Override
	public void resize(int width, int height) {
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
