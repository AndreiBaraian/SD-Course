package business.services;

import java.util.List;

import business.model.CashierModel;
import exceptions.InsertException;
import javafx.collections.ObservableList;

public interface ICashierService {
	
	public void addCashier(String firstName, String lastName, String username, String password) throws InsertException;
	
	public List<CashierModel> findAll();
	
	public ObservableList<CashierModel> displayAll();

	public void updateCashier(CashierModel cashierModel);
	
	public void deleteCashier(CashierModel cashierModel);
	
	public boolean checkPassword(String username, String password);

}
