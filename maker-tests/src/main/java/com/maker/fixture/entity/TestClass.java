package com.maker.fixture.entity;


import com.maker.idmaker.GenerationType;
import com.maker.idmaker.IDMaker;
import com.maker.idmaker.IDMakerEntityListener;
import com.maker.passwordmaker.EncodingId;
import com.maker.passwordmaker.PasswordMaker;
import com.maker.passwordmaker.PasswordMakerListener;

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
