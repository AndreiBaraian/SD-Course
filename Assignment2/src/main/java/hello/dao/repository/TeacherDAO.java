package hello.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.dao.dbModel.TeacherDB;

public interface TeacherDAO extends JpaRepository<TeacherDB,Integer> {

}
