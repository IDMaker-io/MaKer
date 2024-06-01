package IDMaker.idmaker.fixture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import IDMaker.idmaker.fixture.entity.TestClass;
import IDMaker.idmaker.fixture.repository.TestClassRepository;

@Service
public class TestClassService {

	@Autowired
	private TestClassRepository testClassRepository;

	public TestClass testClasssave() {
		TestClass testClass = new TestClass();
		return testClassRepository.save(testClass);
	}
}
