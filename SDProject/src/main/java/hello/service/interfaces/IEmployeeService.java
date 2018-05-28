package hello.service.interfaces;

import java.util.List;

import hello.service.bllmodel.EmployeeBModel;

public interface IEmployeeService {
	
	public List<EmployeeBModel> getAllEmployees();
	
	public EmployeeBModel getEmployeeById(int id);
	
	public boolean addEmployee(EmployeeBModel employee);
	
	public boolean updateEmployee(int id, EmployeeBModel employee);
	
	public boolean deleteEmployee(int id);

}
