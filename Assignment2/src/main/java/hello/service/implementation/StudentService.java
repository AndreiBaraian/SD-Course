package hello.service.implementation;

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
	
	private StudentDAO studentDAO;
	private ModelMapper modelMapper;
	
	@Autowired
	public StudentService(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
		this.modelMapper = new ModelMapper();
	}
	
	/**
	 * returns the 128-character token
	 */
	public String addStudent(StudentBModel student) {
		if(studentDAO.findByEmail(student.getEmail()) == null) {
			StudentDB studentDB = modelMapper.map(student, StudentDB.class);
			studentDB.setPassword(Utils.computeHash(student.getPassword()));
			studentDB.setToken(Utils.generateToken());
			studentDB.setPasswordSet(false);
			studentDAO.save(studentDB);
			return studentDB.getToken();
		}
		return null;
	}

}
