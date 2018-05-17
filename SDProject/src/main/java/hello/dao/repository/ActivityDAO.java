package hello.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.dao.dbModel.ActivityDB;

public interface ActivityDAO extends JpaRepository<ActivityDB,Integer>{

}
