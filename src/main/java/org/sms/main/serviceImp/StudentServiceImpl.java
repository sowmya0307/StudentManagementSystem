package org.sms.main.serviceImp;

import org.modelmapper.ModelMapper;
import org.sms.main.dto.StudentDTO;
import org.sms.main.entity.Address;
import org.sms.main.entity.Courses;
import org.sms.main.entity.Student;
import org.sms.main.repository.AddressRepository;
import org.sms.main.repository.CoursesRepository;
import org.sms.main.repository.StudentRepository;
import org.sms.main.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CoursesRepository coursesRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public StudentDTO updateStudent(String stdCode, Student student) {
		// TODO Auto-generated method stub
		Student student2 = studentRepository.findByStudentCode(stdCode)
				.orElseThrow(() -> new UsernameNotFoundException("Student not found..!!"));
		student2.setDob(student.getDob());
		student2.setFatherName(student.getFatherName());
		student2.setMotherName(student.getMotherName());
		student2.setPhno(student.getPhno());
		student2.setEmail(student.getEmail());
		Student save = studentRepository.save(student2);
		
		ModelMapper modelMapper=new ModelMapper();
		StudentDTO studentDTO = modelMapper.map(save, StudentDTO.class);
		return studentDTO;
	}

	@Override
	public Courses searchCourse(String courseName) throws Exception {
		// TODO Auto-generated method stub
		Courses courses = coursesRepository.findByCourseName(courseName)
				.orElseThrow(() -> new Exception("Course not found..!!"));
		return courses;
	}

	@Override
	public String removeCourse(String stdCode, String courseName) throws Exception {
		// TODO Auto-generated method stub
		Student student2 = studentRepository.findByStudentCode(stdCode)
				.orElseThrow(() -> new UsernameNotFoundException("Student not found..!!"));
		Courses courses = coursesRepository.findByCourseName(courseName)
				.orElseThrow(() -> new Exception("Course not found..!!"));
		boolean remove = student2.getCourses().remove(courses);
		if (remove == true) {
			return "Course removed successfully..!!";
		} else {
			return "Course not removed..!!!";
		}
	}

	@Override
	public String updateAddressByType(String stdCode, String typeOfAddress, Address address) throws Exception {
		// TODO Auto-generated method stub
		Student student2 = studentRepository.findByStudentCode(stdCode)
				.orElseThrow(() -> new UsernameNotFoundException("Student not found..!!"));
		student2.getAddresses().stream().filter(addres -> addres.getAddressType().equals(typeOfAddress)).findFirst()
				.map(addres -> {
					addres.setAddressType(address.getAddressType());
					addres.setHouseNo(address.getHouseNo());
					addres.setStreetName(address.getStreetName());
					addres.setVlgName(address.getVlgName());
					addres.setMandal(address.getMandal());
					addres.setDistrict(address.getDistrict());
					addres.setState(address.getState());
					addres.setPincode(address.getPincode());
					addressRepository.save(addres);
					return true;
				}).orElseThrow(() -> new Exception("Address Type not found..!!!"));

		return "Address Updated successfully..!!!";
	}

}
