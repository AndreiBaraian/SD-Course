package hello.service.implementation;

import java.util.List;
import java.util.Optional;
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
	
	@Autowired
	private LaboratoryDAO labDAO;
	
	@Autowired
	private ModelMapper mapper;
	

	@Override
	public List<LaboratoryBModel> getAllLaboratories() {
		List<LaboratoryDB> list = labDAO.findAll();
		List<LaboratoryBModel> resultList = list.parallelStream().map(x -> mapper.map(x, LaboratoryBModel.class)).collect(Collectors.toList());
		return resultList;
	}

	@Override
	public LaboratoryBModel getById(int id) {
		Optional<LaboratoryDB> labDB = labDAO.findById(id);
		if(!labDB.isPresent())
			return null;
		LaboratoryBModel lab = mapper.map(labDB.get(), LaboratoryBModel.class);
		return lab;
	}

	@Override
	public boolean addLaboratory(LaboratoryBModel lab) {
		if(lab.getLabNumber() < 1 || lab.getLabNumber() > 14)
			return false;
		LaboratoryDB labDB = mapper.map(lab, LaboratoryDB.class);
		if(labDAO.findByLabNumber(labDB.getLabNumber()) == null) {
			labDAO.save(labDB);
			return true;
		}
		else 
			return false;
	}

	@Override
	public boolean updateLaboratory(int labId,LaboratoryBModel lab) {
		LaboratoryDB labDB = labDAO.getOne(labId);
		if(labDB == null)
			return false;
		labDB.setCurricula(lab.getCurricula());
		labDB.setDate(lab.getDate());
		labDB.setLabNumber(lab.getLabNumber());
		labDB.setTitle(lab.getTitle());
		labDB.setDescription(lab.getDescription());
		if(labDAO.save(labDB) != null)
			return true;
		return false;
	}

	@Override
	public boolean deleteLaboratoryById(int id) {
		Optional<LaboratoryDB> labDB = labDAO.findById(id);
		if(labDB.isPresent()) {
			labDAO.delete(labDB.get());
			return true;
		}
		return false;
	}

	@Override
	public List<LaboratoryBModel> getLabsByKeyword(String keyword) {
		List<LaboratoryDB> list = labDAO.findAll().parallelStream()
				.filter(x -> x.getDescription().contains(keyword) || x.getCurricula().contains(keyword))
				.collect(Collectors.toList());
		List<LaboratoryBModel> resultList = list.parallelStream()
				.map(x -> mapper.map(x, LaboratoryBModel.class))
				.collect(Collectors.toList());
		return resultList;
	}

}
