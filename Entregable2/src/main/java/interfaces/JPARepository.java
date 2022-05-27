package main.java.interfaces;



public interface JPARepository<T> {
	
	public void save(T t);
	
}
