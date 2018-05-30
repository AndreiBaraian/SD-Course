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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.apimodel.CustomerAPIModel;
import hello.service.bllmodel.CustomerBModel;
import hello.service.interfaces.ICustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired 
	private ICustomerService customerService;
	
	@Autowired
	private ModelMapper mapper;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CustomerAPIModel>> getAllCustomers() {
		List<CustomerBModel> list = customerService.getAllCustomers();
		List<CustomerAPIModel> resultList = list.parallelStream().map(x -> mapper.map(x, CustomerAPIModel.class)).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(resultList);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{customerId}")
	public ResponseEntity<CustomerAPIModel> getCustomerById(@PathVariable("customerId") int customerId){
		CustomerBModel customerB = customerService.getCustomerById(customerId);
		if(customerB == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		CustomerAPIModel customer = mapper.map(customerB, CustomerAPIModel.class);
		return ResponseEntity.status(HttpStatus.OK).body(customer);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CustomerAPIModel> addCustomer(@RequestBody CustomerAPIModel customer){
		if(customerService.addCustomer(mapper.map(customer, CustomerBModel.class)))
			return ResponseEntity.status(HttpStatus.OK).body(customer);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{customerId}")
	public ResponseEntity<CustomerAPIModel> updateCustomer(@PathVariable("customerId") int customerId,@RequestBody CustomerAPIModel customer){
		if(customerService.updateCustomer(customerId, mapper.map(customer, CustomerBModel.class)))
			return ResponseEntity.status(HttpStatus.OK).body(customer);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{customerId}")
	public ResponseEntity<CustomerAPIModel> deleteCustomer(@PathVariable("customerId") int customerId){
		if(customerService.deleteCustomer(customerId))
			return ResponseEntity.status(HttpStatus.OK).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
