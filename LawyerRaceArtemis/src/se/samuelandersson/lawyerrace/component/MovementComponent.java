package se.samuelandersson.lawyerrace.component;

import com.artemis.Component;

public class MovementComponent extends Component {

	public float velocityX;
	public float velocityY;
	public int directionX;
	public int directionY;
	public boolean moving = true;

	public MovementComponent() {
	}

	public MovementComponent(float velocity) {
		velocity(velocity);
	}

	public MovementComponent velocity(float velocity) {
		velocityX = velocity;
		velocityY = velocity;
		return this;
	}

	public MovementComponent directionX(int directionX) {
		this.directionX = directionX;
		return this;
	}

	public MovementComponent directionY(int directionY) {
		this.directionY = directionY;
		return this;
	}

	public MovementComponent velocityX(float velocityX) {
		this.velocityX = velocityX;
		return this;
	}

	public MovementComponent velocityY(float velocityY) {
		this.velocityY = velocityY;
		return this;
	}
}
