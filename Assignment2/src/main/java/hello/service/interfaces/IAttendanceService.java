package hello.service.interfaces;

import java.util.List;

import hello.service.bllmodel.AttendanceBModel;

public interface IAttendanceService {
	
	List<AttendanceBModel> getAllAssignments();
	AttendanceBModel getById(int id);
	boolean addAttendance(int labId, AttendanceBModel attendance);
	boolean updateAttendance(int id,AttendanceBModel attendance);
	boolean deleteAttendanceById(int id);

}
