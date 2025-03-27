package org.sms.main.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin extends User{
	
	private String adminName;
	private String username;
	private long phno;
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}
	
}
