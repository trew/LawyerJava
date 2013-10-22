package se.samuelandersson.lawyerrace.system;

import se.samuelandersson.lawyerrace.component.EnemyComponent;
import se.samuelandersson.lawyerrace.component.MovementComponent;
import se.samuelandersson.lawyerrace.component.SpatialComponent;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;

public class MovementSystem extends EntityProcessingSystem {

	@Mapper
	ComponentMapper<SpatialComponent> sm;
	@Mapper
	ComponentMapper<MovementComponent> mm;

	public MovementSystem() {
		super(Aspect.getAspectForAll(SpatialComponent.class, MovementComponent.class).exclude(EnemyComponent.class));
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

	@Override
	protected void process(Entity e) {
		float delta = world.getDelta();

		SpatialComponent spatial = sm.get(e);
		MovementComponent movement = mm.get(e);
		if (!movement.moving) return;

		spatial.x += movement.velocityX * movement.directionX * delta;
		spatial.y += movement.velocityY * movement.directionY * delta;

		float maxX = Gdx.graphics.getWidth() - spatial.width;
		float maxY = Gdx.graphics.getHeight() - spatial.height;
		float minX = 0;
		float minY = 0;
		if (spatial.x > maxX) spatial.x = maxX;
		if (spatial.y > maxY) spatial.y = maxY;
		if (spatial.x < minX) spatial.x = minX;
		if (spatial.y < minX) spatial.y = minY;
	}

}
