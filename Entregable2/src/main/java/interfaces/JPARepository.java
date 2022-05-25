package main.java.interfaces;

import java.util.List;

public interface JPARepository<T> {
	
	public void save(T t);
	
}
