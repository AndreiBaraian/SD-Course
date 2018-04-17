package hello.service.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.dao.dbModel.AssignmentDBModel;
import hello.dao.repository.AssignmentDAO;
import hello.service.bllmodel.AssignmentBModel;
import hello.service.interfaces.IAssignmentService;

@Service
public class AssignmentService implements IAssignmentService {

	private AssignmentDAO assignmentDAO;
	private ModelMapper modelMapper;
	
	@Autowired
	public AssignmentService(AssignmentDAO assignmentDAO) {
		this.assignmentDAO = assignmentDAO;
		this.modelMapper = new ModelMapper();
	}
	
	@Override
	public List<AssignmentBModel> getAllAssignment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AssignmentBModel getById(AssignmentBModel assignment) {
		return null;
	}

	@Override
	public boolean addAssignment(AssignmentBModel assignment) {
		AssignmentDBModel assignmentDB = modelMapper.map(assignment, AssignmentDBModel.class);
		if(assignmentDAO.findByName(assignment.getName()) == null) {
			assignmentDAO.save(assignmentDB);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateAssignment(AssignmentBModel assignment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAssignmentById(AssignmentBModel assignment) {
		// TODO Auto-generated method stub
		return false;
	}

}
