package IDMaker.project.listener;

import IDMaker.project.annotation.IDMaker;
import IDMaker.project.util.IDMakerUtils;
import jakarta.persistence.PrePersist;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;

/**
 * Listener for entities that need to generate an ID before persisting.
 * This listener checks for fields annotated with {@link IDMaker} and generates an ID for them if they are null.
 */
@Component
public class IDMakerEntityListener {

	/**
	 * Method that gets called before an entity gets persisted.
	 * This method checks all fields of the entity for the {@link IDMaker} annotation and generates an ID for them if they are null.
	 *
	 * @param entity the entity that is about to be persisted
	 * @throws RuntimeException if there is an error accessing the fields of the entity
	 */
	@PrePersist
	@Transactional
	public void prePersist(Object entity) {
		for (Field field : entity.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(IDMaker.class)) {
				field.setAccessible(true);
				try {
					if (field.get(entity) == null) {
						IDMaker idMaker = field.getAnnotation(IDMaker.class);
						String generatedId = IDMakerUtils.createTimestampedRandomID(idMaker.length(), idMaker.type());
						field.set(entity, generatedId);
					}
				} catch (IllegalAccessException e) {
					throw new RuntimeException("IDMakerEntityListener error", e);
				}
			}
		}
	}
}