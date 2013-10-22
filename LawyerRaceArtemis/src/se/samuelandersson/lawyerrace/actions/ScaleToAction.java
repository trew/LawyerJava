package se.samuelandersson.lawyerrace.actions;

import com.artemis.ComponentType;
import com.badlogic.gdx.math.Interpolation;

import se.samuelandersson.lawyerrace.component.SpatialComponent;

public class ScaleToAction extends BaseAction {

	protected float startX;
	protected float startY;
	protected float endX;
	protected float endY;

	protected float duration;
	protected float time;
	protected boolean completed = false;
	protected Interpolation interpolation;

	protected ComponentType spatialType;
	protected SpatialComponent spatial;

	ScaleToAction(float scaleX, float scaleY, float duration, Interpolation interpolation) {
		spatialType = ComponentType.getTypeFor(SpatialComponent.class);
		endX = scaleX;
		endY = scaleY;
		this.duration = duration;
		this.interpolation = interpolation;
	}

	private void setup(SpatialComponent s) {
		startX = s.scaleX;
		startY = s.scaleY;
	}

	@Override
	public boolean update(float delta) {
		SpatialComponent s = (SpatialComponent) entity.getComponent(spatialType);
		if (s == null) return true;
		if (time == 0) setup(s);

		time += delta;
		float percent;
		completed = time >= duration;
		if (completed) {
			percent = 1;
		} else {
			percent = time / duration;
			if (interpolation != null) percent = interpolation.apply(percent);
		}
		s.scaleX = startX + (endX - startX) * percent;
		s.scaleY = startY + (endY - startY) * percent;
		return completed;
	}

}
