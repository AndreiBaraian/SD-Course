package hello.service.implementation;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.dao.dbModel.EmployeeDB;
import hello.dao.dbModel.Role;
import hello.dao.repository.EmployeeDAO;
import hello.service.bllmodel.EmployeeBModel;
import hello.service.interfaces.IEmployeeService;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<EmployeeBModel> getAllEmployees() {
		List<EmployeeDB> list = employeeDAO.findAll();
		List<EmployeeBModel> resultList = list.parallelStream().map(x -> mapper.map(x, EmployeeBModel.class)).collect(Collectors.toList());
		return resultList;
	}

	@Override
	public EmployeeBModel getEmployeeById(int id) {
		Optional<EmployeeDB> employeeDB = employeeDAO.findById(id);
		if(employeeDB.isPresent()) {
			return mapper.map(employeeDB.get(), EmployeeBModel.class);
		}
		return null;
	}

	@Override
	public boolean addEmployee(EmployeeBModel employee) {
		if(employeeDAO.findByEmail(employee.getEmail()) != null)
			return false;
		EmployeeDB employeeDB = mapper.map(employee, EmployeeDB.class);
		employeeDB.setPasswordSet(false);
		employeeDB.setRole(Role.ROLE_EMPLOYEE);
		employeeDB.setContractReference(getNextContractReference());
		employeeDAO.save(employeeDB);
		return true;
	}
	
	private String getNextContractReference() {
		List<EmployeeDB> allEmployees = employeeDAO.findAll();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		StringBuilder newSysReference = new StringBuilder();
		newSysReference.append("REF");
		newSysReference.append("-");
		newSysReference.append(LocalDateTime.now().format(formatter));
		newSysReference.append("-");
		if(allEmployees.isEmpty()) {
			newSysReference.append("00000000");
		}
		else {
			Comparator<String> sysReferenceComparator = new Comparator<String>() {
				@Override
				public int compare(String ref1, String ref2) {
					Long refNo1 = Long.parseLong(ref1.substring(15));
					Long refNo2 = Long.parseLong(ref2.substring(15));
					return refNo1.compareTo(refNo2);
				}
			};
			String currentSysReference = allEmployees.parallelStream().map(p->p.getContractReference()).max(sysReferenceComparator).get();
			Long currentNumber = Long.parseLong(currentSysReference.substring(15));
			currentNumber++;
			DecimalFormat myFormatter = new DecimalFormat("00000000");
			newSysReference.append(myFormatter.format(currentNumber));
		}
		return newSysReference.toString();
	}

	@Override
	public boolean updateEmployee(int id, EmployeeBModel employee) {
		Optional<EmployeeDB> employeeDB = employeeDAO.findById(id);
		if(employeeDB.isPresent()) {
			EmployeeDB emplDB = employeeDB.get();
			emplDB.setEmail(employee.getEmail());
			emplDB.setName(employee.getName());
			emplDB.setPassword(employee.getPassword());
			emplDB.setUsername(employee.getUsername());
			emplDB.setContractExpiration(employee.getContractExpiration());
			employeeDAO.save(emplDB);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteEmployee(int id) {
		Optional<EmployeeDB> employeeDB = employeeDAO.findById(id);
		if(employeeDB.isPresent()) {
			employeeDAO.delete(employeeDB.get());
			return true;
		}
		return false;
	}

}
