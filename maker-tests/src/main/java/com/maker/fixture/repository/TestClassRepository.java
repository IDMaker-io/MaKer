package com.maker.fixture.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.maker.fixture.entity.TestClass;

public interface TestClassRepository extends JpaRepository<TestClass, String> {
}
