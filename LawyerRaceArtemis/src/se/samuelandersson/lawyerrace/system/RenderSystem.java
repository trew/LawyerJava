package se.samuelandersson.lawyerrace.system;

import se.samuelandersson.lawyerrace.component.Movement;
import se.samuelandersson.lawyerrace.component.Spatial;
import se.samuelandersson.lawyerrace.component.TextureRegion;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderSystem extends EntitySystem {

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
	protected void processEntities(ImmutableBag<Entity> entities) {
		camera.update();
		batch.begin();
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			
			Spatial s = sm.get(entity);
			TextureRegion r = rm.get(entity);
			Movement m = mm.getSafe(entity);
			float rotation = 0;
			if (m != null) {
				rotation = m.getDirectionMultiplier() * 45;
			}
			batch.draw(r.region, s.x, s.y, s.width / 2, s.height / 2, s.width, s.height, 1, 1, rotation);
		}
		batch.end();
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

}
