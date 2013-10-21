package se.samuelandersson.lawyerrace.component;

import com.artemis.Component;

public class Movement extends Component {

	public float velocityX;
	public float velocityY;
	public int directionX;
	public int directionY;
	public boolean moving = true;
	
	public Movement() {
	}
	
	public Movement(float velocity) {
		velocity(velocity);
	}
	
	public Movement velocity(float velocity) {
		velocityX = velocity;
		velocityY = velocity;
		return this;
	}
	
	public Movement directionX(int directionX) {
		this.directionX = directionX;
		return this;
	}
	
	public Movement directionY(int directionY) {
		this.directionY = directionY;
		return this;
	}
	
	public Movement velocityX(float velocityX) {
		this.velocityX = velocityX;
		return this;
	}

	public Movement velocityY(float velocityY) {
		this.velocityY = velocityY;
		return this;
	}
}
