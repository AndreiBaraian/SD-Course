package hello.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.dao.dbModel.ProductDB;

public interface ProductDAO extends JpaRepository<ProductDB,Integer>{
	
	public ProductDB findByName(String name);
	public ProductDB findByNameAndModel(String name, String model);
	public List<ProductDB> findByCustomerId(int customerId);

}
