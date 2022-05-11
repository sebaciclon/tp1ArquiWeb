package interfaces;

import java.util.List;

public interface JPARepository<T> {
	
	public void save(T t);
	
	public List<T> getAll();
	public T getById(int id);
	
	// aca va el crud
}
