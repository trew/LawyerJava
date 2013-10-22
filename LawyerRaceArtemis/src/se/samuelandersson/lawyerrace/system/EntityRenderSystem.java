package se.samuelandersson.lawyerrace.system;

import se.samuelandersson.lawyerrace.component.MovementComponent;
import se.samuelandersson.lawyerrace.component.SpatialComponent;
import se.samuelandersson.lawyerrace.component.TextureRegionComponent;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EntityRenderSystem extends EntityProcessingSystem {

	@Mapper
	ComponentMapper<SpatialComponent> sm;
	@Mapper
	ComponentMapper<TextureRegionComponent> rm;
	@Mapper
	ComponentMapper<MovementComponent> mm;

	private SpriteBatch batch;
	private OrthographicCamera camera;

	public EntityRenderSystem(OrthographicCamera camera) {
		super(Aspect.getAspectForAll(SpatialComponent.class, TextureRegionComponent.class));
		this.camera = camera;
	}

	@Override
	protected void initialize() {
		batch = new SpriteBatch();
	}

	@Override
	protected void begin() {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
	}

	@Override
	protected void process(Entity entity) {
		SpatialComponent s = sm.get(entity);
		TextureRegionComponent r = rm.get(entity);

		MovementComponent m = mm.getSafe(entity);
		float rotation = 0;
		if (m != null) rotation += getDirectionMultiplier(m) * 45;
		s.angle = rotation;
		batch.draw(r.region, s.x, s.y, s.width / 2, s.height / 2, s.width, s.height, s.scaleX, s.scaleY, s.angle);
	}

	public int getDirectionMultiplier(MovementComponent m) {
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
