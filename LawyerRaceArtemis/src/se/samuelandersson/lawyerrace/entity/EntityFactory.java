package se.samuelandersson.lawyerrace.entity;

import se.samuelandersson.lawyerrace.component.ActionComponent;
import se.samuelandersson.lawyerrace.component.CollisionComponent;
import se.samuelandersson.lawyerrace.component.EnemyComponent;
import se.samuelandersson.lawyerrace.component.MovementComponent;
import se.samuelandersson.lawyerrace.component.PlayerComponent;
import se.samuelandersson.lawyerrace.component.RewardComponent;
import se.samuelandersson.lawyerrace.component.SpatialComponent;
import se.samuelandersson.lawyerrace.component.TextureRegionComponent;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;

public final class EntityFactory {

	public static Entity createPlayer(World world) {
		Entity e = world.createEntity();

		TextureRegionComponent r = new TextureRegionComponent("entities/player");
		float width = r.region.getRegionWidth();
		float height = r.region.getRegionHeight();

		world.getManager(GroupManager.class).add(e, Group.PLAYER);
		world.getManager(TagManager.class).register("PLAYER", e);

		Polygon pol = new Polygon(new float[] { 0, 0, width, height / 2, 0, height });
		e.addComponent(r);
		e.addComponent(new SpatialComponent(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, width, height));
		e.addComponent(new CollisionComponent(pol));
		e.addComponent(new MovementComponent(200));
		e.addComponent(new PlayerComponent());
		return e;
	}

	public static Entity createEnemy(World world, Entity target) {
		Entity e = world.createEntity();

		TextureRegionComponent r = new TextureRegionComponent("entities/enemy");
		float width = r.region.getRegionWidth();
		float height = r.region.getRegionHeight();

		world.getManager(GroupManager.class).add(e, Group.ENEMY);

		// a triangle polygon for collision
		Polygon pol = new Polygon(new float[] { 0, 0, width, height / 2, 0, height });
		
		e.addComponent(r);
		e.addComponent(new SpatialComponent(0, 0, width, height));
		e.addComponent(new CollisionComponent(pol));
		e.addComponent(new MovementComponent(150));
		e.addComponent(new EnemyComponent());
		return e;
	}

	public static Entity createDollar(World world) {
		Entity e = world.createEntity();

		TextureRegionComponent r = new TextureRegionComponent("entities/dollar");

		float width = r.region.getRegionWidth();
		float height = r.region.getRegionHeight();
		float x = MathUtils.random(0, Gdx.graphics.getWidth() - width);
		float y = MathUtils.random(0, Gdx.graphics.getHeight() - height);

		world.getManager(GroupManager.class).add(e, Group.DOLLAR);

		e.addComponent(r);
		e.addComponent(new SpatialComponent(x, y, width, height));
		e.addComponent(new CollisionComponent(0, 0, width, height));
		e.addComponent(new ActionComponent());
		e.addComponent(new RewardComponent(1));
		return e;
	}
}
