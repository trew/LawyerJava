package se.samuelandersson.lawyerrace.tools;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public final class Intersector {

	private static final Rectangle rectA = new Rectangle();
	private static final Rectangle rectB  = new Rectangle();
	
	public static boolean intersects(Actor a, Actor b) {
		rectA.set(a.getX(), a.getY(), a.getWidth(), a.getHeight());
		rectB.set(b.getX(), b.getY(), b.getWidth(), b.getHeight());
		return com.badlogic.gdx.math.Intersector.intersectRectangles(rectA, rectB);
	}
	
	private Intersector() {
	}

}
