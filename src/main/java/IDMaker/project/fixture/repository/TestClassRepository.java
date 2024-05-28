package IDMaker.project.fixture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import IDMaker.project.fixture.entity.TestClass;

public interface TestClassRepository extends JpaRepository<TestClass, String> {
}
