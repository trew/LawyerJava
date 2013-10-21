package se.samuelandersson.lawyerrace.system;

import se.samuelandersson.lawyerrace.CoreRegistry;
import se.samuelandersson.lawyerrace.LawyerRace;
import se.samuelandersson.lawyerrace.actions.Actions;
import se.samuelandersson.lawyerrace.actions.SequenceAction;
import se.samuelandersson.lawyerrace.component.ActionComponent;
import se.samuelandersson.lawyerrace.component.Collision;
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
		
		public void updateCollisionBoundaries() {
			updateGroupBoundaries(groupA);
			updateGroupBoundaries(groupB);
		}
		
		private void updateGroupBoundaries(ImmutableBag<Entity> group) {
			for (int i = 0; i < group.size(); i++) {
				Entity e = group.get(i);
				Collision c = cm.getSafe(e);
				Spatial s = sm.getSafe(e);
				if (c != null && s != null) {
					c.polygon.setPosition(s.x, s.y);
					c.polygon.setRotation(s.angle);
					c.polygon.setScale(s.scaleX, s.scaleY);
				}
			}
		}

		public void checkCollisions() {
			for (int i = 0; i < groupA.size(); i++) {
				Entity a = groupA.get(i);
				Collision ac = cm.getSafe(a);
				if (ac == null) continue;

				for (int j = 0; j < groupB.size(); j++) {
					Entity b = groupB.get(j);
					Collision bc = cm.getSafe(b);
					if (bc == null) continue;

					if (ac.overlaps(bc)) handler.handleCollision(a, b);
				}
			}
		}
	}

	private interface CollisionHandler {
		void handleCollision(Entity a, Entity b);
	}

	@Mapper
	ComponentMapper<Collision> cm;
	@Mapper
	ComponentMapper<Spatial> sm;
	@Mapper
	ComponentMapper<Player> pm;
	@Mapper
	ComponentMapper<Reward> rm;
	@Mapper
	ComponentMapper<ActionComponent> am;

	private Array<CollisionGroup> groups;

	public CollisionSystem() {
	}

	@Override
	protected void initialize() {
		groups = new Array<CollisionGroup>();

		groups.add(new CollisionGroup(Group.PLAYER, Group.ENEMY, new CollisionHandler() {
			@Override
			public void handleCollision(Entity a, Entity b) {
				LawyerRace game = CoreRegistry.get(LawyerRace.class);
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
				world.getManager(GroupManager.class).remove(b, Group.DOLLAR);
				b.removeComponent(r).changedInWorld();

				SequenceAction seq = Actions.sequence();
				seq.addAction(Actions.scaleTo(1.2f, 1.2f, 0.1f)).addAction(Actions.scaleTo(0, 0, 0.1f)).addAction(Actions.remove());
				am.get(b).actions.add(seq);
			}
		}));
	}

	@Override
	protected void processSystem() {
		for (CollisionGroup group : groups) {
			group.updateCollisionBoundaries();
			group.checkCollisions();
		}
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

}
