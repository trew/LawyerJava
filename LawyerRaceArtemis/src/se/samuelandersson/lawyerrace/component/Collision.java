package se.samuelandersson.lawyerrace.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public class Collision extends Component {
	
	public Polygon polygon;
	
	public Collision(Vector2[] points) {
		float[] vertices = new float[points.length * 2];
		int i = 0;
		for (Vector2 point : points) {
			vertices[i++] = point.x;
			vertices[i++] = point.y;
		}
		polygon = new Polygon(vertices);
		polygon.setOrigin(polygon.getBoundingRectangle().width / 2, polygon.getBoundingRectangle().height / 2);
	}
	
	public Collision(float[] vertices) {
		polygon = new Polygon(vertices);
		polygon.setOrigin(polygon.getBoundingRectangle().width / 2, polygon.getBoundingRectangle().height / 2);
	}
	
	public Collision(Polygon polygon) {
		this.polygon = polygon;
		this.polygon.setOrigin(polygon.getBoundingRectangle().width / 2, polygon.getBoundingRectangle().height / 2);
	}
	
	public boolean overlaps(Collision other) {
		return Intersector.overlapConvexPolygons(polygon, other.polygon);
	}
	
}
