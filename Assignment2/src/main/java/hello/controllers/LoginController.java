package hello.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.exceptions.LoginException;
import hello.service.interfaces.IUserService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired 
	private IUserService userService;
	
	@Autowired 
	private ModelMapper mapper;

	@RequestMapping(method = POST)
	public ResponseEntity<?> logIn(@RequestParam String email, @RequestParam String password){
		try {
			if(userService.login(email,password))
				return ResponseEntity.status(HttpStatus.OK).build();
		} catch (LoginException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect email/password");
	}
	
}
