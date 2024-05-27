package IDMaker.project.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.RandomStringUtils;

import IDMaker.project.annotation.IDMaker;

/**
 * @author yongjunhong
 */
public class IDMakerUtil {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

	private IDMakerUtil() {}

	public static void generateIDs(Object object) throws IllegalAccessException {
		Class<?> clazz = object.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(IDMaker.class) && field.getType().equals(String.class)) {
				IDMaker idMaker = field.getAnnotation(IDMaker.class);
				field.setAccessible(true);
				field.set(object, generateID(adjustNumber(idMaker.length())));
			}
		}
	}

	private static int adjustNumber(int length) {
		return Math.min(length, 15);
	}

	public static String generateID(int length) {
		return LocalDateTime.now().format(formatter)
			+ RandomValueUtil.createRandomString(length);
	}
}
