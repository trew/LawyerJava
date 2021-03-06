package se.samuelandersson.lawyerrace.entity;

import se.samuelandersson.lawyerrace.Assets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Dollar extends Entity {

	private TextureRegion region;
	private boolean active;

	public Dollar() {
		region = Assets.getAtlas().findRegion("entities/dollar");
		if (!region.isFlipY()) region.flip(false, true);
		setBounds(0, 0, region.getRegionWidth(), region.getRegionHeight());
		setOrigin(getWidth() / 2, getHeight() / 2);
		active = true;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(region, getX(), getY(), getWidth() / 2, getHeight() / 2, getWidth(), getHeight(), getScaleX(), getScaleY(),
		      getRotation());
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isActive() {
		return active;
	}

}
