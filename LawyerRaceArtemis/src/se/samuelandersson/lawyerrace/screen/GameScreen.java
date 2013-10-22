package se.samuelandersson.lawyerrace.screen;

import se.samuelandersson.lawyerrace.LawyerRace;
import se.samuelandersson.lawyerrace.entity.EntityFactory;
import se.samuelandersson.lawyerrace.system.ActionsSystem;
import se.samuelandersson.lawyerrace.system.CollisionSystem;
import se.samuelandersson.lawyerrace.system.DebugRenderSystem;
import se.samuelandersson.lawyerrace.system.DollarSpawnerSystem;
import se.samuelandersson.lawyerrace.system.EnemySpawnerSystem;
import se.samuelandersson.lawyerrace.system.MovementSystem;
import se.samuelandersson.lawyerrace.system.PlayerInputSystem;
import se.samuelandersson.lawyerrace.system.EntityRenderSystem;
import se.samuelandersson.lawyerrace.system.EnemyMovementSystem;
import se.samuelandersson.lawyerrace.system.UIRenderSystem;

import com.artemis.EntitySystem;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen {

	private World world;

	private Array<EntitySystem> updateSystems;
	private Array<EntitySystem> renderSystems;

	private OrthographicCamera camera;

	public GameScreen() {
		camera = new OrthographicCamera();
		camera.setToOrtho(true);
		updateSystems = new Array<EntitySystem>();
		renderSystems = new Array<EntitySystem>();

		world = new World();

		world.setManager(new GroupManager());
		world.setManager(new TagManager());

		updateSystems.add(world.setSystem(new ActionsSystem(), true));
		updateSystems.add(world.setSystem(new MovementSystem(), true));
		updateSystems.add(world.setSystem(new EnemyMovementSystem(), true));
		updateSystems.add(world.setSystem(new CollisionSystem(), true));
		updateSystems.add(world.setSystem(new DollarSpawnerSystem(), true));
		updateSystems.add(world.setSystem(new EnemySpawnerSystem(), true));

		renderSystems.add(world.setSystem(new EntityRenderSystem(camera), true));
		if (LawyerRace.DEBUG) renderSystems.add(world.setSystem(new DebugRenderSystem(camera), true));
		renderSystems.add(world.setSystem(new UIRenderSystem(), true));

		Gdx.input.setInputProcessor(world.setSystem(new PlayerInputSystem()));

		world.initialize();
		EntityFactory.createPlayer(world).addToWorld();
	}

	public void update(float delta) {
		world.setDelta(delta);
		world.process();

		for (EntitySystem system : updateSystems) {
			system.process();
		}
	}

	@Override
	public void render() {
		for (EntitySystem system : renderSystems) {
			system.process();
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
		// not called
	}

	@Override
	public void resume() {
		// not called
	}

	@Override
	public void dispose() {
	}

}
