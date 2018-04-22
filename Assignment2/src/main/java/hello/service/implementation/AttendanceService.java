package hello.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.dao.dbModel.AttendanceDB;
import hello.dao.dbModel.LaboratoryDB;
import hello.dao.dbModel.StudentDB;
import hello.dao.repository.AttendanceDAO;
import hello.dao.repository.LaboratoryDAO;
import hello.dao.repository.StudentDAO;
import hello.exceptions.GetException;
import hello.service.bllmodel.AttendanceBModel;
import hello.service.interfaces.IAttendanceService;

@Service
public class AttendanceService implements IAttendanceService {
	
	@Autowired 
	private AttendanceDAO attendanceDAO;
	
	@Autowired 
	private LaboratoryDAO labDAO;
	
	@Autowired
	private StudentDAO studentDAO;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<AttendanceBModel> getAllAttendancesByLab(int labId) {
		List<AttendanceDB> list = attendanceDAO.findAttendanceByLaboratoryId(labId);
		List<AttendanceBModel> resultList = list.parallelStream().map(x -> modelMapper.map(x, AttendanceBModel.class)).collect(Collectors.toList());
		return resultList;
	}

	@Override
	public AttendanceBModel getById(int id) {
		Optional<AttendanceDB> attendanceDB = attendanceDAO.findById(id);
		if(attendanceDB.isPresent())
			return modelMapper.map(attendanceDB.get(), AttendanceBModel.class);
		return null;
	}

	@Override
	public AttendanceBModel addAttendance(int labId, int studentId) throws GetException {
		Optional<LaboratoryDB> labDB = labDAO.findById(labId);
		if(!labDB.isPresent())
			throw new GetException("Laboratory not found!");
		LaboratoryDB lab = labDB.get();
		Optional<StudentDB> studentDB = studentDAO.findById(studentId);
		if(!studentDB.isPresent())
			throw new GetException("Student not found!");
		StudentDB student = studentDB.get();
		AttendanceDB attendanceDB = new AttendanceDB();
		attendanceDB.setLaboratory(lab);
		attendanceDB.setStudent(student);
		return modelMapper.map(attendanceDAO.save(attendanceDB), AttendanceBModel.class);
	}

	@Override
	public boolean updateAttendance(int attId,int labId, int studentId) {
		Optional<AttendanceDB> attendanceDB = attendanceDAO.findById(attId);
		if(attendanceDB.isPresent()){
			AttendanceDB attDB = attendanceDB.get();
			Optional<StudentDB> student = studentDAO.findById(studentId);
			if(!student.isPresent())
				return false;
			Optional<LaboratoryDB> lab = labDAO.findById(labId);
			if(!lab.isPresent())
				return false;
			attDB.setLaboratory(lab.get());
			attDB.setStudent(student.get());
			attendanceDAO.save(attDB);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteAttendanceById(int id) {
		Optional<AttendanceDB> attendanceDB = attendanceDAO.findById(id);
		if(attendanceDB.isPresent()){
			attendanceDAO.delete(attendanceDB.get());
			return true;
		}
		return false;
	}

}
