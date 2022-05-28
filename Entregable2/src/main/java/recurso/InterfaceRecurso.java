package main.java.recurso;

import jakarta.ws.rs.core.Response;

public interface InterfaceRecurso<T> {
	
	public Response getResponse(T t);
	
	public T getRepository(T t);

	public T getResponse(T t, Object o);

}
