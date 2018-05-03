package hello.service.interfaces;

import java.util.List;

import hello.service.bllmodel.StudentBModel;

public interface IStudentService {

	List<StudentBModel> getAllStudents();
	StudentBModel getById(int id);
	String addStudent(StudentBModel student);
	boolean updateStudent(int studentId, StudentBModel student);
	boolean deleteStudentById(int studentId);
	
}
