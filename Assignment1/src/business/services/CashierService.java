package business.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import business.model.CashierModel;
import exceptions.InsertException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.CashierRepo;
import repository.IAbstractRepo;
import repository.dbmodel.Cashier;

public class CashierService implements ICashierService {
	
	private IAbstractRepo<Cashier> cashierRepo;
	private ModelMapper modelMapper;
	
	public CashierService() {
		this.cashierRepo = new CashierRepo();
		modelMapper = new ModelMapper();
	}
	
	public void addCashier(String firstName, String lastName, String username, String password) throws InsertException {
		String hashPass = PasswordEncryptor.computeHash(password);
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
		if(cashierModel.getPassword() != null)
			cashierDB.setPassword(PasswordEncryptor.computeHash(cashierModel.getPassword()));
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
		CashierModel cashierModel = modelMapper.map(cashierDB, CashierModel.class);
		String passwordDB = PasswordEncryptor.computeHash(password);
		if(passwordDB.equals(cashierModel.getPassword()))
			return true;
		return false;
	}

}
