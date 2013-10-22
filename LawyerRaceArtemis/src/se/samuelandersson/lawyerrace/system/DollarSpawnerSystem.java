package se.samuelandersson.lawyerrace.system;

import se.samuelandersson.lawyerrace.entity.EntityFactory;
import se.samuelandersson.lawyerrace.entity.Group;

import com.artemis.Entity;
import com.artemis.managers.GroupManager;
import com.artemis.systems.VoidEntitySystem;
import com.artemis.utils.ImmutableBag;

public class DollarSpawnerSystem extends VoidEntitySystem {

	private ImmutableBag<Entity> dollars;

	@Override
	protected void initialize() {
		dollars = world.getManager(GroupManager.class).getEntities(Group.DOLLAR);
	}
	
	@Override
   protected void processSystem() {
		int dollarsToSpawn = 1 - dollars.size();
		for (int i = 0; i < dollarsToSpawn; i++) {
			EntityFactory.createDollar(world).addToWorld();
		}
   }

}
