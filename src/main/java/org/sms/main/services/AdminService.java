package org.sms.main.services;

import java.util.List;

import org.sms.main.entity.Admin;
import org.sms.main.entity.Courses;
import org.sms.main.entity.Student;

public interface AdminService {
	
	void addRoles();
	
	Admin addAdmin(Admin admin);
	
	Student addStudent(Student student);
	
	Courses addCourses(Courses courses);
	
	Student assignCourses(String stdId,String courseName) throws Exception;
	
	List<Student> getStudentsByName(String name);
	
	List<Student> getStudentsByCourse(String courseName);
}
