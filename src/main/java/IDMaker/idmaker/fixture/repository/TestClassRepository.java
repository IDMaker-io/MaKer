package IDMaker.idmaker.fixture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import IDMaker.idmaker.fixture.entity.TestClass;

public interface TestClassRepository extends JpaRepository<TestClass, String> {
}
