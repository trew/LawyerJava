package se.samuelandersson.lawyerrace.component;

import se.samuelandersson.lawyerrace.Assets;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComponent extends Component {

	public Sprite sprite;
	
	public SpriteComponent(String name) {
		sprite = Assets.getSprite(name);
	}
}
