package se.samuelandersson.lawyerrace.system;

import se.samuelandersson.lawyerrace.component.Spatial;
import se.samuelandersson.lawyerrace.component.SpriteComponent;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class DebugRenderSystem extends EntityProcessingSystem {

	protected ShapeRenderer renderer;
	protected OrthographicCamera camera;
	
	@Mapper
	ComponentMapper<SpriteComponent> sm;
	
	public DebugRenderSystem (OrthographicCamera camera) {
		super(Aspect.getAspectForAll(SpriteComponent.class, Spatial.class));
		renderer = new ShapeRenderer();
		this.camera = camera;
	}

	@Override
	protected void begin () {
		renderer.setProjectionMatrix(camera.combined);
		renderer.begin(ShapeType.Rectangle);
	}
	
	@Override
	protected void process (Entity e) {
		SpriteComponent s = sm.get(e);
		Sprite sprite = s.sprite;
		renderer.rect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
	}
	
	@Override
	protected void end () {
		renderer.end();
	}

}
