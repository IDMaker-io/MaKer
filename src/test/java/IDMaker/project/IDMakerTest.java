package IDMaker.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import IDMaker.project.annotation.IDMaker;
import IDMaker.project.util.IDMakerUtil;

import org.apache.commons.lang3.StringUtils;

class IDMakerTest {

	private TestClass testObject;

	@BeforeEach
	void setUp() {
		testObject = new TestClass();
	}

	@Test
	void testGenerateIDs() throws IllegalAccessException {
		IDMakerUtil.generateIDs(testObject);
		assertNotNull(testObject.idField);
		assertTrue(StringUtils.isAlphanumeric(testObject.idField));
		assertEquals(29, testObject.idField.length());
	}

	@Test
	void testGenerateID() {
		String id = IDMakerUtil.generateID(10);
		assertNotNull(id);
		assertTrue(StringUtils.isAlphanumeric(id));
		assertEquals(24, id.length());
	}

	@Test
	void testIDConsistsOfADateAndString() {
		String id = IDMakerUtil.generateID(10);
		String date = id.substring(0, 14);

		String randomString = id.substring(14);

		assertAll(
			() -> assertNotNull(id),
			() -> assertTrue(StringUtils.isAlphanumeric(id)),
			() -> assertEquals(24, id.length()),
			() -> assertTrue(StringUtils.isNumeric(date)),
			() -> assertEquals(14, date.length()),
			() -> assertTrue(StringUtils.isAlphanumeric(randomString)),
			() -> assertEquals(10, randomString.length())
		);
	}

	static class TestClass {
		@IDMaker(length = 15)
		private String idField;

		public String getIdField() {
			return idField;
		}
	}
}
