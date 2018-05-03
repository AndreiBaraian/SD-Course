package hello.service.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.dao.dbModel.AssignmentDB;
import hello.dao.dbModel.LaboratoryDB;
import hello.dao.dbModel.SubmissionDB;
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
	private ModelMapper mapper;
	
	@Override
	public List<AssignmentBModel> getAllAssignments() {
		List<AssignmentDB> list = assignmentDAO.findAll();
		List<AssignmentBModel> resultList = list.parallelStream().map(x -> mapper.map(x, AssignmentBModel.class)).collect(Collectors.toList());
		return resultList;
	}

	@Override
	public AssignmentBModel getById(int id) {
		Optional<AssignmentDB> assignmentDB = assignmentDAO.findById(id);
		if(assignmentDB.isPresent()){
			return mapper.map(assignmentDB.get(), AssignmentBModel.class);
		}
		return null;
	}
	
	@Override
	public List<AssignmentBModel> getAssignmentsByLab(int labId){
		List<AssignmentDB> list = assignmentDAO.findAssignmentsByLaboratoryId(labId);
		List<AssignmentBModel> resultList = list.parallelStream().map(x -> mapper.map(x, AssignmentBModel.class)).collect(Collectors.toList());
		return resultList;	
	}

	@Override
	public boolean addAssignment(int labId,AssignmentBModel assignment) {
		AssignmentDB assignmentDB = mapper.map(assignment, AssignmentDB.class);
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
	
	public HashMap<String,Integer> getGradesForAssignment(int assignmentId){
		Optional<AssignmentDB> assignment = assignmentDAO.findById(assignmentId);
		if(!assignment.isPresent())
			return null;
		Set<SubmissionDB> submissions = assignment.get().getSubmissions();
		HashMap<String,Integer> grades = new HashMap<>();
		grades = (HashMap<String, Integer>) submissions.parallelStream().collect(Collectors.toMap(x-> x.getStudent().getName(), x -> x.getGrade()));
		return grades;
	}

}
