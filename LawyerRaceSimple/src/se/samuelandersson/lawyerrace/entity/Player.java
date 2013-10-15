package se.samuelandersson.lawyerrace.entity;

public class Player extends MovingEntity {

	private float speed;
	private int score;
	private boolean alive;

	public Player() {
		super("entities/player");
		speed = 200; // pixels per second
		alive = true;
	}

	@Override
	public void act(float delta) {
		if (!alive) return;
		if (direction == RIGHT)
			setX(getX() + speed * delta);
		else if (direction == UP)
			setY(getY() + speed * delta);
		else if (direction == LEFT)
			setX(getX() - speed * delta);
		else if (direction == DOWN) setY(getY() - speed * delta);

		super.act(delta);
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
