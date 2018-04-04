package repository;

import java.util.List;

public interface IAbstractRepo<T> {
	
	public T findById(int id);
	
	public int insert(T t);
	
	public List<T> findAll();
	
	public T findByField(String field, String value);
	
	public void update(T t);
	
	public void delete(T t);

}
