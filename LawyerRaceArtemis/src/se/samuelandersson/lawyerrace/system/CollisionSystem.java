package se.samuelandersson.lawyerrace.system;

import se.samuelandersson.lawyerrace.LawyerRace;
import se.samuelandersson.lawyerrace.component.Player;
import se.samuelandersson.lawyerrace.component.Reward;
import se.samuelandersson.lawyerrace.component.Spatial;
import se.samuelandersson.lawyerrace.screen.GameOverScreen;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;

public class CollisionSystem extends EntitySystem {

	@Mapper
	ComponentMapper<Spatial> sm;
	@Mapper
	ComponentMapper<Player> pm;
	@Mapper
	ComponentMapper<Reward> rm;

	LawyerRace game;
	
	public CollisionSystem(LawyerRace game) {
		super(Aspect.getAspectForAll(Spatial.class));
		this.game = game;
	}

	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		for (int i = 0; i < entities.size(); i++) {
			for (int j = i + 1; j < entities.size(); j++) {
				Entity a = entities.get(i);
				Entity b = entities.get(j);
				
				Spatial sa = sm.get(a);
				Spatial sb = sm.get(b);

				if (sa.intersects(sb)) {
					collides(a, b);
				}
			}
		}
	}
	
	void collides(Entity a, Entity b) {
		// FIXME: this is a mess, how to do this properly??
		
		Entity rewardEntity = null;
		Reward r = rm.getSafe(a);
		if (r == null) {
			r = rm.getSafe(b);
			if (r != null) rewardEntity = b;
		} else rewardEntity = a;
		Player p = pm.getSafe(a);
		if (p == null) p = pm.getSafe(b);
		
		if (r == null && p != null) {
			// player collided with enemy, kill
			game.setScreen(new GameOverScreen(game));
		} else if (r != null && p != null) {
			// player collided with something that gave points
			p.score += r.points;
			rewardEntity.deleteFromWorld();
		}
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

}
