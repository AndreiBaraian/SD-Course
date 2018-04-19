package hello.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.dao.dbModel.AssignmentDB;
import hello.dao.dbModel.LaboratoryDB;
import hello.dao.repository.AssignmentDAO;
import hello.dao.repository.LaboratoryDAO;
import hello.service.bllmodel.AssignmentBModel;
import hello.service.interfaces.IAssignmentService;

@Service
public class AssignmentService implements IAssignmentService {

	@Autowired
	private AssignmentDAO assignmentDAO;
	
	@Autowired
	private LaboratoryDAO labDAO;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<AssignmentBModel> getAllAssignments() {
		List<AssignmentDB> list = assignmentDAO.findAll();
		List<AssignmentBModel> resultList = list.parallelStream().map(x -> modelMapper.map(x, AssignmentBModel.class)).collect(Collectors.toList());
		return resultList;
	}

	@Override
	public AssignmentBModel getById(int id) {
		Optional<AssignmentDB> assignmentDB = assignmentDAO.findById(id);
		if(assignmentDB.isPresent()){
			return modelMapper.map(assignmentDB.get(), AssignmentBModel.class);
		}
		return null;
	}

	@Override
	public boolean addAssignment(int labId,AssignmentBModel assignment) {
		AssignmentDB assignmentDB = modelMapper.map(assignment, AssignmentDB.class);
		Optional<LaboratoryDB> lab = labDAO.findById(labId);
		if(!lab.isPresent())
			return false;
		assignmentDB.setLaboratory(lab.get());
		if(assignmentDAO.findByName(assignment.getName()) == null) {
			assignmentDAO.save(assignmentDB);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateAssignment(int id,AssignmentBModel assignment) {
		Optional<AssignmentDB> assignmentDB = assignmentDAO.findById(id);
		if(assignmentDB.isPresent()){
			AssignmentDB aDB = assignmentDB.get();
			aDB.setDeadline(assignment.getDeadline());
			aDB.setDescription(assignment.getDescription());
			aDB.setName(assignment.getName());
			assignmentDAO.save(aDB);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteAssignmentById(int id) {
		Optional<AssignmentDB> assignmentDB = assignmentDAO.findById(id);
		if(assignmentDB.isPresent()) {
			assignmentDAO.delete(assignmentDB.get());
			return true;
		}
		return false;
	}

}
