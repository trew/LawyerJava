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

public class DebugRenderSystem extends EntityProcessingSystem {

	protected ShapeRenderer renderer;
	protected OrthographicCamera camera;

	@Mapper
	ComponentMapper<CollisionComponent> cm;

	public DebugRenderSystem(OrthographicCamera camera) {
		super(Aspect.getAspectForAll(CollisionComponent.class));
		this.camera = camera;
		renderer = new ShapeRenderer();
	}

	@Override
	protected void begin() {
		renderer.setProjectionMatrix(camera.combined);
		renderer.begin(ShapeType.Line);
	}

	@Override
	protected void process(Entity e) {
		CollisionComponent c = cm.getSafe(e);
		if (c != null) GdxUtils.drawPolygon(renderer, c.polygon);
	}

	@Override
	protected void end() {
		renderer.end();
	}

}
