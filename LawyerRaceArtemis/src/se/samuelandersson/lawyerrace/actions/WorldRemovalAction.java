package se.samuelandersson.lawyerrace.actions;

public class WorldRemovalAction extends BaseAction {

	WorldRemovalAction () {
	}
	
	@Override
	public void update (float delta) {
		entity.deleteFromWorld();
		setCompleted(true);
	}

}
