package com.maker.passwordmaker;


import static com.maker.passwordmaker.ExceptionCode.*;

import java.lang.reflect.Field;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.PrePersist;

/**
 * Listener class for password encoding.
 * This class listens for the PrePersist event and encodes any fields annotated with PasswordMaker.
 *
 * @author yongjunhong
 * @since 2024. 6. 3.
 */
@Component
public class PasswordMakerListener {

	/**
	 * Method that is called before an entity is persisted.
	 * This method encodes any fields in the entity that are annotated with PasswordMaker.
	 *
	 * @param entity the entity that is about to be persisted
	 */
	@PrePersist
	@Transactional
	public void prePersist(Object entity) {
		Field[] fields = entity.getClass().getDeclaredFields();

		for (Field field : fields) {
			if (isPasswordEncoderAnnotated(field)) {
				encodingField(entity, field);
			}
		}
	}

	/**
	 * Checks if a field is annotated with PasswordMaker and is of type String.
	 *
	 * @param field the field to check
	 * @return true if the field is annotated with PasswordMaker and is of type String, false otherwise
	 * @throws PasswordMakerInvalidArgumentsException if the field is annotated with PasswordMaker
	 * but is not of type String
	 */
	private boolean isPasswordEncoderAnnotated(Field field) {
		if (!field.isAnnotationPresent(PasswordMaker.class)) {
			return false;
		}

		if (field.getType() != String.class) {
			throw new PasswordMakerInvalidArgumentsException(PASSWORD_MAKER_ANNOTATION_ON_NON_STRING.getMessage());
		}

		return true;
	}

	/**
	 * Encodes a field in an entity.
	 * The field is encoded using the encoding type specified in the PasswordMaker annotation on the field.
	 *
	 * @param entity the entity that contains the field
	 * @param field the field to encode
	 * @throws PasswordMakerAccessException if there is an error accessing the field
	 */
	private void encodingField(Object entity, Field field) throws PasswordMakerInvalidArgumentsException {
		field.setAccessible(true);

		try {
			String originalPassword = (String)field.get(entity);
			if (originalPassword != null && !originalPassword.isEmpty()) {
				PasswordMaker passwordMaker = field.getAnnotation(PasswordMaker.class);
				String encodedPassword = PasswordMakerUtil.encodingPassword(originalPassword,
					passwordMaker.encodingType());
				field.set(entity, encodedPassword);
			}
		} catch (IllegalAccessException e) {
			throw new PasswordMakerAccessException(FAILED_TO_ACCESS_FIELD.getMessage());
		}
	}
}
