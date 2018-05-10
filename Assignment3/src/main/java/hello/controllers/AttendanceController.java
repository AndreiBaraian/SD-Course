package hello.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import hello.apimodels.AttendanceAPIModel;
import hello.apimodels.LaboratoryAPIModel;
import hello.apimodels.StudentAPIModel;
import hello.exceptions.GetException;
import hello.service.bllmodel.AttendanceBModel;
import hello.service.bllmodel.LaboratoryBModel;
import hello.service.bllmodel.StudentBModel;
import hello.service.interfaces.IAttendanceService;
import hello.service.interfaces.ILaboratoryService;
import hello.service.interfaces.IStudentService;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
	
	@Autowired 
	private IAttendanceService attService;
	
	@Autowired 
	private IStudentService studentService;
	
	@Autowired
	private ILaboratoryService labService;
	
	@Autowired
	private ModelMapper mapper;
	
	@RequestMapping(method = GET)
	public ModelAndView getAllAttendancesByLab(@RequestParam("labId") int labId){
		ModelAndView mv = new ModelAndView("listAttendances");
		List<AttendanceBModel> list = attService.getAllAttendancesByLab(labId);
		List<AttendanceAPIModel> resultList = list.parallelStream().map(x -> mapper.map(x, AttendanceAPIModel.class)).collect(Collectors.toList());
		mv.addObject("attendances",resultList);
		return mv;
	}
	
	@RequestMapping(method = POST)
	public ResponseEntity<AttendanceAPIModel> addAttendance(@RequestParam("labId") int labId,@RequestParam("studentId") int studentId){
		AttendanceAPIModel attendance = null;
		try {
			attendance = mapper.map(attService.addAttendance(labId, studentId), AttendanceAPIModel.class);
		} catch (GetException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(attendance);
	}
	
	@RequestMapping(method = DELETE)
	public ResponseEntity<AttendanceAPIModel> deleteAttendance(@RequestParam int attId){
		if(attService.deleteAttendanceById(attId))
			return ResponseEntity.status(HttpStatus.OK).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@RequestMapping(method = PUT)
	public ResponseEntity<AttendanceAPIModel> updateAttendance(@RequestParam int attId, @RequestParam int labId, @RequestParam int studentId){
		if(attService.updateAttendance(attId, labId, studentId))
			return ResponseEntity.status(HttpStatus.OK).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@RequestMapping(method = GET, value = "/{attId}")
	public ResponseEntity<AttendanceAPIModel> getAttendanceById(@RequestParam int attId){
		AttendanceAPIModel attendance = null;
		AttendanceBModel att = attService.getById(attId);
		if(att != null){
			attendance = mapper.map(att, AttendanceAPIModel.class);
			return ResponseEntity.status(HttpStatus.OK).body(attendance);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@RequestMapping(value = "/student/{labId}")
	public ModelAndView getStudents(@PathVariable("labId") int labId) {
		ModelAndView mv = new ModelAndView("listAttendanceStudents");
		List<StudentBModel> list = studentService.getAllStudents();
		List<StudentAPIModel> resultList = list.parallelStream().map(x -> mapper.map(x, StudentAPIModel.class)).collect(Collectors.toList());
		mv.addObject("students",resultList);
		mv.addObject("labId",labId);
		return mv;
	}
	
}




