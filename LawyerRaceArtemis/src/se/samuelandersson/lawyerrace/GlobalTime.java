package se.samuelandersson.lawyerrace;

public class GlobalTime {

	private static float delta;
	
	public static void setDelta(float delta) {
		GlobalTime.delta = delta;
	}
	
	public static float getDelta() {
		return delta;
	}
	
}
