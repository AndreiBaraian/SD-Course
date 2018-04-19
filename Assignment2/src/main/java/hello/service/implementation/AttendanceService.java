package hello.service.implementation;

import java.util.List;

import hello.service.bllmodel.AttendanceBModel;
import hello.service.interfaces.IAttendanceService;

public class AttendanceService implements IAttendanceService {

	@Override
	public List<AttendanceBModel> getAllAssignments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AttendanceBModel getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addAttendance(int labId, AttendanceBModel attendance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAttendance(int id, AttendanceBModel attendance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAttendanceById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
