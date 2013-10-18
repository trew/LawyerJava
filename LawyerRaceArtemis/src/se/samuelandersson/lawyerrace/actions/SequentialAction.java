
package se.samuelandersson.lawyerrace.actions;

import com.artemis.Entity;
import com.badlogic.gdx.utils.Array;

public class SequentialAction extends BaseAction {

	Array<BaseAction> actions;

	SequentialAction() {
		actions = new Array<BaseAction>();
	}
	
	@Override
	public void setEntity (Entity e) {
		super.setEntity(e);
		for (BaseAction a : actions) {
			a.setEntity(e);
		}
	}
	
	public SequentialAction addAction(BaseAction a) {
		actions.add(a);
		return this;
	}

	@Override
	public void update (float delta) {
		BaseAction action = actions.first();
		if (action == null) {
			setCompleted(true);
			return;
		}
		action.update(delta);
		if (action.isCompleted()) {
			actions.removeValue(action, true);
		}
		setCompleted(actions.size == 0);
	}
}
