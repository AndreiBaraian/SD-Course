package hello.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.dao.repository.UserDAO;

@Service
public class UserService {

	private UserDAO userDAO;
	private ModelMapper modelMapper;
	
	@Autowired
	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
		this.modelMapper = new ModelMapper();
	}
	
}
