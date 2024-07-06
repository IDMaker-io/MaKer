package IDMaker.fixture.controller;

import java.io.IOException;
import java.time.DayOfWeek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import IDMaker.eventDateTimeMaker.EventDateTimeArbitrary;
import IDMaker.fixture.entity.TestClass;
import IDMaker.fixture.service.TestClassService;

@RestController
public class TestClassController {
	@Autowired
	private TestClassService testClassService;

	@GetMapping("/test")
	public ResponseEntity<String> registTestClass() {
		TestClass testClass = testClassService.testClasssave();
		return ResponseEntity.ok(testClass.getIdField());
	}

	@PostMapping("/test")
	public ResponseEntity<String> registTestClassPost(@RequestParam String origin) {
		TestClass testClass = testClassService.generateTestClass(origin);
		return ResponseEntity.ok(testClass.getIdField());
	}

	@PostMapping("/test/match")
	public ResponseEntity<String> matchTestClassPost(@RequestParam String id, @RequestParam String origin) {
		return ResponseEntity.ok(testClassService.matchTestClass(origin, id));
	}

	@GetMapping("/test/event")
	public ResponseEntity<String> registTestClassEvent() throws IOException {
		EventDateTimeArbitrary eventDateTimeArbitrary = EventDateTimeArbitrary.builder()
			.setYear(2024)
			.setMonth(7)
			.setDay(7)
			.build();
		return ResponseEntity.ok(eventDateTimeArbitrary.getEventDateTime().toPrettyString());
	}
}
