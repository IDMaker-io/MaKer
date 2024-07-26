package com.maker.fixture.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.maker.fixture.entity.TestClass;
import com.maker.fixture.service.TestClassService;
import com.maker.idmaker.GenerationType;
import com.maker.idmaker.IDMakerUtils;
import com.maker.passwordmaker.PasswordEncoderFactories;
import com.maker.passwordmaker.PasswordMakerUtil;

@SpringBootTest
class TestClassServiceTest {

	@Autowired
	private TestClassService testClassService;

	@Test
	void testGenerateIDs() {
		TestClass testObject = testClassService.testClasssave();
		Assertions.assertTrue(StringUtils.isAlphanumeric(testObject.getIdField()));
		assertEquals(27, testObject.getIdField().length());
	}

	@Test
	void testGenerateKoreanID() {
		String id = IDMakerUtils.createTimestampedRandomID(10, GenerationType.KO);
		String randomPart = id.substring(17);
		assertTrue(randomPart.matches("[가-힣]+"));
	}

	@Test
	void testGenerateEnglishID() {
		String id = IDMakerUtils.createTimestampedRandomID(10, GenerationType.EN);
		String randomPart = id.substring(17);
		assertTrue(randomPart.matches("[a-zA-Z]+"));
	}

	@Test
	void testGenerateNumberID() {
		String id = IDMakerUtils.createTimestampedRandomID(10, GenerationType.NUMBER);
		String randomPart = id.substring(17);
		assertTrue(randomPart.matches("[0-9]+"));
	}

	@Test
	void testGenerateMixedID() {
		String id = IDMakerUtils.createTimestampedRandomID(10, GenerationType.MIX);
		String randomPart = id.substring(17);
		assertTrue(randomPart.matches("[가-힣a-zA-Z0-9]+"));
	}

	@RepeatedTest(30)
	void testIDConsistsOfADateAndString() {
		Random random = new Random();
		int randomLength = random.nextInt(20) + 1;

		String id = IDMakerUtils.createTimestampedRandomID(randomLength, GenerationType.EN);
		String date = id.substring(0, 17);
		String randomString = id.substring(17);

		assertAll(
			() -> assertNotNull(id),
			() -> Assertions.assertTrue(StringUtils.isAlphanumeric(id)),
			() -> assertEquals(randomLength + 17, id.length()),
			() -> Assertions.assertTrue(StringUtils.isNumeric(date)),
			() -> assertEquals(17, date.length()),
			() -> Assertions.assertTrue(StringUtils.isAlpha(randomString)),
			() -> assertEquals(randomLength, randomString.length())
		);
	}

	@Test
	void testGenerateIDsInAscendingOrder() {
		List<TestClass> testObjects = IntStream.range(0, 1000)
			.mapToObj(i -> {
				try {
					TestClass testObject = testClassService.testClasssave();
					Thread.sleep(10);
					return testObject;
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			})
			.toList();

		List<String> ids = testObjects.stream()
			.map(TestClass::getIdField)
			.toList();

		List<String> sortedIds = new ArrayList<>(ids);
		sortedIds.sort(String::compareTo);

		assertEquals(ids, sortedIds);
	}

	@Test
	void testNoDuplicateIDs() {
		Set<String> ids = new HashSet<>();
		for (int i = 0; i < 1_000_000; i++) {
			String id = IDMakerUtils.createTimestampedRandomID(10, GenerationType.EN);
			ids.add(id);
		}
		assertEquals(1_000_000, ids.size());
	}

	@Test
	void testGenerateTestClass() {
		String originId = "testOriginId";
		TestClass testClass = testClassService.generateTestClass(originId);

		assertNotEquals(originId, testClass.getRandomField());
		assertNotNull(testClass.getRandomField());
	}

	@Test
	void testMatchTestClass() {
		String originId = "testOriginId";
		TestClass testClass = testClassService.generateTestClass(originId);
		String id = testClass.getIdField();

		String result = testClassService.matchTestClass(originId, id);

		assertEquals("true", result, "The result should be true when the originId matches the randomField");
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"bcrypt",
		"pbkdf2@SpringSecurity_v5_8",
		"scrypt@SpringSecurity_v5_8",
		"argon2@SpringSecurity_v5_8"
	})
	void testMatchTestClassWithDifferentEncodingIds(String encodingId) {
		String originId = "testOriginId";
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(encodingId);

		Assertions.assertTrue(PasswordMakerUtil.matchPasswordMaker(originId, passwordEncoder.encode(originId)));
	}
}
