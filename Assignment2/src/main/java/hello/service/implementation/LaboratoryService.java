package hello.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.dao.dbModel.LaboratoryDB;
import hello.dao.repository.LaboratoryDAO;
import hello.service.bllmodel.LaboratoryBModel;
import hello.service.interfaces.ILaboratoryService;

@Service
public class LaboratoryService implements ILaboratoryService {
	
	private final LaboratoryDAO labDAO;
	private ModelMapper modelMapper;
	
	@Autowired
	public LaboratoryService(LaboratoryDAO labDAO) {
		this.labDAO = labDAO;
		this.modelMapper = new ModelMapper();
	}

	@Override
	public List<LaboratoryBModel> getAllLaboratories() {
		List<LaboratoryDB> list = labDAO.findAll();
		List<LaboratoryBModel> resultList = list.parallelStream().map(x -> modelMapper.map(x, LaboratoryBModel.class)).collect(Collectors.toList());
		return resultList;
	}

	@Override
	public LaboratoryBModel getById(int id) {
		LaboratoryDB labDB = labDAO.getOne(id);
		LaboratoryBModel lab = modelMapper.map(labDB, LaboratoryBModel.class);
		return lab;
	}

	@Override
	public boolean addLaboratory(LaboratoryBModel lab) {
		if(lab.getLabNumber() < 1 || lab.getLabNumber() > 14)
			return false;
		LaboratoryDB labDB = modelMapper.map(lab, LaboratoryDB.class);
		if(labDAO.findByLabNumber(labDB.getLabNumber()) == null) {
			labDAO.save(labDB);
			return true;
		}
		else 
			return false;
	}

	@Override
	public boolean updateLaboratory(LaboratoryBModel lab) {
		LaboratoryDB labDB = labDAO.getOne(lab.getId());
		if(labDB == null)
			return false;
		labDB.setCurricula(lab.getCurricula());
		labDB.setDate(lab.getDate());
		labDB.setLabNumber(lab.getLabNumber());
		labDB.setTitle(lab.getTitle());
		if(labDAO.save(labDB) != null)
			return true;
		return false;
	}

	@Override
	public boolean deleteLaboratoryById(int id) {
		LaboratoryDB labDB = labDAO.getOne(id);
		if(labDB == null)
			return false;
		labDAO.delete(labDB);
		return true;
	}

}
