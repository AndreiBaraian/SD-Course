package hello.service.interfaces;

import hello.dao.dbModel.Role;
import hello.exceptions.LoginException;

public interface IUserService {
	
	public Role login(String email, String password) throws LoginException;
	
	public void register(String email, String password, String token) throws LoginException;

}
