package se.samuelandersson.lawyerrace.entity;

import se.samuelandersson.lawyerrace.utils.GdxUtils;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Entity extends Actor {

	private static ShapeRenderer renderer = new ShapeRenderer();
	protected Polygon polygon;
	
	public Polygon getPolygon() {
		return polygon;
	}
	
	@Override
	public void act(float delta) {
	   if (polygon != null) {
			polygon.setPosition(getX(), getY());
			polygon.setRotation(getRotation());
			polygon.setScale(getScaleX(), getScaleY());
			polygon.setOrigin(getOriginX(), getOriginY());
	   }
	   super.act(delta);
	}
	
	public void drawDebug() {
		if (polygon == null) return;
		renderer.setProjectionMatrix(getStage().getCamera().combined);
		renderer.begin(ShapeType.Line);
		GdxUtils.drawPolygon(renderer, polygon);
		renderer.end();
	}
}
