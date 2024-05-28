package IDMaker.project.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import IDMaker.project.sample.entity.TestClass;
import IDMaker.project.sample.service.TestClassService;

@RestController
public class TestClassController {
	@Autowired
	private TestClassService testClassService;

	@GetMapping("/test")
	public ResponseEntity<String> registTestClass() {
		TestClass testClass = testClassService.testClasssave();
		return ResponseEntity.ok(testClass.getIdField());
	}
}
