package hello;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hello.dao.dbModel.LaboratoryDTO;
import hello.dao.repository.LaboratoryDAO;

@RestController
public class HelloController {
	
	@Autowired
	private LaboratoryDAO laboratoryDAO;
	
	 @RequestMapping("/")
	    public String index() {
	        return "Greetings from Spring Boot!";
	    }
	 
	 @RequestMapping("/create")
	 @ResponseBody
	 public String create() {
		 LaboratoryDTO lab = new LaboratoryDTO();
		 lab.setLabNumber(3);
		 lab.setCurricula("A curricula");
		 lab.setDate(Timestamp.valueOf("2007-09-23 10:10:10.0"));
		 lab.setId(3);
		 lab.setTitle("My title");
		 laboratoryDAO.save(lab);
		 return "Success";
	 }

}
