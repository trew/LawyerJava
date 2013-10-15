package se.samuelandersson.lawyerrace.component;

import com.artemis.Component;

public class Movement extends Component {

	public float velocityX;
	public float velocityY;
	public int directionX;
	public int directionY;
	
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
	
	public int getDirectionMultiplier() {
		if (directionX == 1 && directionY == 0) return 0;
		if (directionX == 1 && directionY == 1) return 1;
		if (directionX == 0 && directionY == 1) return 2;
		if (directionX == -1 && directionY == 1) return 3;
		if (directionX == -1 && directionY == 0) return 4;
		if (directionX == -1 && directionY == -1) return 5;
		if (directionX == 0 && directionY == -1) return 6;
		if (directionX == 1 && directionY == -1) return 7;
		return 0;
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
