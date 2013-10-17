package se.samuelandersson.lawyerrace.system;

import se.samuelandersson.lawyerrace.GlobalTime;
import se.samuelandersson.lawyerrace.component.Enemy;
import se.samuelandersson.lawyerrace.component.Movement;
import se.samuelandersson.lawyerrace.component.Spatial;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.managers.TagManager;
import com.artemis.systems.EntityProcessingSystem;

public class EnemyMovementSystem extends EntityProcessingSystem {

	@Mapper
	ComponentMapper<Spatial> sm;
	@Mapper
	ComponentMapper<Movement> mm;

	protected static final float DIAGONAL_MULTIPLIER = 0.7071068f;

	private Entity target;
	
	public EnemyMovementSystem() {
		super(Aspect.getAspectForAll(Enemy.class, Spatial.class, Movement.class));
	}
	
	@Override
	protected void begin() {
		target = world.getManager(TagManager.class).getEntity("PLAYER");
	}

	@Override
	protected void process(Entity e) {
		Spatial targetSpatial = sm.get(target);

		float delta = GlobalTime.getDelta();

		Spatial spatial = sm.get(e);
		Movement movement = mm.get(e);

		float targetX = targetSpatial.x;
		float targetY = targetSpatial.y;
		float deltaX = 0;
		float deltaY = 0;

		if (targetX < spatial.x) {
			movement.directionX = -1;
		} else if (targetX > spatial.x) {
			movement.directionX = 1;
		} else {
			movement.directionX = 0;
		}
		deltaX = movement.velocityX * movement.directionX * delta;

		if (targetY < spatial.y) {
			movement.directionY = -1;
		} else if (targetY > spatial.y) {
			movement.directionY = 1;
		} else {
			movement.directionY = 0;
		}
		deltaY = movement.velocityY * movement.directionY * delta;

		if (deltaX != 0 && deltaY != 0) {
			deltaX *= DIAGONAL_MULTIPLIER;
			deltaY *= DIAGONAL_MULTIPLIER;
		}

		if (spatial.x < targetX && spatial.x + deltaX > targetX) deltaX = targetX - spatial.x;
		if (spatial.y < targetY && spatial.y + deltaY > targetY) deltaY = targetY - spatial.y;
		spatial.x += deltaX;
		spatial.y += deltaY;
	}

}
