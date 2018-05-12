package hello.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.dao.dbModel.Role;
import hello.dao.dbModel.StudentDB;
import hello.dao.dbModel.UserDB;
import hello.dao.repository.StudentDAO;
import hello.dao.repository.UserDAO;
import hello.exceptions.LoginException;
import hello.service.Utils;
import hello.service.interfaces.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private StudentDAO studentDAO;
	
	public Role login(String email, String password) throws LoginException{
		UserDB userDB = userDAO.findUserByEmail(email);
		if(userDB == null)
			throw new LoginException("User not found!");
		if(userDB.isPasswordSet() == false)
			throw new LoginException("Password not set, please register first!");
		String hashPassword = Utils.computeHash(password);
		if(hashPassword.equals(userDB.getPassword()))
			return userDB.getRole();
		return null;
	}
	
	public void register(String email, String password, String token) throws LoginException{
		StudentDB studentDB = studentDAO.findByEmail(email);
		if(studentDB == null)
			throw new LoginException("User not found!");
		if(!token.equals(studentDB.getToken()))
			throw new LoginException("Incorrect authentification token!");
		studentDB.setPassword(Utils.computeHash(password));
		studentDB.setPasswordSet(true);
		studentDAO.save(studentDB);
	}
	
	public int getUserIdByEmail(String email) {
		int id = userDAO.findUserByEmail(email).getId();
		return id;
	}
	
}
