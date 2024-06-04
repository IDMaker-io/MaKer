package IDMaker.passwordmaker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import IDMaker.fixture.entity.TestClass;
import IDMaker.fixture.service.TestClassService;

/**
 *
 * @author yongjunhong
 * @since 2024. 6. 4.
 */

@SpringBootTest
class PasswordMakerUtilTest {

	@Autowired
	private TestClassService testClassService;

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

		assertTrue(PasswordMakerUtil.matchPasswordMaker(originId, passwordEncoder.encode(originId)));
	}

}