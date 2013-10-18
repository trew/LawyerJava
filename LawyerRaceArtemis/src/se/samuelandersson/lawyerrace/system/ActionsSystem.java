package se.samuelandersson.lawyerrace.system;

import java.util.HashMap;
import java.util.Map;

import se.samuelandersson.lawyerrace.actions.BaseAction;
import se.samuelandersson.lawyerrace.component.ActionComponent;

import com.artemis.Aspect;
import com.artemis.ComponentType;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.utils.Array;

public class ActionsSystem extends EntityProcessingSystem {

	Map<Entity, Array<BaseAction>> actionsByEntity;
	ComponentType actionComponentType;
	
	public ActionsSystem () {
		super(Aspect.getAspectForAll(ActionComponent.class));
		actionsByEntity = new HashMap<Entity, Array<BaseAction>>();
	}
	
	@Override
	protected void initialize () {
		actionComponentType = ComponentType.getTypeFor(ActionComponent.class);
	}

	public void addAction(Entity e, BaseAction action) {
		action.setEntity(e);
		e.addComponent(new ActionComponent()).changedInWorld();
		Array<BaseAction> entityActions = actionsByEntity.get(e);
		if (entityActions == null) {
			entityActions = new Array<BaseAction>();
			actionsByEntity.put(e, entityActions);
		}
		entityActions.add(action);
	}
	
	@Override
	protected void process (Entity e) {
		float delta = world.getDelta();
		Array<BaseAction> entityActions = actionsByEntity.get(e);
		if (entityActions == null) return;

		for (int i = 0; i < entityActions.size; i++) {
			BaseAction action = entityActions.get(i);
			boolean completed = action.update(delta);
			if (completed) entityActions.removeIndex(i--);
		}
		if (entityActions.size == 0) {
			e.removeComponent(actionComponentType).changedInWorld();
		}
	}

}
 