package hello.dao.repository;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import hello.dao.dbModel.LaboratoryDTO;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
@Transactional
public interface LaboratoryDAO extends JpaRepository<LaboratoryDTO,Integer>{
	
	LaboratoryDTO findByLabNumber(int labNumber);

}
