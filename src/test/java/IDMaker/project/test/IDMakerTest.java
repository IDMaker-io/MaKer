package IDMaker.project.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import IDMaker.project.sample.entity.TestClass;
import IDMaker.project.sample.service.TestClassService;
import IDMaker.project.util.IDMakerUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IDMakerTest {


	@Autowired
	private TestClassService testClassService;


	@Test
	void testGenerateIDs() {
		TestClass testObject = testClassService.save();
		assertTrue(StringUtils.isAlphanumeric(testObject.getIdField()));
		assertEquals(27, testObject.getIdField().length());
	}

	@RepeatedTest(30)
	void testIDConsistsOfADateAndString() {
		Random random = new Random();
		int randomLength = random.nextInt(20) + 1;

		String id = IDMakerUtils.createTimestampedRandomID(randomLength);
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
					TestClass testObject = testClassService.save();
					Thread.sleep(10);
					return testObject;
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			})
			.toList();

		List<String> ids = testObjects.stream()
			.map(TestClass::getIdField)
			.collect(Collectors.toList());

		List<String> sortedIds = new ArrayList<>(ids);
		sortedIds.sort(String::compareTo);

		assertEquals(ids, sortedIds);
	}
}
