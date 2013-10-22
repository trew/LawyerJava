package se.samuelandersson.lawyerrace.system;

import se.samuelandersson.lawyerrace.component.EnemyComponent;
import se.samuelandersson.lawyerrace.component.MovementComponent;
import se.samuelandersson.lawyerrace.component.SpatialComponent;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.managers.TagManager;
import com.artemis.systems.EntityProcessingSystem;

public class EnemyMovementSystem extends EntityProcessingSystem {

	@Mapper
	ComponentMapper<SpatialComponent> sm;
	@Mapper
	ComponentMapper<MovementComponent> mm;

	protected static final float DIAGONAL_MULTIPLIER = 0.7071068f;

	private Entity target;

	public EnemyMovementSystem() {
		super(Aspect.getAspectForAll(EnemyComponent.class, SpatialComponent.class, MovementComponent.class));
	}

	@Override
	protected void begin() {
		target = world.getManager(TagManager.class).getEntity("PLAYER");
	}

	@Override
	protected void process(Entity e) {
		MovementComponent targetMovement = mm.get(target);
		if (!targetMovement.moving) return;

		SpatialComponent targetSpatial = sm.get(target);

		float delta = world.getDelta();

		SpatialComponent spatial = sm.get(e);
		MovementComponent movement = mm.get(e);

		float targetX = targetSpatial.x;
		float targetY = targetSpatial.y;
		float deltaX = 0;
		float deltaY = 0;

		if (targetX < spatial.x)
			deltaX = -movement.velocityX * delta;
		else if (targetX > spatial.x) deltaX = movement.velocityX * delta;

		if (targetY < spatial.y)
			deltaY = -movement.velocityY * delta;
		else if (targetY > spatial.y) deltaY = movement.velocityY * delta;

		if (deltaX != 0 && deltaY != 0) {
			deltaX *= DIAGONAL_MULTIPLIER;
			deltaY *= DIAGONAL_MULTIPLIER;
		}

		if (spatial.x < targetX && spatial.x + deltaX > targetX) deltaX = targetX - spatial.x;
		if (spatial.y < targetY && spatial.y + deltaY > targetY) deltaY = targetY - spatial.y;
		spatial.x += deltaX;
		spatial.y += deltaY;

		if (targetX < spatial.x) {
			movement.directionX = -1;
		} else if (targetX > spatial.x) {
			movement.directionX = 1;
		} else {
			movement.directionX = 0;
		}
		if (targetY < spatial.y) {
			movement.directionY = -1;
		} else if (targetY > spatial.y) {
			movement.directionY = 1;
		} else {
			movement.directionY = 0;
		}
	}

}
