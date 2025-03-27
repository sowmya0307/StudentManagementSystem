package org.sms.main.serviceImp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.sms.main.entity.Address;
import org.sms.main.entity.Admin;
import org.sms.main.entity.Courses;
import org.sms.main.entity.Role;
import org.sms.main.entity.Student;
import org.sms.main.repository.AddressRepository;
import org.sms.main.repository.AdminRepository;
import org.sms.main.repository.CoursesRepository;
import org.sms.main.repository.RoleRepository;
import org.sms.main.repository.StudentRepository;
import org.sms.main.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CoursesRepository coursesRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(12);
	
	private AtomicLong studentCodeCounter = new AtomicLong(1); //It should start from 1
	
	@Override
	public void addRoles() {
		// TODO Auto-generated method stub
		Role role=new Role();
		role.setRoleName("Admin");
		roleRepository.save(role);
		
		Role role2=new Role();
		role2.setRoleName("Student");
		roleRepository.save(role2);
	}

	@Override
	public Admin addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		Role role = roleRepository.findById("Admin").get();
		Set<Role> roles=new HashSet<Role>();
		roles.add(role);
		String encode = bCryptPasswordEncoder.encode(admin.getPassword());
		admin.setPassword(encode);
		admin.setRoles(roles);
		Admin save = adminRepository.save(admin);
		return save;
	}

	@Override
	public Student addStudent(Student student) {
		// TODO Auto-generated method stub
		Role role = roleRepository.findById("Student").get();
		Set<Role> roles=new HashSet<Role>();
		roles.add(role);
		student.setRoles(roles);
		student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
		
		String studentCode="STD-"+String.format("%04d", studentCodeCounter.getAndIncrement()); //STD-0001
		student.setStudentCode(studentCode);
		
		Student student2 = studentRepository.save(student);
		for (Address address : student.getAddresses()) {
			address.setStudent(student2);
			addressRepository.save(address);
		}
		return student2;
	}

	@Override
	public Courses addCourses(Courses courses) {
		// TODO Auto-generated method stub
		Courses courses2 = coursesRepository.save(courses);
		return courses2;
	}

	@Override
	public Student assignCourses(String stdId, String courseName) throws Exception {
		// TODO Auto-generated method stub
		Student student = studentRepository.findByStudentCode(stdId).orElseThrow(()-> new UsernameNotFoundException("Student not found..!!"));
		Courses courses = coursesRepository.findByCourseName(courseName).orElseThrow(()-> new Exception("Course Not found..!!"));
		student.getCourses().add(courses);
		Student student2 = studentRepository.save(student);
		return student2;
	}

	@Override
	public List<Student> getStudentsByName(String name) {
		// TODO Auto-generated method stub
		List<Student> byStudentName = studentRepository.findByStudentName(name);
		return byStudentName;
	}

	@Override
	public List<Student> getStudentsByCourse(String courseName) {
		// TODO Auto-generated method stub
		List<Student> studentsByCourseName = studentRepository.findStudentsByCourseName(courseName);
		return studentsByCourseName;
	}
	
}
