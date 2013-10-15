package se.samuelandersson.lawyerrace.component;

import se.samuelandersson.lawyerrace.Assets;

import com.artemis.Component;

public class TextureRegion extends Component {

	public com.badlogic.gdx.graphics.g2d.TextureRegion region;
	
	public TextureRegion(String name) {
		region = Assets.getAtlas().findRegion(name);
	}
}
