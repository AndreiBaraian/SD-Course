package hello.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.dao.dbModel.StudentDB;
import hello.dao.repository.StudentDAO;
import hello.service.Utils;
import hello.service.bllmodel.StudentBModel;
import hello.service.interfaces.IStudentService;

@Service
public class StudentService implements IStudentService {
	
	@Autowired
	private StudentDAO studentDAO;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * returns the 128-character token
	 */
	public String addStudent(StudentBModel student) {
		if(studentDAO.findByEmail(student.getEmail()) == null) {
			StudentDB studentDB = modelMapper.map(student, StudentDB.class);
			studentDB.setToken(Utils.generateToken());
			studentDB.setPasswordSet(false);
			studentDAO.save(studentDB);
			return studentDB.getToken();
		}
		return null;
	}

	@Override
	public List<StudentBModel> getAllStudents() {
		List<StudentDB> list = studentDAO.findAll();
		List<StudentBModel> resultList = list.parallelStream().map(x -> modelMapper.map(x, StudentBModel.class)).collect(Collectors.toList());
		return resultList;
	}

	@Override
	public StudentBModel getById(int id) {
		Optional<StudentDB> studentDB = studentDAO.findById(id);
		if(studentDB.isPresent()){
			return modelMapper.map(studentDB.get(), StudentBModel.class);
		}
		return null;
	}

	@Override
	public boolean updateStudent(int studentId, StudentBModel student) {
		Optional<StudentDB> studentDB = studentDAO.findById(studentId);
		if(studentDB.isPresent()){
			StudentDB std = studentDB.get();
			std.setEmail(student.getEmail());
			std.setGroup(student.getGroup());
			std.setHobby(student.getHobby());
			studentDAO.save(std);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteStudentById(int studentId) {
		Optional<StudentDB> studentDB = studentDAO.findById(studentId);
		if(studentDB.isPresent()){
			studentDAO.delete(studentDB.get());
			return true;
		}
		return false;
	}

}
