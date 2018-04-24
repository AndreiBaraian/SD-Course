package hello.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.exceptions.LoginException;
import hello.service.interfaces.IUserService;

@RestController
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(method = POST)
	public ResponseEntity<String> register(@RequestParam String email, @RequestParam String password, @RequestParam String token) {
		try {
			userService.register(email, password, token);
		} catch (LoginException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
