package main.java.recurso;

import java.util.List;

import jakarta.persistence.EntityManager;

import main.java.context.ContextListener;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import main.java.modelo.Estudiante;
import main.java.repositorio.EstudianteRepository;

@Path("/estudiantes")
public class EstudianteRecurso {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		EntityManager em = ContextListener.createEntityManager();
		List<Estudiante> e = this.getRepository(em).getAll();
		em.close();
		return this.getResponse(Status.OK, e);
	}

	private EstudianteRepository getRepository(EntityManager em) {
		return new EstudianteRepository(em);
	}
	
	private Response getResponse(Status status) {
		return getResponse(status, null);
	}

	private Response getResponse(Status status, Object o) {
		if(o != null) {			
			return Response.status(status.getStatusCode(), status.toString()).entity(o).build();
		} else {
			return Response.status(status.getStatusCode(), status.toString()).build();
		}
	}
}
