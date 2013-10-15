package se.samuelandersson.lawyerrace.system;

import se.samuelandersson.lawyerrace.GlobalTime;
import se.samuelandersson.lawyerrace.component.Movement;
import se.samuelandersson.lawyerrace.component.Spatial;
import se.samuelandersson.lawyerrace.component.Target;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;

public class TargetMovementSystem extends EntityProcessingSystem {

	@Mapper
	ComponentMapper<Spatial> sm;
	@Mapper
	ComponentMapper<Movement> mm;
	@Mapper
	ComponentMapper<Target> tm;

	protected static final float DIAGONAL_MULTIPLIER = 0.7071068f;

	public TargetMovementSystem() {
		super(Aspect.getAspectForAll(Spatial.class, Movement.class, Target.class));
	}

	@Override
	protected void process(Entity e) {
		Target target = tm.get(e);
		if (target.target == null) return;
		Spatial targetSpatial = sm.getSafe(target.target);
		if (targetSpatial == null) return;

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
