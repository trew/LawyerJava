package se.samuelandersson.lawyerrace.system;

import se.samuelandersson.lawyerrace.component.CollisionComponent;
import se.samuelandersson.lawyerrace.utils.GdxUtils;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class DebugRenderSystem extends EntityProcessingSystem {

	protected ShapeRenderer renderer;
	protected OrthographicCamera camera;

	@Mapper
	ComponentMapper<CollisionComponent> cm;

	Vector2 v1 = new Vector2();
	Vector2 v2 = new Vector2();

	public DebugRenderSystem(OrthographicCamera camera) {
		super(Aspect.getAspectForAll(CollisionComponent.class));
		renderer = new ShapeRenderer();
		this.camera = camera;
	}

	@Override
	protected void begin() {
		renderer.setProjectionMatrix(camera.combined);
		renderer.begin(ShapeType.Line);
	}

	@Override
	protected void process(Entity e) {
		CollisionComponent c = cm.get(e);
		GdxUtils.drawPolygon(renderer, c.polygon);
	}

	@Override
	protected void end() {
		renderer.end();
	}

}
