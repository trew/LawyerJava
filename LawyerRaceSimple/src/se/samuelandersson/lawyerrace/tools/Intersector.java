package se.samuelandersson.lawyerrace.tools;

import se.samuelandersson.lawyerrace.entity.Entity;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public final class Intersector {

	private static final Rectangle rectA = new Rectangle();
	private static final Rectangle rectB = new Rectangle();

	public static boolean intersects(Entity a, Entity b) {
		Polygon pa = getPolygon(a);
		Polygon pb = getPolygon(b);
		return com.badlogic.gdx.math.Intersector.overlapConvexPolygons(pa, pb);
		
	}

	private static Polygon getPolygon(Entity e) {
		Polygon p = e.getPolygon();
		if (p == null)
		   p = new Polygon(new float[] { e.getX(), e.getY(), e.getX() + e.getWidth(), e.getY(), e.getX() + e.getWidth(),
		         e.getY() + e.getWidth(), e.getX(), e.getY() + e.getHeight() });
		return p;
	}

	private Intersector() {
	}

}
