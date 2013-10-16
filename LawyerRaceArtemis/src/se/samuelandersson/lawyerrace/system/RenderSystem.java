package se.samuelandersson.lawyerrace.system;

import se.samuelandersson.lawyerrace.component.Movement;
import se.samuelandersson.lawyerrace.component.Spatial;
import se.samuelandersson.lawyerrace.component.TextureRegion;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderSystem extends EntityProcessingSystem {

	@Mapper
	ComponentMapper<Spatial> sm;
	@Mapper
	ComponentMapper<TextureRegion> rm;
	@Mapper
	ComponentMapper<Movement> mm;

	private SpriteBatch batch;
	private OrthographicCamera camera;

	public RenderSystem() {
		super(Aspect.getAspectForAll(Spatial.class, TextureRegion.class));
	}

	@Override
	protected void initialize() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
	}

	@Override
	protected void begin() {
		camera.update();
		batch.begin();
	}

	@Override
	protected void process(Entity entity) {
		Spatial s = sm.get(entity);
		TextureRegion r = rm.get(entity);
		Movement m = mm.getSafe(entity);
		float rotation = 0;
		if (m != null) {
			rotation = getDirectionMultiplier(m) * 45;
		}
		batch.draw(r.region, s.x, s.y, s.width / 2, s.height / 2, s.width, s.height, 1, 1, rotation);
	}

	public int getDirectionMultiplier(Movement m) {
		if (m.directionX == 1 && m.directionY == 0) return 0;
		if (m.directionX == 1 && m.directionY == 1) return 1;
		if (m.directionX == 0 && m.directionY == 1) return 2;
		if (m.directionX == -1 && m.directionY == 1) return 3;
		if (m.directionX == -1 && m.directionY == 0) return 4;
		if (m.directionX == -1 && m.directionY == -1) return 5;
		if (m.directionX == 0 && m.directionY == -1) return 6;
		if (m.directionX == 1 && m.directionY == -1) return 7;
		return 0;
	}

	@Override
	protected void end() {
		batch.end();
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

}
