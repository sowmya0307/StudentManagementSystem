package org.sms.main.security;

import java.util.Optional;

import org.sms.main.entity.Admin;
import org.sms.main.entity.Student;
import org.sms.main.entity.User;
import org.sms.main.repository.AdminRepository;
import org.sms.main.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements UserDetailsService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	@Lazy
	private AuthenticationManager authenticationManager;

	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		String username = jwtRequest.getUsername();
		String password = jwtRequest.getPassword();

		loadUserByUsername(username);
		authenticate(username, password);
		User user = loadUser(username);
		String token = jwtUtil.generateToken(username);
		return new JwtResponse(token,user);
	}
	
	public User loadUser(String username) {
		Optional<Admin> byUsername = adminRepository.findByUsername(username);
		if(byUsername.isPresent())return byUsername.get();
		
		Optional<Student> byStudentCode = studentRepository.findByStudentCode(username);
		if(byStudentCode.isPresent()) return byStudentCode.get();
		
		Optional<Student> byDob = studentRepository.findByDob(username);
		if(byDob.isPresent()) return byDob.get();
		
		throw new UsernameNotFoundException("User Not found..!!!");
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Admin admin = adminRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found..!!!"));
		if(admin!=null)return admin;
		
		Student student = studentRepository.findByStudentCode(username).orElseThrow(()-> new UsernameNotFoundException("Student not Found..!!!"));
		if(student!=null)return student;
		
		Student student2 = studentRepository.findByDob(username).orElseThrow(()-> new UsernameNotFoundException("Studnet Not found..!!"));
		if(student2!=null)return student2;
		
		throw new UsernameNotFoundException("User Not found..!!!");
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
