package se.samuelandersson.lawyerrace;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {

	public static final AssetManager manager = new AssetManager();

	public static void load() {
		manager.load("images/atlas/pages.atlas", TextureAtlas.class);
		manager.load("com/badlogic/gdx/utils/arial-15.fnt", BitmapFont.class);
		manager.finishLoading();
	}

	public static TextureAtlas getAtlas() {
		return manager.get("images/atlas/pages.atlas", TextureAtlas.class);
	}

	public static BitmapFont getFont() {
		return manager.get("com/badlogic/gdx/utils/arial-15.fnt", BitmapFont.class);
	}

}
