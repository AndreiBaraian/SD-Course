package hello.service.interfaces;

import java.util.List;

import hello.service.bllmodel.LaboratoryBModel;

public interface ILaboratoryService {
	
	List<LaboratoryBModel> getAllLaboratories();
	LaboratoryBModel getById(int id);
	boolean addLaboratory(LaboratoryBModel lab);
	boolean updateLaboratory(int labId, LaboratoryBModel lab);
	boolean deleteLaboratoryById(int id);

}
