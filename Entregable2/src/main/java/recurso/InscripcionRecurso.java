package main.java.recurso;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import main.java.context.ContextListener;
import main.java.modelo.Carrera;
import main.java.modelo.Estudiante;
import main.java.modelo.Inscripcion;
import main.java.repositorio.CarreraRepository;
import main.java.repositorio.EstudianteRepository;
import main.java.repositorio.InscripcionRepository;

@Path("/inscripcion")
public class InscripcionRecurso {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{LU}/{idCarrera}")
	public Response inscribirEstudiante(@PathParam("LU")int LU, @PathParam("idCarrera")int idCarrera) {
		EntityManager em = ContextListener.createEntityManager();
		Estudiante e = this.getEstudianteRepository(em).getByLU(LU);
		Carrera c = this.getCarreraRepository(em).getById(idCarrera);
		Timestamp  t = new Timestamp(new Date().getTime()); 
		
		if(c != null && e != null) {
			Inscripcion i = new Inscripcion(e, c, t);
			this.getInscripcionRepository(em).save(i);			
			em.close();
			return this.getResponse(Status.OK);
		}
		else {
			return this.getResponse(Status.CONFLICT);			
		}
	}
	
	private EstudianteRepository getEstudianteRepository(EntityManager em) {
		return new EstudianteRepository(em);
	}
	private CarreraRepository getCarreraRepository(EntityManager em) {
		return new CarreraRepository(em);
	}
	
	private InscripcionRepository getInscripcionRepository(EntityManager em) {
		return new InscripcionRepository(em);
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
}
