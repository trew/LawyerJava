package se.samuelandersson.lawyerrace;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

	public static final AssetManager manager = new AssetManager();
	private static final Map<String, TextureRegion> regions = new HashMap<String, TextureRegion>();

	public static void load() {
		manager.load("images/atlas/pages.atlas", TextureAtlas.class);
		manager.load("com/badlogic/gdx/utils/arial-15.fnt", BitmapFont.class);
		manager.finishLoading();
	}

	public static TextureRegion getRegion(String name) {
		TextureRegion region = regions.get(name);
		if (region == null) {
			region = getAtlas().findRegion(name);
			region.flip(false, true);
			regions.put(name, region);
		}
		return new TextureRegion(region);
	}

	public static TextureAtlas getAtlas() {
		return manager.get("images/atlas/pages.atlas", TextureAtlas.class);
	}

	public static BitmapFont getFont() {
		return manager.get("com/badlogic/gdx/utils/arial-15.fnt", BitmapFont.class);
	}

}
