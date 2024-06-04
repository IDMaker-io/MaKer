package IDMaker.fixture.entity;

import IDMaker.idmaker.GenerationType;
import IDMaker.idmaker.IDMaker;
import IDMaker.idmaker.IDMakerEntityListener;
import IDMaker.passwordmaker.EncodingId;
import IDMaker.passwordmaker.PasswordMaker;
import IDMaker.passwordmaker.PasswordMakerListener;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;

@Entity
@EntityListeners({IDMakerEntityListener.class, PasswordMakerListener.class})
public class TestClass {

	@Id
	@IDMaker(length = 10, type = GenerationType.EN)
	private String id;

	@PasswordMaker(encodingType = EncodingId.SCRYPT)
	private String randomField;

	public String getIdField() {
		return id;
	}

	public String getRandomField() {
		return randomField;
	}

	public void setRandomField(String randomField) {
		this.randomField = randomField;
	}
}
