package hello.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.dao.dbModel.ProductDB;

public interface ProductDAO extends JpaRepository<ProductDB,Integer>{

}
