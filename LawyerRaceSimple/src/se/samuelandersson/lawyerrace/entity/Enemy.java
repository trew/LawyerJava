package se.samuelandersson.lawyerrace.entity;

import com.badlogic.gdx.math.Polygon;

public class Enemy extends MovingEntity {

	private Player target;

	public Enemy() {
		super("entities/enemy");
		speed = 150; // pixels per second
		polygon = new Polygon(new float[] { getX(), getY(), getX() + getWidth(), getY() + getHeight() / 2, getX(),
		      getY() + getHeight() });
	}

	@Override
	public void act(float delta) {
		if (target == null) return;
		if (target.isMoving()) {
			updateDirection();

			float targetX = target.getX();
			float targetY = target.getY();
			float deltaX = 0;
			float deltaY = 0;

			if (targetX < getX()) {
				deltaX = -speed * delta;
			} else if (targetX > getX()) {
				deltaX = speed * delta;
			}

			if (targetY < getY()) {
				deltaY = -speed * delta;
			} else if (targetY > getY()) {
				deltaY = speed * delta;
			}

			if (deltaX != 0 && deltaY != 0) {
				deltaX *= DIAGONAL_MULTIPLIER;
				deltaY *= DIAGONAL_MULTIPLIER;
			}

			if (getX() < targetX && getX() + deltaX > targetX) deltaX = targetX - getX();
			if (getY() < targetY && getY() + deltaY > targetY) deltaY = targetY - getY();
			setX(getX() + deltaX);
			setY(getY() + deltaY);
		}
		super.act(delta);
	}

	private void updateDirection() {
		if (target == null) return;
		float targetX = target.getX();
		float targetY = target.getY();

		horizontal = targetX < getX() ? -1 : targetX > getX() ? 1 : 0;
		vertical = targetY < getY() ? -1 : targetY > getY() ? 1 : 0;

		if (horizontal == 1 && vertical == 1) direction = RIGHT_DOWN;
		if (horizontal == 1 && vertical == 0) direction = RIGHT;
		if (horizontal == 1 && vertical == -1) direction = RIGHT_UP;
		if (horizontal == -1 && vertical == 1) direction = LEFT_DOWN;
		if (horizontal == -1 && vertical == 0) direction = LEFT;
		if (horizontal == -1 && vertical == -1) direction = LEFT_UP;
		if (horizontal == 0 && vertical == 1) direction = UP;
		if (horizontal == 0 && vertical == -1) direction = DOWN;
	}

	public Enemy setTarget(Player player) {
		target = player;
		return this;
	}

}
