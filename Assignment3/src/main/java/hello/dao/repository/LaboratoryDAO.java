package hello.dao.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hello.dao.dbModel.LaboratoryDB;

@Repository
@Transactional
public interface LaboratoryDAO extends JpaRepository<LaboratoryDB,Integer>{
	
	LaboratoryDB findByLabNumber(int labNumber);

}
