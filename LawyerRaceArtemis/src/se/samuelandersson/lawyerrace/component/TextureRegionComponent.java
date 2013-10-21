package se.samuelandersson.lawyerrace.component;

import se.samuelandersson.lawyerrace.Assets;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureRegionComponent extends Component {

	public TextureRegion region;

	public TextureRegionComponent(String name) {
		region = Assets.getRegion(name);
	}
}
