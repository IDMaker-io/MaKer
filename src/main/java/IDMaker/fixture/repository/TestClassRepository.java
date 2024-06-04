package IDMaker.fixture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import IDMaker.fixture.entity.TestClass;

public interface TestClassRepository extends JpaRepository<TestClass, String> {
}
