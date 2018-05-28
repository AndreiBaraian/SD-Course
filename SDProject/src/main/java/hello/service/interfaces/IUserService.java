package hello.service.interfaces;

import hello.dao.dbModel.Role;
import hello.exception.LoginException;
import hello.service.bllmodel.UserBModel;

public interface IUserService {
	
	public Role login(String email, String password) throws LoginException;
	
	public int getUserIdByEmail(String email);
	
	public UserBModel getUserByUsername(String username);

}
