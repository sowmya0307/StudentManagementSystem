package org.sms.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {
	
	@Autowired
	private JwtServiceImpl jwtServiceImpl;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{
		JwtResponse jwtToken = jwtServiceImpl.createJwtToken(jwtRequest);
		return new ResponseEntity<JwtResponse>(jwtToken, HttpStatus.OK);
	}
}
