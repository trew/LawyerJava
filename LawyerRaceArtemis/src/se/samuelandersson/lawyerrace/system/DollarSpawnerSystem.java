package se.samuelandersson.lawyerrace.system;

import se.samuelandersson.lawyerrace.component.RewardComponent;
import se.samuelandersson.lawyerrace.entity.EntityFactory;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;

public class DollarSpawnerSystem extends EntitySystem {

	public DollarSpawnerSystem() {
		super(Aspect.getAspectForAll(RewardComponent.class));
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		int dollarsToSpawn = 1 - entities.size();
		for (int i = 0; i < dollarsToSpawn; i++) {
			EntityFactory.createDollar(world).addToWorld();
		}
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

}
