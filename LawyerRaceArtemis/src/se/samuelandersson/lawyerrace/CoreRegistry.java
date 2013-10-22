package se.samuelandersson.lawyerrace;

import java.util.HashMap;

public final class CoreRegistry {

	private static HashMap<Class<? extends Object>, Object> instances = new HashMap<Class<? extends Object>, Object>();

	public static void put(Object object) {
		instances.put(object.getClass(), object);
	}

	public static <T> T get(Class<? extends T> clazz) {
		return clazz.cast(instances.get(clazz));
	}

}
