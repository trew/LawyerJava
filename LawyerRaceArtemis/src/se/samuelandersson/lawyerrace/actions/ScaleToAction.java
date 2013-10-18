
package se.samuelandersson.lawyerrace.actions;

import com.artemis.ComponentType;
import com.badlogic.gdx.math.Interpolation;

import se.samuelandersson.lawyerrace.component.Spatial;

public class ScaleToAction extends BaseAction {

	float startX;
	float startY;
	float endX;
	float endY;

	float duration;
	float time;
	Interpolation interpolation;
	
	ComponentType spatialType;
	Spatial spatial;

	ScaleToAction (float scaleX, float scaleY, float duration, Interpolation interpolation) {
		spatialType = ComponentType.getTypeFor(Spatial.class);
		endX = scaleX;
		endY = scaleY;
		this.duration = duration;
		this.interpolation = interpolation;
	}

	private void setup(Spatial s) {
		startX = s.scaleX;
		startY = s.scaleY;
	}

	@Override
	public void update (float delta) {
		Spatial s = (Spatial)entity.getComponent(spatialType);
		if (s == null) return;
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
	}

}
