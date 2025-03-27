package org.sms.main.controller;

import java.util.List;

import org.sms.main.entity.Admin;
import org.sms.main.entity.Courses;
import org.sms.main.entity.Student;
import org.sms.main.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostConstruct
	public void addRoles() {
		adminService.addRoles();
	}
	
	@PostMapping("/addAdmin")
	public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin){
		Admin admin2 = adminService.addAdmin(admin);
		return new ResponseEntity<Admin>(admin2, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('Admin')")
	@PostMapping("/addStudent")
	public ResponseEntity<Student> addStudent(@RequestBody Student student){
		Student student2 = adminService.addStudent(student);
		return new ResponseEntity<Student>(student2, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('Admin')")
	@PostMapping("/addCourses")
	public ResponseEntity<Courses> addCourses(@RequestBody Courses courses){
		Courses courses2 = adminService.addCourses(courses);
		return new ResponseEntity<Courses>(courses2, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('Admin')")
	@PostMapping("/assignCourses")
	public ResponseEntity<Student> assignCourses(@RequestParam String studentCode,@RequestParam String courseName) throws Exception{
		Student assignCourses = adminService.assignCourses(studentCode, courseName);
		return new ResponseEntity<Student>(assignCourses, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/getStudentsByName")
	public ResponseEntity<List<Student> > getStudentsByName(@RequestParam String name){
		List<Student> studentsByName = adminService.getStudentsByName(name);
		return new ResponseEntity<List<Student>>(studentsByName, HttpStatus.OK);
	}
}
