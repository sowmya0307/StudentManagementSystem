package org.sms.main.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends User{
	
	private String studentName;
	private String dob;
	private String gender;
	private String studentCode;
	private String fatherName;
	private String motherName;
	private String email;
	private long phno;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "student")
	private List<Address> addresses;

	@ManyToMany
	@JoinTable(name = "Student_Courses", joinColumns = { @JoinColumn(name = "Student_Code") }, inverseJoinColumns = {
			@JoinColumn(name="Course_Name") })
	private List<Courses> courses;

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.studentCode;
	}
}
