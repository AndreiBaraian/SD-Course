package business.services;

import java.security.MessageDigest;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.DatatypeConverter;

import org.modelmapper.ModelMapper;

import business.model.CashierModel;
import exceptions.InsertException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.AbstractRepo;
import repository.CashierRepo;
import repository.dbmodel.Cashier;

public class CashierService {
	
	private AbstractRepo<Cashier> cashierRepo;
	private ModelMapper modelMapper;
	
	public CashierService() {
		this.cashierRepo = new CashierRepo();
		modelMapper = new ModelMapper();
	}
	
	public void addCashier(String firstName, String lastName, String username, String password) throws InsertException {
		String hashPass = computeHash(password);
		CashierModel cashierModel = new CashierModel(username,hashPass,firstName,lastName);
		Cashier cashierDB = modelMapper.map(cashierModel, Cashier.class);
		if(findAll().parallelStream().filter(r -> r.getUsername().equals(username)).count() == 0)
			cashierRepo.insert(cashierDB);
		else
			throw new InsertException("Cashier already in the database!");
	}
	
	public List<CashierModel> findAll() {
		List<Cashier> list = cashierRepo.findAll();
		List<CashierModel> resList = list.parallelStream().map(r -> modelMapper.map(r, CashierModel.class)).collect(Collectors.toList());
		return resList;
	}
	
	public ObservableList<CashierModel> displayAll() {
		ObservableList<CashierModel> cashiers = FXCollections.observableArrayList(findAll());
		return cashiers;
	}
	
	public void updateCashier(CashierModel cashierModel) {
		Cashier cashierDB = cashierRepo.findById(cashierModel.getId());
		cashierDB.setFirstName(cashierModel.getFirstName());
		cashierDB.setLastName(cashierModel.getLastName());
		cashierDB.setUsername(cashierModel.getUsername());
		if(cashierModel.getPassword() != "")
			cashierDB.setPassword(computeHash(cashierModel.getPassword()));
		cashierRepo.update(cashierDB);
	}
	
	public void deleteCashier(CashierModel cashierModel) {
		Cashier cashierDB = cashierRepo.findById(cashierModel.getId());
		cashierRepo.delete(cashierDB);
	}
	
	public boolean checkPassword(String username, String password) {
		Cashier cashierDB = cashierRepo.findByField("username", username);
		if(cashierDB == null)
			return false;
		//ModelMapper modelMapper = new ModelMapper();
		CashierModel cashierModel = modelMapper.map(cashierDB, CashierModel.class);
		String passwordDB = computeHash(password);
		if(passwordDB.equals(cashierModel.getPassword()))
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
