package IDMaker.idmaker;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import IDMaker.idmaker.fixture.entity.TestClass;
import IDMaker.idmaker.fixture.service.TestClassService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IDMakerTest {

	@Autowired
	private TestClassService testClassService;

	@Test
	void testGenerateIDs() {
		TestClass testObject = testClassService.testClasssave();
		assertTrue(StringUtils.isAlphanumeric(testObject.getIdField()));
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
			() -> assertTrue(StringUtils.isAlphanumeric(id)),
			() -> assertEquals(randomLength + 17, id.length()),
			() -> assertTrue(StringUtils.isNumeric(date)),
			() -> assertEquals(17, date.length()),
			() -> assertTrue(StringUtils.isAlpha(randomString)),
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
}
