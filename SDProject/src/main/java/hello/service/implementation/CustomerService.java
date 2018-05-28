package hello.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.dao.dbModel.CustomerDB;
import hello.dao.dbModel.Role;
import hello.dao.repository.CustomerDAO;
import hello.service.Utils;
import hello.service.bllmodel.CustomerBModel;
import hello.service.interfaces.ICustomerService;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<CustomerBModel> getAllCustomers() {
		List<CustomerDB> list = customerDAO.findAll();
		List<CustomerBModel> resultList = list.parallelStream().map(x -> mapper.map(x, CustomerBModel.class)).collect(Collectors.toList());
		return resultList;
	}

	@Override
	public CustomerBModel getCustomerById(int id) {
		Optional<CustomerDB> reservationDB = customerDAO.findById(id);
		if(!reservationDB.isPresent())
			return null;
		CustomerBModel reservation = mapper.map(reservationDB.get(), CustomerBModel.class);
		return reservation;
	}

	@Override
	public boolean addCustomer(CustomerBModel customer) {
		CustomerDB customerDB = mapper.map(customer, CustomerDB.class);
		if(customerDAO.findByUsername(customer.getUsername()) != null)
			return false;
		customerDB.setPassword(Utils.computeHash(customer.getPassword()));
		customerDB.setRole(Role.ROLE_CUSTOMER);
		customerDB.setPasswordSet(true);
		customerDAO.save(customerDB);
		return true;
	}

	@Override
	public boolean updateCustomer(int id, CustomerBModel customer) {
		Optional<CustomerDB> customerDB = customerDAO.findById(id);
		if(customerDB.isPresent()) {
			CustomerDB custDB = customerDB.get();
			custDB.setEmail(customer.getEmail());
			custDB.setBalance(customer.getBalance());
			custDB.setName(customer.getName());
			custDB.setPassword(Utils.computeHash(customer.getPassword()));
			customerDAO.save(custDB);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCustomer(int id) {
		Optional<CustomerDB> customerDB = customerDAO.findById(id);
		if(customerDB.isPresent()) {
			customerDAO.delete(customerDB.get());
			return true;
		}
		return false;
	}

}
