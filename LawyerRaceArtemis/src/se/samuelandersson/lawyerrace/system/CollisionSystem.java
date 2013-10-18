package se.samuelandersson.lawyerrace.system;

import se.samuelandersson.lawyerrace.LawyerRace;
import se.samuelandersson.lawyerrace.actions.Actions;
import se.samuelandersson.lawyerrace.actions.SequentialAction;
import se.samuelandersson.lawyerrace.component.Player;
import se.samuelandersson.lawyerrace.component.Reward;
import se.samuelandersson.lawyerrace.component.Spatial;
import se.samuelandersson.lawyerrace.entity.Group;
import se.samuelandersson.lawyerrace.screen.GameOverScreen;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.managers.GroupManager;
import com.artemis.systems.VoidEntitySystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.utils.Array;

public class CollisionSystem extends VoidEntitySystem {

	private class CollisionGroup {
		private ImmutableBag<Entity> groupA;
		private ImmutableBag<Entity> groupB;
		private CollisionHandler handler;

		public CollisionGroup(String groupA, String groupB, CollisionHandler handler) {
			GroupManager gm = world.getManager(GroupManager.class);
			this.groupA = gm.getEntities(groupA);
			this.groupB = gm.getEntities(groupB);
			this.handler = handler;
		}

		public void checkCollisions() {
			for (int i = 0; i < groupA.size(); i++) {
				Entity a = groupA.get(i);
				Spatial as = sm.getSafe(a);
				if (as == null) continue;

				for (int j = 0; j < groupB.size(); j++) {
					Entity b = groupB.get(j);
					Spatial bs = sm.getSafe(b);
					if (bs == null) continue;

					if (as.intersects(bs)) handler.handleCollision(a, b);
				}
			}
		}
	}

	private interface CollisionHandler {
		void handleCollision(Entity a, Entity b);
	}

	@Mapper
	ComponentMapper<Spatial> sm;
	@Mapper
	ComponentMapper<Player> pm;
	@Mapper
	ComponentMapper<Reward> rm;

	private LawyerRace game;
	private Array<CollisionGroup> groups;

	public CollisionSystem(LawyerRace game) {
		this.game = game;
	}

	@Override
	protected void initialize() {
		groups = new Array<CollisionGroup>();
		
		groups.add(new CollisionGroup(Group.PLAYER, Group.ENEMY, new CollisionHandler() {
			@Override
			public void handleCollision(Entity a, Entity b) {
				game.setScreen(new GameOverScreen(game));
			}
		}));
		
		groups.add(new CollisionGroup(Group.PLAYER, Group.DOLLAR, new CollisionHandler() {
			@Override
			public void handleCollision(Entity a, Entity b) {
				Reward r = rm.getSafe(b);
				if (r == null) return;
				Player p = pm.get(a);
				p.score += r.points;
				b.removeComponent(r);
				SequentialAction seq = Actions.sequence();
				seq.addAction(Actions.scaleTo(1.2f, 1.2f, 0.1f)).addAction(Actions.scaleTo(0, 0, 0.1f)).addAction(Actions.remove());
				world.getSystem(ActionsSystem.class).addAction(b, seq);
				b.changedInWorld();
			}
		}));
	}

	@Override
   protected void processSystem() {
		for (CollisionGroup group : groups) {
			group.checkCollisions();
		}
   }

	@Override
	protected boolean checkProcessing() {
		return true;
	}

}
