package se.samuelandersson.lawyerrace.entity;

import se.samuelandersson.lawyerrace.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MovingEntity extends Actor {

	public static final int RIGHT = 0;
	public static final int RIGHT_DOWN = 1;
	public static final int UP = 2;
	public static final int LEFT_DOWN = 3;
	public static final int LEFT = 4;
	public static final int LEFT_UP = 5;
	public static final int DOWN = 6;
	public static final int RIGHT_UP = 7;

	/* d / sqrt( 2*( d*d ) ) */
	protected static final float DIAGONAL_MULTIPLIER = 0.7071068f;

	private TextureRegion region;

	protected int vertical;
	protected int horizontal;
	protected int direction;
	protected float speed;
	
	public MovingEntity(TextureRegion region) {
		this.region = region;
		setBounds(0, 0, region.getRegionWidth(), region.getRegionHeight());
		direction = 0;
		speed = 150; // pixels per second
	}
	
	public MovingEntity(String name) {
		this(Assets.getAtlas().findRegion(name));
	}

	@Override
	public void act(float delta) {
		if (getX() < 0) setX(0);
		if (getY() < 0) setY(0);
		if (getX() + getWidth() > Gdx.graphics.getWidth()) setX(Gdx.graphics.getWidth() - getWidth());
		if (getY() + getHeight() > Gdx.graphics.getHeight()) setY(Gdx.graphics.getHeight() - getHeight());
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(region, getX(), getY(), getWidth() / 2, getHeight() / 2, getWidth(), getHeight(), 1, 1, 45 * direction);
	}

}

