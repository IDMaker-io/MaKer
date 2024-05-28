package IDMaker.project.listener;

import IDMaker.project.annotation.IDMaker;
import IDMaker.project.util.IDMakerUtils;
import jakarta.persistence.PrePersist;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;

@Component
public class IDMakerEntityListener {

	@PrePersist
	@Transactional
	public void prePersist(Object entity) {
		for (Field field : entity.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(IDMaker.class)) {
				field.setAccessible(true);
				try {
					if (field.get(entity) == null) {
						IDMaker idMaker = field.getAnnotation(IDMaker.class);
						String generatedId = IDMakerUtils.createTimestampedRandomID(idMaker.length());
						field.set(entity, generatedId);
					}
				} catch (IllegalAccessException e) {
					throw new RuntimeException("IDMakerEntityListener error", e);
				}
			}
		}
	}
}
