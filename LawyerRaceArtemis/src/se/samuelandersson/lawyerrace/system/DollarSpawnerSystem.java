package se.samuelandersson.lawyerrace.system;

import se.samuelandersson.lawyerrace.component.Reward;
import se.samuelandersson.lawyerrace.entity.EntityFactory;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;

public class DollarSpawnerSystem extends EntitySystem {

	public DollarSpawnerSystem() {
	   super(Aspect.getAspectForAll(Reward.class));
   }

	@Override
   protected void processEntities(ImmutableBag<Entity> entities) {
		int entitiesSize = entities.size();
		while (entitiesSize <= 0) {
			EntityFactory.createDollar(world).addToWorld();
			entitiesSize++; // simulate the bag growing
		}
   }

	@Override
   protected boolean checkProcessing() {
		return true;
   }

}
