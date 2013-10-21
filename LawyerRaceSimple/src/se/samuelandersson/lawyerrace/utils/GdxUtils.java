package se.samuelandersson.lawyerrace.utils;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;

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

	public static void drawPolygon(ShapeRenderer renderer, Polygon polygon) {
		float[] vertices = polygon.getTransformedVertices();
		if (vertices.length < 6) return;
		for (int i = 2; i < vertices.length;) {
			renderer.line(vertices[i - 2], vertices[i - 1], vertices[i++], vertices[i++]);
		}
		renderer.line(vertices[0], vertices[1], vertices[vertices.length - 2], vertices[vertices.length - 1]);
	}
}
