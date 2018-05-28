package hello.service.interfaces;

import java.util.List;

import hello.service.bllmodel.CustomerBModel;

public interface ICustomerService {
	
	public List<CustomerBModel> getAllCustomers();
	
	public CustomerBModel getCustomerById(int id);
	
	public boolean addCustomer(CustomerBModel customer);
	
	public boolean updateCustomer(int id, CustomerBModel customer);
	
	public boolean deleteCustomer(int id);

}
