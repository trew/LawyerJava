package se.samuelandersson.lawyerrace.system;

import se.samuelandersson.lawyerrace.entity.EntityFactory;
import se.samuelandersson.lawyerrace.entity.Group;

import com.artemis.Entity;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.artemis.systems.VoidEntitySystem;
import com.artemis.utils.ImmutableBag;

public class EnemySpawnerSystem extends VoidEntitySystem {

	private ImmutableBag<Entity> enemies;

	public EnemySpawnerSystem() {
	}

	@Override
	protected void initialize() {
		enemies = world.getManager(GroupManager.class).getEntities(Group.ENEMY);
		
	}
	
	@Override
	protected void processSystem() {
		int enemiesToCreate = 1 - enemies.size();
		for (int i = 0; i < enemiesToCreate; i++) {
			Entity target = world.getManager(TagManager.class).getEntity("PLAYER");
			EntityFactory.createEnemy(world, target);
		}
	}

}
