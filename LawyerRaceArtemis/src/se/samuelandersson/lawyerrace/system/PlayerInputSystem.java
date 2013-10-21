package se.samuelandersson.lawyerrace.system;

import se.samuelandersson.lawyerrace.component.Movement;
import se.samuelandersson.lawyerrace.component.Player;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

public class PlayerInputSystem extends EntityProcessingSystem implements InputProcessor {

	@Mapper
	ComponentMapper<Movement> tm;

	private Movement movement;

	public PlayerInputSystem() {
		super(Aspect.getAspectForAll(Player.class, Movement.class));
	}

	@Override
	protected void process(Entity e) {
		if (movement == null) movement = tm.get(e);
	}

	@Override
	public boolean keyDown(int keycode) {
		if (movement == null) return false;
		if (keycode == Keys.LEFT) {
			movement.directionX = -1;
			movement.directionY = 0;
			return true;
		}
		if (keycode == Keys.RIGHT) {
			movement.directionX = 1;
			movement.directionY = 0;
			return true;
		}
		if (keycode == Keys.UP) {
			movement.directionX = 0;
			movement.directionY = -1;
			return true;
		}
		if (keycode == Keys.DOWN) {
			movement.directionX = 0;
			movement.directionY = 1;
			return true;
		}
		if (keycode == Keys.SPACE) {
			movement.moving = !movement.moving;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

}
