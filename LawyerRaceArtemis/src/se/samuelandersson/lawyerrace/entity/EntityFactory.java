package se.samuelandersson.lawyerrace.entity;

import se.samuelandersson.lawyerrace.component.Movement;
import se.samuelandersson.lawyerrace.component.Player;
import se.samuelandersson.lawyerrace.component.Reward;
import se.samuelandersson.lawyerrace.component.Spatial;
import se.samuelandersson.lawyerrace.component.Target;
import se.samuelandersson.lawyerrace.component.TextureRegion;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public final class EntityFactory {

	public static Entity createPlayer(World world) {
		Entity e = world.createEntity();

		Spatial s = new Spatial();
		s.position(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2).size(30, 30);

		TextureRegion r = new TextureRegion("entities/player");

		Movement m = new Movement();
		m.velocity(200);

		Player p = new Player();

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

		Spatial s = new Spatial();
		s.position(0, 0).size(width, height);

		Movement m = new Movement();
		m.velocity(150);

		Target t = new Target();
		t.target = target;

		e.addComponent(s);
		e.addComponent(r);
		e.addComponent(m);
		e.addComponent(t);
		return e;
	}

	public static Entity createDollar(World world) {
		Entity e = world.createEntity();

		TextureRegion r = new TextureRegion("entities/dollar");
		float width = r.region.getRegionWidth();
		float height = r.region.getRegionHeight();

		Spatial s = new Spatial();
		float x = MathUtils.random(0, Gdx.graphics.getWidth() - width);
		float y = MathUtils.random(0, Gdx.graphics.getHeight() - height);
		s.position(x, y).size(width, height);

		Reward rw = new Reward();
		rw.points = 1;
		e.addComponent(rw);
		e.addComponent(r);
		e.addComponent(s);
		return e;
	}
}
