package org.sms.main.services;

import org.sms.main.dto.StudentDTO;
import org.sms.main.entity.Address;
import org.sms.main.entity.Courses;
import org.sms.main.entity.Student;

public interface StudentService {
	
	StudentDTO updateStudent(String stdCode, Student student);
	
	Courses searchCourse(String courseName) throws Exception;
	
	String removeCourse(String stdCode, String courseName) throws Exception;
	
	String updateAddressByType(String stdCode, String typeOfAddress, Address address) throws Exception;
}
