package IDMaker.project.fixture.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import IDMaker.project.fixture.entity.TestClass;
import IDMaker.project.fixture.repository.TestClassRepository;

@Service
public class TestClassService {

	@Autowired
	private TestClassRepository testClassRepository;

	public TestClass testClasssave() {
		TestClass testClass = new TestClass();
		return testClassRepository.save(testClass);
	}
}
