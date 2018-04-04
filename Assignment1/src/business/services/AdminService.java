package business.services;

import org.modelmapper.ModelMapper;

import business.model.AdminModel;
import repository.AdminRepo;
import repository.IAbstractRepo;
import repository.dbmodel.Admin;

public class AdminService implements IAdminService {
	
	private IAbstractRepo<Admin> adminRepo;
	
	public AdminService() {
		adminRepo = new AdminRepo();
	}
	
	public boolean checkPassword(String username, String password) {
		Admin adminDB = adminRepo.findByField("username", username);
		if(adminDB == null)
			return false;
		ModelMapper modelMapper = new ModelMapper();
		AdminModel adminModel = modelMapper.map(adminDB, AdminModel.class);
		String passwordDB = PasswordEncryptor.computeHash(password);
		if(passwordDB.equals(adminModel.getPassword()))
			return true;
		return false;
	}

}
