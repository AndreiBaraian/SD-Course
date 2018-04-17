package hello.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.apimodels.StudentAPIModel;
import hello.service.bllmodel.StudentBModel;
import hello.service.interfaces.IStudentService;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private IStudentService studentService;
	
	@RequestMapping(method = POST)
	public ResponseEntity<String> addStudent(@RequestBody StudentAPIModel student) {
		if(studentService.addStudent(new ModelMapper().map(student,StudentBModel.class)) != null)
			return ResponseEntity.status(HttpStatus.CREATED).build();
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
