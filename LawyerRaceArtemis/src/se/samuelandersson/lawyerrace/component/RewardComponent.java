package se.samuelandersson.lawyerrace.component;

import com.artemis.Component;

public class RewardComponent extends Component {
	public int points;
	public boolean active = true;

	public RewardComponent() {
	}

	public RewardComponent(int points) {
		this.points = points;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}
}
