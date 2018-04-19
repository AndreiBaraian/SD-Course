package hello.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.apimodels.StudentAPIModel;
import hello.service.bllmodel.StudentBModel;
import hello.service.interfaces.IStudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private IStudentService studentService;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	@RequestMapping(method = POST)
	public ResponseEntity<HashMap<String,String>> addStudent(@RequestBody StudentAPIModel student) {
		HashMap<String,String> response = new HashMap<>();
		String token = studentService.addStudent(modelMapper.map(student,StudentBModel.class));
		if(token != null) {
			response.put("token", token);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}
		response.put("error", "Student already in database!");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	@RequestMapping(method = GET, value = "/{studentId}")
	public ResponseEntity<StudentAPIModel> getStudentById(@RequestParam int id) {
		StudentBModel studentBModel = studentService.getById(id);
		if(studentBModel == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		StudentAPIModel studentAPIModel = modelMapper.map(studentBModel, StudentAPIModel.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(studentAPIModel);
	}
	
	@RequestMapping(method = GET)
	public List<StudentAPIModel> getAllStudents() {
		List<StudentBModel> list = studentService.getAllStudents();
		List<StudentAPIModel> resultList = list.parallelStream().map(x -> modelMapper.map(x, StudentAPIModel.class)).collect(Collectors.toList());
		return resultList;
	}
	
	@RequestMapping(method = PUT)
	public ResponseEntity<StudentAPIModel> updateStudent(@RequestParam int id,@RequestBody StudentAPIModel student) {
		if(studentService.updateStudent(id, modelMapper.map(student, StudentBModel.class)))
				return ResponseEntity.status(HttpStatus.OK).body(student);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@RequestMapping(method = DELETE, value = "/{studentId}")
	public ResponseEntity<StudentAPIModel> deleteStudent(@RequestParam int studentId) {
		if(studentService.deleteStudentById(studentId))
				return ResponseEntity.status(HttpStatus.OK).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
