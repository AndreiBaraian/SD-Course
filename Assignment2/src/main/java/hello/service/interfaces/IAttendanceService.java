package hello.service.interfaces;

import java.util.List;

import hello.exceptions.GetException;
import hello.service.bllmodel.AttendanceBModel;

public interface IAttendanceService {
	
	List<AttendanceBModel> getAllAttendancesByLab(int labId);
	AttendanceBModel getById(int id);
	AttendanceBModel addAttendance(int labId, int studentId) throws GetException;
	boolean updateAttendance(int id, int labId, int studentId);
	boolean deleteAttendanceById(int id);

}
