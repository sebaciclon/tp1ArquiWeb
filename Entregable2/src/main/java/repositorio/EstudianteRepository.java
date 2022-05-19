package repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import interfaces.JPARepository;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import modelo.Carrera;
import modelo.Estudiante;

@Path("/estudiantes")
public class EstudianteRepository implements JPARepository<Estudiante> {
	
	private static EntityManager em = null;
		
	public EstudianteRepository(EntityManager em) {
		this.em = em;
	}
	
	public EstudianteRepository() {
		System.out.println("Pasé por acá");
	}

	@Override
	public void save(Estudiante e) {
		if(!em.contains(e)) {
			em.persist(e);		// insert
		}
		else {
			em.merge(e);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estudiante> getAll() {
		TypedQuery<Estudiante> tq = this.em.createNamedQuery(
				Estudiante.ORDENAR_POR_APELLIDO, Estudiante.class);
		return tq.getResultList();
	}
	
	
	public Estudiante getById(int id) {
		TypedQuery<Estudiante> tq = this.em.createNamedQuery(
				Estudiante.BUSCAR_POR_LU, Estudiante.class).setParameter("num_lu", id);
		return tq.getSingleResult();
	}
	
	public List<Estudiante> getByGenre(String genero){
		TypedQuery<Estudiante> tq = this.em.createNamedQuery(
				Estudiante.BUSCAR_POR_GENERO, Estudiante.class).setParameter("genero", genero);
		return tq.getResultList();
	}
	
	public List<Estudiante> getEstudiantesByCarreraByCiudad(int carrera, String ciudad){
		TypedQuery<Estudiante> tq = this.em.createNamedQuery(
				Estudiante.BUSCAR_POR_CARRERA_Y_CIUDAD, Estudiante.class).setParameter("carrera", carrera).setParameter("ciudad", ciudad);
		return tq.getResultList();
	}

}
