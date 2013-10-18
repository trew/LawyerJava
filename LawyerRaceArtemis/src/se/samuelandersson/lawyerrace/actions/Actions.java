package se.samuelandersson.lawyerrace.actions;

import com.badlogic.gdx.math.Interpolation;

public class Actions {

	private Actions () {
	}
	
	public static ScaleToAction scaleTo(float x, float y) {
		return scaleTo(x, y, 0, null);
	}
	
	public static ScaleToAction scaleTo(float x, float y, float duration) {
		return scaleTo(x, y, duration, null);
	}
	
	public static ScaleToAction scaleTo(float x, float y, float duration, Interpolation interpolation) {
		return new ScaleToAction(x, y, duration, interpolation);
	}
	
	public static SequentialAction sequence() {
		return new SequentialAction();
	}
	
	public static WorldRemovalAction remove() {
		return new WorldRemovalAction();
	}

}
