package hello.dao.dbModel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class AdminDB extends UserDB {

	public AdminDB() {}
	
}
