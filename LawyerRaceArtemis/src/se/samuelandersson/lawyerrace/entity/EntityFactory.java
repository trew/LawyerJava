package se.samuelandersson.lawyerrace.entity;

import se.samuelandersson.lawyerrace.component.ActionComponent;
import se.samuelandersson.lawyerrace.component.Enemy;
import se.samuelandersson.lawyerrace.component.Movement;
import se.samuelandersson.lawyerrace.component.Player;
import se.samuelandersson.lawyerrace.component.Reward;
import se.samuelandersson.lawyerrace.component.Spatial;
import se.samuelandersson.lawyerrace.component.TextureRegion;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public final class EntityFactory {

	private static GroupManager getGroupManager(World world) {
		return world.getManager(GroupManager.class);
	}
	
	public static Entity createPlayer(World world) {
		Entity e = world.createEntity();

		TextureRegion r = new TextureRegion("entities/player");
		float width = r.region.getRegionWidth();
		float height = r.region.getRegionHeight();
		Spatial s = new Spatial(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, width, height);
		Movement m = new Movement(200);
		Player p = new Player();
		
		getGroupManager(world).add(e, Group.PLAYER);
		world.getManager(TagManager.class).register("PLAYER", e);

		e.addComponent(p);
		e.addComponent(s);
		e.addComponent(r);
		e.addComponent(m);
		return e;
	}

	public static Entity createEnemy(World world, Entity target) {
		Entity e = world.createEntity();

		TextureRegion r = new TextureRegion("entities/enemy");
		float width = r.region.getRegionWidth();
		float height = r.region.getRegionHeight();
		Spatial s = new Spatial(0, 0, width, height);
		Movement m = new Movement(150);

		getGroupManager(world).add(e, Group.ENEMY);

		e.addComponent(s);
		e.addComponent(r);
		e.addComponent(m);
		e.addComponent(new Enemy());
		return e;
	}

	public static Entity createDollar(World world) {
		Entity e = world.createEntity();

		TextureRegion r = new TextureRegion("entities/dollar");

		float width = r.region.getRegionWidth();
		float height = r.region.getRegionHeight();
		float x = MathUtils.random(0, Gdx.graphics.getWidth() - width);
		float y = MathUtils.random(0, Gdx.graphics.getHeight() - height);
		Spatial s = new Spatial(x, y, width, height);
		Reward rw = new Reward(1);
		
		getGroupManager(world).add(e, Group.DOLLAR);
		
		e.addComponent(new ActionComponent());
		e.addComponent(rw);
		e.addComponent(r);
		e.addComponent(s);
		return e;
	}
}
