package hello.service.interfaces;

import hello.exceptions.LoginException;

public interface IUserService {
	
	public boolean login(String email, String password) throws LoginException;
	
	public void register(String email, String password, String token) throws LoginException;

}
