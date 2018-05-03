package hello.service.interfaces;

import java.util.List;

import hello.service.bllmodel.LaboratoryBModel;

public interface ILaboratoryService {
	
	public List<LaboratoryBModel> getAllLaboratories();
	public List<LaboratoryBModel> getLabsByKeyword(String keyword);
	public LaboratoryBModel getById(int id);
	public boolean addLaboratory(LaboratoryBModel lab);
	public boolean updateLaboratory(int labId, LaboratoryBModel lab);
	public boolean deleteLaboratoryById(int id);

}
