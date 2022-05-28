package main.java.recurso;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import main.java.context.ContextListener;
import main.java.modelo.Carrera;
import main.java.repositorio.CarreraRepository;

@Path("/carreras")
public class CarreraRecurso {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		EntityManager em = ContextListener.createEntityManager();
		List<Carrera> c = this.getRepository(em).getAll();
		em.close();
		return this.getResponse(Status.OK, c);
	}
	
	private Response getResponse(Status status) {
		return getResponse(status, null);
	}
	
	private CarreraRepository getRepository(EntityManager em) {
		return new CarreraRepository(em);
	}

	private Response getResponse(Status status, Object o) {
		if(o != null) {			
			return Response.status(status.getStatusCode(), status.toString()).entity(o).build();
		} else {
			return Response.status(status.getStatusCode(), status.toString()).build();
			//return Response.status(Response.Status.NOT_FOUND)
		    //         .entity("El recurso con id no fue encontrado").type(MediaType.TEXT_PLAIN).build();
		}
	}
}
