package hello.dao.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hello.dao.dbModel.SubmissionDB;

@Repository
@Transactional
public interface SubmissionDAO extends JpaRepository<SubmissionDB,Integer> {

}
