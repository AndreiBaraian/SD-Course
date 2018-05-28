package hello.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hello.apimodel.EmployeeAPIModel;
import hello.service.bllmodel.EmployeeBModel;
import hello.service.interfaces.IEmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private ModelMapper mapper;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeAPIModel>> getAllEmployees() {
		List<EmployeeBModel> list = employeeService.getAllEmployees();
		List<EmployeeAPIModel> resultList = list.parallelStream().map(x -> mapper.map(x, EmployeeAPIModel.class)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{employeeId}")
	public ResponseEntity<EmployeeAPIModel> getEmployeeById(@PathVariable("employeeId") int employeeId){
		EmployeeBModel employeeB = employeeService.getEmployeeById(employeeId);
		if(employeeB == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		EmployeeAPIModel employee = mapper.map(employeeB, EmployeeAPIModel.class);
		return ResponseEntity.status(HttpStatus.OK).body(employee);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<EmployeeAPIModel> addEmployee(@RequestBody EmployeeAPIModel employee){
		if(employeeService.addEmployee(mapper.map(employee, EmployeeBModel.class)))
			return ResponseEntity.status(HttpStatus.OK).body(employee);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{employeeId}")
	public ResponseEntity<EmployeeAPIModel> updateEmployee(@PathVariable("employeeId") int employeeId,@RequestBody EmployeeAPIModel employee){
		if(employeeService.updateEmployee(employeeId, mapper.map(employee, EmployeeBModel.class)))
			return ResponseEntity.status(HttpStatus.OK).body(employee);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{employeeId}")
	public ResponseEntity<EmployeeAPIModel> deleteEmployee(@PathVariable("employeeId") int employeeId){
		if(employeeService.deleteEmployee(employeeId))
			return ResponseEntity.status(HttpStatus.OK).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
}
