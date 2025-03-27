package org.sms.main.dto;

import java.util.List;

import org.sms.main.entity.Address;
import org.sms.main.entity.Courses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

	private String studentName;
	private String dob;
	private String gender;
	private String studentCode;
	private String fatherName;
	private String motherName;
	private String email;
	private long phno;
	
	private List<Address> addresses;
	private List<Courses> courses;
}
