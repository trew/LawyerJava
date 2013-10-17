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
import com.badlogic.gdx.Gdx;

public class MovementSystem extends EntityProcessingSystem {

	@Mapper
	ComponentMapper<Spatial> sm;
	@Mapper
	ComponentMapper<Movement> mm;

	public MovementSystem() {
		super(Aspect.getAspectForAll(Spatial.class, Movement.class).exclude(Target.class));
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

	@Override
	protected void process(Entity e) {
		float delta = GlobalTime.getDelta();

		Spatial spatial = sm.get(e);
		Movement movement = mm.get(e);
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
