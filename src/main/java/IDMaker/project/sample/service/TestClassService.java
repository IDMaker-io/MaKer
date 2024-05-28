package IDMaker.project.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import IDMaker.project.sample.entity.TestClass;
import IDMaker.project.sample.repository.TestClassRepository;

@Service
public class TestClassService {

	@Autowired
	private TestClassRepository testClassRepository;

	public TestClass save() {
		TestClass testClass = new TestClass();
		return testClassRepository.save(testClass);
	}
}
