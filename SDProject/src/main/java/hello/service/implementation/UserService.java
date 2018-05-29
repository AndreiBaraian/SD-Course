package hello.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.dao.dbModel.Role;
import hello.dao.dbModel.UserDB;
import hello.dao.repository.UserDAO;
import hello.exception.LoginException;
import hello.service.Utils;
import hello.service.bllmodel.UserBModel;
import hello.service.interfaces.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private ModelMapper mapper;
	
	public Role login(String username, String password) throws LoginException{
		UserDB userDB = userDAO.findByUsername(username);
		if(userDB == null)
			throw new LoginException("User not found!");
		if(userDB.isPasswordSet() == false)
			throw new LoginException("Password not set, please register first!");
		String hashPassword = Utils.computeHash(password);
		if(hashPassword.equals(userDB.getPassword()))
			return userDB.getRole();
		return null;
	}
	
	public int getUserIdByEmail(String email) {
		int id = userDAO.findUserByEmail(email).getId();
		return id;
	}
	
	public UserBModel getUserByUsername(String username) {
		UserDB user = userDAO.findByUsername(username);
		return mapper.map(user, UserBModel.class);
	}
	
	public int getUserIdByUsername(String username) {
		int id = userDAO.findByUsername(username).getId();
		return id;
	}
	
}
