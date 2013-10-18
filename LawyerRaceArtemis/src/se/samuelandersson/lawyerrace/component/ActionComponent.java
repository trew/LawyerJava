package se.samuelandersson.lawyerrace.component;

import se.samuelandersson.lawyerrace.actions.BaseAction;

import com.artemis.Component;
import com.badlogic.gdx.utils.Array;

public class ActionComponent extends Component {
	public Array<BaseAction> actions = new Array<BaseAction>();
}
