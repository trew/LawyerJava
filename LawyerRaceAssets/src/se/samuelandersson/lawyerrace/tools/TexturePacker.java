package se.samuelandersson.lawyerrace.tools;

import java.io.File;

import javax.swing.JOptionPane;

import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

/**
 * Packs single images into image atlases.
 */
public class TexturePacker extends TexturePacker2 {
	public TexturePacker(File rootDir, Settings settings) {
		super(rootDir, settings);
	}

	private static final String TEXTURE_INPUT_DIR = "images";
	private static final String TEXTURE_OUTPUT_DIR = "assets/images/atlas";
	private static final String PACK_FILE = "pages"; // .json

	private static final String SKIN_INPUT_DIR = "skin";
	private static final String SKIN_OUTPUT_DIR = "assets/skin";
	private static final String SKIN_PACK_FILE = "uiskin"; // .json

	public static void main(String[] args) {
		// dialog to prevent accidental running of this
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog(null, "ABORT?", "Warning", dialogButton);
		if (dialogResult == JOptionPane.NO_OPTION) {
			try {
				TexturePacker2.process(TEXTURE_INPUT_DIR, TEXTURE_OUTPUT_DIR, PACK_FILE);
				TexturePacker2.process(SKIN_INPUT_DIR, SKIN_OUTPUT_DIR, SKIN_PACK_FILE);
			} catch (RuntimeException e) {
				e.printStackTrace(System.err);
			}
			System.out.println("Finished!");
		}
	}
}
