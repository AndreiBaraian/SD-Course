package hello.service.implementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.dao.dbModel.CustomerDB;
import hello.dao.dbModel.ProductDB;
import hello.dao.repository.CustomerDAO;
import hello.dao.repository.ProductDAO;
import hello.service.bllmodel.ProductBModel;
import hello.service.interfaces.IProductService;

@Service
public class ProductService implements IProductService {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<ProductBModel> getAllProducts() {
		List<ProductDB> list = productDAO.findAll();
		List<ProductBModel> resultList = list.parallelStream().map(x -> mapper.map(x, ProductBModel.class)).collect(Collectors.toList());
		return resultList;
	}
	
	@Override
	public ProductBModel getProductById(int id) {
		Optional<ProductDB> productDB = productDAO.findById(id);
		if(productDB.isPresent()) {
			return mapper.map(productDB.get(), ProductBModel.class);
		}
		return null;
	}

	@Override
	public boolean addProduct(ProductBModel product) {
		ProductDB productDB = mapper.map(product, ProductDB.class);
		if(productDAO.findByNameAndModel(product.getName(), product.getModel()) == null) {
			productDAO.save(productDB);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateProduct(int id, ProductBModel product) {
		Optional<ProductDB> productDB = productDAO.findById(id);
		if(productDB.isPresent()) {
			ProductDB prodDB = productDB.get();
			prodDB.setName(product.getName());
			prodDB.setPricePerDay(product.getPricePerDay());
			prodDB.setModel(product.getModel());
			prodDB.setRented(product.isRented());
			productDAO.save(prodDB);
			return true;
		}
		return false;
	}
	
	@Override
	public void rentProduct(int customerId, int productId) {
		CustomerDB customerDB = customerDAO.getOne(customerId);
		ProductDB productDB = productDAO.getOne(productId);
		productDB.setRented(true);
		productDB.setCustomer(customerDB);
		productDB.setDate(LocalDateTime.now());
		productDAO.save(productDB);
	}
	
	@Override
	public int returnProduct(int customerId, int productId) {
		CustomerDB customerDB = customerDAO.getOne(customerId);
		ProductDB productDB = productDAO.getOne(productId);
		productDB.setRented(false);
		productDB.setCustomer(null);
		LocalDateTime now = LocalDateTime.now();
		long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(productDB.getDate(),now);
		int price = (int) (productDB.getPricePerDay() * ((int)daysBetween + 1));
		System.out.println(daysBetween);
		productDAO.save(productDB);
		return price;
	}
	
	@Override
	public List<ProductBModel> getProductsByCustomerId(int customerId) { 
		List<ProductDB> list = productDAO.findByCustomerId(customerId);
		List<ProductBModel> resultList = list.parallelStream().map(x -> mapper.map(x, ProductBModel.class)).collect(Collectors.toList());
		return resultList;
	}

	@Override
	public boolean deleteProduct(int id) {
		Optional<ProductDB> productDB = productDAO.findById(id);
		if(productDB.isPresent()) {
			productDAO.delete(productDB.get());
			return true;
		}
		return false;
	}
	
}
