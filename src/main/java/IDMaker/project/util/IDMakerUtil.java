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

	public static void generateIDs(Object object) throws IllegalAccessException,
		IntrospectionException, InvocationTargetException {
		Class<?> clazz = object.getClass();
		for (PropertyDescriptor pd : Introspector.getBeanInfo(clazz, Object.class).getPropertyDescriptors()) {
			if (pd.getReadMethod().isAnnotationPresent(IDMaker.class) && pd.getPropertyType().equals(String.class)) {
				IDMaker idMaker = pd.getReadMethod().getAnnotation(IDMaker.class);
				pd.getWriteMethod().invoke(object, generateID(adjustNumber(idMaker.length())));
			}
		}
	}

	private static int adjustNumber(int length) {
		return Math.min(length, 15);
	}

	private static String generateID(int length) {
		return LocalDateTime.now().format(formatter)
			+ RandomStringUtils.random(length);
	}
}
