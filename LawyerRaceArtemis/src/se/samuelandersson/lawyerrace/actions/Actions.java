package se.samuelandersson.lawyerrace.actions;

import se.samuelandersson.lawyerrace.system.ActionsSystem;

import com.badlogic.gdx.math.Interpolation;

/**
 * Returns actions that are processed in the {@link ActionsSystem}.
 * 
 * @author Samuel Andersson
 */
public class Actions {

	private Actions() {
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

	public static SequenceAction sequence() {
		return new SequenceAction();
	}

	public static WorldRemovalAction remove() {
		return new WorldRemovalAction();
	}

}
