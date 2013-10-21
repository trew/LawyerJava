package se.samuelandersson.lawyerrace.utils;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public final class GdxUtils {
	private GdxUtils() {
	}
	
	public static void drawCenteredAt(SpriteBatch batch, TextureRegion region, float x, float y) {
		float halfRegWidth = region.getRegionWidth() / 2;
		float halfRegHeight = region.getRegionHeight() / 2;
		batch.draw(region, x - halfRegWidth, y - halfRegHeight);
	}
	
	public static void drawCenteredAt(SpriteBatch batch, BitmapFont font, CharSequence str, float x, float y) {
		TextBounds bounds = font.getBounds(str);
		font.draw(batch, str, x - bounds.width / 2, y - bounds.height / 2);
	}
}
