package IDMaker.fixture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import IDMaker.fixture.entity.TestClass;
import IDMaker.fixture.repository.TestClassRepository;
import IDMaker.passwordmaker.PasswordMakerUtil;

@Service
public class TestClassService {

	@Autowired
	private TestClassRepository testClassRepository;

	public TestClass testClasssave() {
		TestClass testClass = new TestClass();
		return testClassRepository.save(testClass);
	}

	public TestClass generateTestClass(String originId) {
		TestClass testClass = new TestClass();
		testClass.setRandomField(originId);
		return testClassRepository.save(testClass);
	}

	public String matchTestClass(String originId, String id) {
		TestClass testClass = testClassRepository.findById(id).orElse(null);

		return PasswordMakerUtil.matchPasswordMaker(originId, testClass.getRandomField()) ? "true" : "false";
	}
}
