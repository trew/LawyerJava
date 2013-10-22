package se.samuelandersson.lawyerrace.system;

import se.samuelandersson.lawyerrace.Assets;
import se.samuelandersson.lawyerrace.CoreRegistry;
import se.samuelandersson.lawyerrace.component.PlayerComponent;
import se.samuelandersson.lawyerrace.utils.GdxUtils;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UIRenderSystem extends EntityProcessingSystem {

	@Mapper
	ComponentMapper<PlayerComponent> pm;

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private BitmapFont font;

	public UIRenderSystem() {
		super(Aspect.getAspectForAll(PlayerComponent.class));
		batch = CoreRegistry.get(SpriteBatch.class);
		font = Assets.getFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
	}

	@Override
	protected void begin() {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
	}

	@Override
	protected void end() {
		batch.end();
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

	@Override
	protected void process(Entity e) {
		PlayerComponent p = pm.get(e);
		GdxUtils.drawCenteredAt(batch, font, Integer.toString(p.score), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 10);
	}

}
