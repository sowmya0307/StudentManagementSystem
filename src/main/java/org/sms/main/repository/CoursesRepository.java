package org.sms.main.repository;

import java.util.Optional;

import org.sms.main.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long>{

	Optional<Courses> findByCourseName(String courseName);

}
