package org.sms.main.repository;

import java.util.List;
import java.util.Optional;

import org.sms.main.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	Optional<Student> findByStudentCode(String username);

	Optional<Student> findByDob(String username);

	List<Student> findByStudentName(String name);
	
	@Query("SELECT std FROM Student std JOIN std.courses c WHERE c.courseName = :courseName")
    List<Student> findStudentsByCourseName(@Param("courseName") String courseName);

}
