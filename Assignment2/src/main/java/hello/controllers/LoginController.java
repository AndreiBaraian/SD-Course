package hello.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import hello.exceptions.LoginException;
import hello.service.interfaces.IUserService;

@RestController
public class LoginController {
	
	@Autowired 
	private IUserService userService;
	
	@RequestMapping(value = "/login")
	public ModelAndView loginView() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

	@RequestMapping(method = POST)
	public ResponseEntity<?> logIn(@RequestParam String email, @RequestParam String password){
		try {
			if(userService.login(email,password) != null)
				return ResponseEntity.status(HttpStatus.OK).build();
		} catch (LoginException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect email/password");
	}
	
}
