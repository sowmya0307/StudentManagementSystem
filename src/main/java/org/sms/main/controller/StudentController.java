package org.sms.main.controller;

import org.sms.main.dto.StudentDTO;
import org.sms.main.entity.Address;
import org.sms.main.entity.Student;
import org.sms.main.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class StudentController {

	@Autowired
	private StudentService studentsServices;
	
	@PutMapping("/updateStudents/{studentCode}")
	public ResponseEntity<StudentDTO> updateStudent(@PathVariable String studentCode, @RequestBody Student students) throws Exception {
		//TODO: process PUT request
		StudentDTO students2 = studentsServices.updateStudent(studentCode, students);
		return new ResponseEntity<StudentDTO>(students2,HttpStatus.OK);
	}


	@DeleteMapping("/removeCourse")
	public ResponseEntity<String> removeCourse(@PathVariable String studentCode,@RequestParam  String courseName) throws Exception{
		
		String student = studentsServices.removeCourse(studentCode, courseName);
		return new ResponseEntity<String>(student,HttpStatus.OK);
	}
	
	@PutMapping("/updateAddressByType/{id}")
	public ResponseEntity<String> updateAddressByType(@PathVariable String studentCode, @RequestBody Address address) throws Exception {
		//TODO: process PUT request
		String addres = studentsServices.updateAddressByType(studentCode, address.getAddressType(), address);
		return new ResponseEntity<String>(addres,HttpStatus.OK);
	}
}
