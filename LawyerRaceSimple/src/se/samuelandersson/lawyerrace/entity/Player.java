package se.samuelandersson.lawyerrace.entity;

import com.badlogic.gdx.math.Polygon;

public class Player extends MovingEntity {

	private float speed;
	private int score;
	private boolean alive;
	private boolean moving;

	public Player() {
		super("entities/player");
		speed = 200; // pixels per second
		alive = true;
		moving = true;
		polygon = new Polygon(new float[] { getX(), getY(), getX() + getWidth(), getY() + getHeight() / 2, getX(),
		      getY() + getHeight() });
	}

	@Override
	public void act(float delta) {
		if (!alive) return;
		if (moving) {
			if (direction == RIGHT)
				setX(getX() + speed * delta);
			else if (direction == UP)
				setY(getY() - speed * delta);
			else if (direction == LEFT)
				setX(getX() - speed * delta);
			else if (direction == DOWN) setY(getY() + speed * delta);
		}

		super.act(delta);
	}

	public Player setMoving(boolean moving) {
		this.moving = moving;
		return this;
	}

	public boolean isMoving() {
		return moving;
	}
	
	public Player setDirection(int direction) {
		this.direction = direction;
		return this;
	}

	public Player setSpeed(float speed) {
		this.speed = speed;
		return this;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setDead(boolean dead) {
		alive = !dead;
	}

}
