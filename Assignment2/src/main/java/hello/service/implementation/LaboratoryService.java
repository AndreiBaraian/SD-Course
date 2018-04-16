package hello.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.dao.dbModel.LaboratoryDTO;
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
		List<LaboratoryDTO> list = labDAO.findAll();
		List<LaboratoryBModel> resultList = list.parallelStream().map(x -> modelMapper.map(x, LaboratoryBModel.class)).collect(Collectors.toList());
		return resultList;
	}

	@Override
	public LaboratoryBModel getById(int id) {
		LaboratoryDTO labDTO = labDAO.getOne(id);
		LaboratoryBModel lab = modelMapper.map(labDTO, LaboratoryBModel.class);
		return lab;
	}

	@Override
	public boolean addLaboratory(LaboratoryBModel lab) {
		if(lab.getLabNumber() < 1 || lab.getLabNumber() > 14)
			return false;
		LaboratoryDTO labDTO = modelMapper.map(lab, LaboratoryDTO.class);
		if(labDAO.findByLabNumber(labDTO.getLabNumber()) == null) {
			labDAO.save(labDTO);
			return true;
		}
		else 
			return false;
	}

	@Override
	public boolean updateLaboratory(LaboratoryBModel lab) {
		LaboratoryDTO labDTO = labDAO.getOne(lab.getId());
		if(labDTO == null)
			return false;
		labDTO.setCurricula(lab.getCurricula());
		labDTO.setDate(lab.getDate());
		labDTO.setLabNumber(lab.getLabNumber());
		labDTO.setTitle(lab.getTitle());
		if(labDAO.save(labDTO) != null)
			return true;
		return false;
	}

	@Override
	public boolean deleteLaboratoryById(int id) {
		LaboratoryDTO labDTO = labDAO.getOne(id);
		if(labDTO == null)
			return false;
		labDAO.delete(labDTO);
		return true;
	}

}
