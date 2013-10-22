package se.samuelandersson.lawyerrace.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class CollisionComponent extends Component {

	public Polygon polygon;

	public CollisionComponent(Vector2[] points) {
		float[] vertices = new float[points.length * 2];
		int i = 0;
		for (Vector2 point : points) {
			vertices[i++] = point.x;
			vertices[i++] = point.y;
		}
		polygon = new Polygon(vertices);
		polygon.setOrigin(polygon.getBoundingRectangle().width / 2, polygon.getBoundingRectangle().height / 2);
	}

	public CollisionComponent(float[] vertices) {
		polygon = new Polygon(vertices);
		polygon.setOrigin(polygon.getBoundingRectangle().width / 2, polygon.getBoundingRectangle().height / 2);
	}

	public CollisionComponent(Polygon polygon) {
		this.polygon = polygon;
		this.polygon.setOrigin(polygon.getBoundingRectangle().width / 2, polygon.getBoundingRectangle().height / 2);
	}

	public CollisionComponent(Rectangle rectangle) {
		this(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}

	public CollisionComponent(float x, float y, float width, float height) {
		this(new float[] { x, y, x + width, y, x + width, y + height, x, y + height });
	}

	public boolean overlaps(CollisionComponent other) {
		return Intersector.overlapConvexPolygons(polygon, other.polygon);
	}

}
