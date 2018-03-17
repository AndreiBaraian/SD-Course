package business.services;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

import org.modelmapper.ModelMapper;

import business.model.AdminModel;
import repository.AbstractRepo;
import repository.AdminRepo;
import repository.dbmodel.Admin;

public class AdminService {
	
	private AbstractRepo<Admin> adminRepo;
	
	public AdminService() {
		adminRepo = new AdminRepo();
	}
	
	public boolean checkPassword(String username, String password) {
		Admin adminDB = adminRepo.findByField("username", username);
		if(adminDB == null)
			return false;
		ModelMapper modelMapper = new ModelMapper();
		AdminModel adminModel = modelMapper.map(adminDB, AdminModel.class);
		String passwordDB = computeHash(password);
		if(passwordDB.equals(adminModel.getPassword()))
			return true;
		return false;
	}
	
	private String computeHash(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes("UTF-8"));
			return DatatypeConverter.printHexBinary(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
