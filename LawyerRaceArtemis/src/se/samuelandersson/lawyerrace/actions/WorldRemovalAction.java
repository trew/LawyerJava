package se.samuelandersson.lawyerrace.actions;

public class WorldRemovalAction extends BaseAction {

	WorldRemovalAction () {
	}
	
	@Override
	public boolean update (float delta) {
		entity.deleteFromWorld();
		return true;
	}

}
