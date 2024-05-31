package IDMaker.project;

import jakarta.persistence.PrePersist;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;

/**
 * A listener that checks for fields annotated with {@link IDMaker} and generates an ID for them if they are null.
 * @author yongjunhong
 * @since 2024. 5. 31.
 */
@Component
public class IDMakerEntityListener {

	/**
	 * Method that gets called before an entity gets persisted.
	 * This method checks all fields of the entity for the {@link IDMaker} annotation and generates an ID for them if they are null.
	 *
	 * @param entity the entity that is about to be persisted
	 * @throws IDMakerAccessException if there is an error accessing the fields of the entity
	 */
	@PrePersist
	@Transactional
	public void prePersist(Object entity) {
		Field[] fields = entity.getClass().getDeclaredFields();

		for (Field field : fields) {
			if (isIDMakerAnnotated(field)) {
				generateIdForField(entity, field);
			}
		}
	}

	/**
	 * Checks if a field is annotated with {@link IDMaker} and if it is of type String.
	 *
	 * @param field the field to check
	 * @return true if the field is annotated with {@link IDMaker} and is of type String, false otherwise
	 * @throws IllegalArgumentException if the field is annotated with {@link IDMaker} but is not of type String
	 */
	private boolean isIDMakerAnnotated(Field field) {
		if (!field.isAnnotationPresent(IDMaker.class)) {
			return false;
		}

		if (field.getType() != String.class) {
			throw new IllegalArgumentException("IDMaker annotation is only allowed on fields of type String");
		}

		return true;
	}

	/**
	 * Generates an ID for a field if it is null.
	 *
	 * @param entity the entity that contains the field
	 * @param field the field for which to generate an ID
	 * @throws IDMakerAccessException if there is an error accessing the field
	 */
	private void generateIdForField(Object entity, Field field) {
		field.setAccessible(true);

		try {
			if (field.get(entity) == null) {
				IDMaker idMaker = field.getAnnotation(IDMaker.class);
				String generatedId = IDMakerUtils.createTimestampedRandomID(idMaker.length(), idMaker.type());
				field.set(entity, generatedId);
			}
		} catch (IllegalAccessException e) {
			throw new IDMakerAccessException("Failed to access field with IDMaker annotation", e);
		}
	}

	/**
	 * A custom RuntimeException that is thrown when there is an error accessing a field.
	 */
	private static class IDMakerAccessException extends RuntimeException {
		IDMakerAccessException(String message, Throwable cause) {
			super(message, cause);
		}
	}
}