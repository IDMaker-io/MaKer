package IDMaker.project.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import IDMaker.project.sample.entity.TestClass;

public interface TestClassRepository extends JpaRepository<TestClass, String> {
}
