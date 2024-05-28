package IDMaker.project.sample.entity;

import IDMaker.project.annotation.IDMaker;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TestClass {

	@Id
	@IDMaker(length = 10)
	private String id;

	@IDMaker
	private String randomField;

	public String getIdField() {
		return id;
	}

	public String getRandomField() {
		return randomField;
	}
}
