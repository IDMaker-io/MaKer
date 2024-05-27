package IDMaker.project.listener;

import IDMaker.project.annotation.IDMaker;
import IDMaker.project.util.IDMakerUtil;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;

import java.lang.reflect.Field;

public class IDMakerEntityListener implements PreInsertEventListener {

	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		Object entity = event.getEntity();
		Field[] fields = entity.getClass().getDeclaredFields();

		for (Field field : fields) {
			if (field.isAnnotationPresent(IDMaker.class)) {
				field.setAccessible(true);
				try {
					if (field.get(entity) == null) {
						int length = field.getAnnotation(IDMaker.class).length();
						String generatedId = IDMakerUtil.generateID(length);
						field.set(entity, generatedId);
					}
				} catch (IllegalAccessException e) {
					throw new RuntimeException("IDMakerEntityListener error", e);
				}
			}
		}

		return false;
	}
}