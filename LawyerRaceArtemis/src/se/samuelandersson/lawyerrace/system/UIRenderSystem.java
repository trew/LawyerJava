package se.samuelandersson.lawyerrace.system;

import se.samuelandersson.lawyerrace.Assets;
import se.samuelandersson.lawyerrace.component.Player;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UIRenderSystem extends EntityProcessingSystem implements RenderSystem {

	@Mapper
	ComponentMapper<Player> pm;
	
	private SpriteBatch batch;
	private BitmapFont font;
	
	public UIRenderSystem() {
	   super(Aspect.getAspectForAll(Player.class));
	   batch = new SpriteBatch();
	   font = Assets.getFont();
   }
	
	@Override
   public void resize(int width, int height) {
   }

	@Override
	protected void begin() {
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
		Player p = pm.get(e);
		font.draw(batch, Integer.toString(p.score), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 10);
   }

}
