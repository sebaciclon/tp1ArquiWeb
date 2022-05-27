package main.java.recurso;

import java.util.List;
import jakarta.persistence.EntityManager;
import main.java.context.ContextListener;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response save(Estudiante e) {
		EntityManager em = ContextListener.createEntityManager();
		this.getRepository(em).save(e);
		em.close();
		return this.getResponse(Status.CREATED, e);
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") int id) {
		EntityManager em = ContextListener.createEntityManager();
		Estudiante e = this.getRepository(em).getById(id);
		
		em.close();
		return this.getResponse(Status.OK, e);
	}
	
	@GET
	@Path("/{String}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByGenre(@PathParam("genero") String genero) {
		EntityManager em = ContextListener.createEntityManager();
		List<Estudiante> e = this.getRepository(em).getByGenre(genero);
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
			//return Response.status(Response.Status.NOT_FOUND)
		    //         .entity("El recurso con id no fue encontrado").type(MediaType.TEXT_PLAIN).build();
		}
	}
	/*
	public class RecursoDuplicado extends WebApplicationException {
	     public RecursoDuplicado(int id) {
	         super(Response.status(Response.Status.CONFLICT)
	             .entity("El recurso con ID "+id+" ya existe").type(MediaType.TEXT_PLAIN).build());
	     }
	}
	
	public class RecursoNoExiste extends WebApplicationException {
	     public RecursoNoExiste(int id) {
	         super(Response.status(Response.Status.NOT_FOUND)
	             .entity("El recurso con id "+id+" no fue encontrado").type(MediaType.TEXT_PLAIN).build());
	     }
	}*/
}
