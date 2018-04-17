package hello.dao.dbModel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("teacher")
public class TeacherDB extends UserDB {
	
	public TeacherDB() {}

}
