package business.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import business.model.ShowModel;
import exceptions.InsertException;
import repository.AbstractRepo;
import repository.ShowRepo;
import repository.dbmodel.Show;

public class ShowService {
	
	private AbstractRepo<Show> showRepo;
	private ModelMapper modelMapper;
	
	public ShowService() {
		this.showRepo = new ShowRepo();
		this.modelMapper = new ModelMapper();
	}
	
	public List<ShowModel> findAll() {
		List<Show> list = showRepo.findAll();
		List<ShowModel> resList = list.parallelStream().map(r -> modelMapper.map(r, ShowModel.class)).collect(Collectors.toList());
		return resList;
	}
	
	public void addShow(String genre, String title, String distribution, String date, int tickets) throws InsertException {
		ShowModel showModel = new ShowModel(genre,title,distribution,date,tickets);
		Show showDB = modelMapper.map(showModel, Show.class);
		if(findAll().parallelStream().filter(r -> (r.getTitle().equals(title) && r.getDateOfShow().equals(date))).count() == 0)
			showRepo.insert(showDB);
		else
			throw new InsertException("Show already on schedule for that day!");
	}

}
