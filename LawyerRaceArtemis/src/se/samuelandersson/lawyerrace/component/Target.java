package se.samuelandersson.lawyerrace.component;

import com.artemis.Component;
import com.artemis.Entity;

public class Target extends Component {
	public Entity target;

	public Target() {
	}

	public Target(Entity e) {
		target = e;
	}
}
