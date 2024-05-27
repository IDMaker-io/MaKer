package IDMaker.project.config;

import javax.persistence.EntityManagerFactory;

import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import IDMaker.project.listener.IDMakerEntityListener;
import jakarta.annotation.PostConstruct;

@Configuration
public class HibernateConfig {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@PostConstruct
	public void registerListeners() {
		SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
		EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
		registry.getEventListenerGroup(EventType.PRE_INSERT).appendListener(new IDMakerEntityListener());
	}
}