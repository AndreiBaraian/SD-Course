package hello.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import hello.apimodels.LaboratoryAPIModel;
import hello.controllers.LaboratoryController;
import hello.service.bllmodel.LaboratoryBModel;
import hello.service.interfaces.ILaboratoryService;

@RunWith(SpringRunner.class)
@WebMvcTest(LaboratoryController.class)
public class LaboratoryControllerTest {

	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private ModelMapper mapper;
	
	@MockBean
	private ILaboratoryService labService;
	
	@Test
	public void testGetAllLabs() throws Exception{
		
		LaboratoryBModel lab1 = new LaboratoryBModel(1,LocalDateTime.now(),"Lab1","A curricula","A description");
		List<LaboratoryBModel> labs = new ArrayList<>();
		labs.add(lab1);
		
		given(labService.getAllLaboratories()).willReturn(labs);
		
		mvc.perform(get("/lab")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$",hasSize(1)));
		//.andExpect(jsonPath("$[0].title",is(lab1.getTitle())));
		
	}
	
	/*
	@Test
	public void testGetLabById() throws Exception{
		LaboratoryBModel lab1 = new LaboratoryBModel(1,LocalDateTime.now(),"Lab1","A curricula","A description");
		
		given(labService.getById(1)).willReturn(lab1);
		
		mvc.perform(get("/lab/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.title",is("Lab1")));
	}
	*/
	
	@Test
	public void testAddLab() throws Exception {
		LaboratoryAPIModel labAPIModel = new LaboratoryAPIModel(1,LocalDateTime.now(),"Lab1","A curricula","A description");
		
		given(labService.addLaboratory(mapper.map(labAPIModel, LaboratoryBModel.class))).willReturn(true);
		
		mvc.perform(post("/lab")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
	}
	
	@Test
	public void testEditLab() throws Exception {
		LaboratoryAPIModel labAPIModel = new LaboratoryAPIModel(1,LocalDateTime.now(),"Lab1","A curricula","A description");
		
		given(labService.updateLaboratory(1,mapper.map(labAPIModel, LaboratoryBModel.class))).willReturn(true);
		
		mvc.perform(post("/lab/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
	}
	
	@Test
	public void testDeleteLab() throws Exception {
		
		given(labService.deleteLaboratoryById(1)).willReturn(true);
		
		mvc.perform(post("/lab/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
	}
	
	
}

