package se.samuelandersson.lawyerrace.component;

import com.artemis.Component;

public class Spatial extends Component {
	public float x, y;
	public float width, height;
	public float angle;
	public float scaleX = 1;
	public float scaleY = 1;
	
	public Spatial() {
	}

	public Spatial(float x, float y, float width, float height) {
		position(x, y).size(width, height);
	}

	public Spatial position(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public Spatial size(float width, float height) {
		this.width = width;
		this.height = height;
		return this;
	}

	public Spatial angle(float angle) {
		this.angle = angle;
		return this;
	}

	public Spatial scale(float scaleX, float scaleY) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		return this;
	}

	public boolean intersects(Spatial b) {
		if (x > b.x + b.width) return false;
		if (b.x > x + width) return false;
		if (y > b.y + b.height) return false;
		if (b.y > y + height) return false;
		return true;
	}
}
