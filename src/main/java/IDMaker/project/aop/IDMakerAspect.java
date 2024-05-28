package IDMaker.project.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import IDMaker.project.annotation.IDMaker;
import IDMaker.project.util.IDMakerUtils;

import java.lang.reflect.Field;

/**
 * Aspect for generating IDs before persisting entities.
 * The ID is generated for fields annotated with @IDMaker.
 * The ID consists of a timestamp and a random string.
 * The length of the random string is specified in the @IDMaker annotation.
 */
@Aspect
@Component
public class IDMakerAspect {

	/**
	 * Advice that generates an ID before persisting an entity.
	 * @param entity the entity to persist
	 * @throws IllegalAccessException if the ID field cannot be accessed
	 */
	@Before("execution(* org.springframework.data.jpa.repository.JpaRepository.save(..)) && args(entity)")
	public void beforePersist(Object entity) throws IllegalAccessException {
		generateId(entity);
	}

	private void generateId(Object entity) throws IllegalAccessException {
		for (Field field : entity.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(IDMaker.class)) {
				field.setAccessible(true);
				if (field.get(entity) == null) {
					IDMaker idMaker = field.getAnnotation(IDMaker.class);
					String generatedId = IDMakerUtils.createTimestampedRandomID(idMaker.length());
					field.set(entity, generatedId);
				}
			}
		}
	}
}