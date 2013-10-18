package se.samuelandersson.lawyerrace.actions;

import com.artemis.Entity;

public abstract class BaseAction {
	protected Entity entity;
	protected boolean completed = false;
	
	public abstract void update(float delta);
	
	public void setEntity(Entity e) {
		entity = e;
	}
	
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public boolean isCompleted() {
		return completed;
	}
}
