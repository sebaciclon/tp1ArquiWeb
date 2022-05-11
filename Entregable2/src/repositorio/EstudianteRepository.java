package repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import interfaces.JPARepository;
import modelo.Carrera;
import modelo.Estudiante;

public class EstudianteRepository implements JPARepository<Estudiante> {
	
	private static EntityManager em = null;
		
	public EstudianteRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public void save(Estudiante e) {
		if(!em.contains(e)) {
		//if(em.find(Estudiante.class, e.getLU()) == null) {
			em.persist(e);		// insert
		}
		else {
			em.merge(e);
			//em.refresh(e);		// update
		}
		
	}

	@Override
	public List<Estudiante> getAll() {
		TypedQuery<Estudiante> tq = this.em.createNamedQuery(
				Estudiante.BUSCAR_TODOS, Estudiante.class);
		return tq.getResultList();
	}
	
	public Estudiante getById(int id) {
		TypedQuery<Estudiante> tp = this.em.createNamedQuery(
				Estudiante.BUSCAR_POR_LU, Estudiante.class).setParameter("num_lu", id);
		return tp.getSingleResult();
	}
	
	public List<Estudiante> getByGenre(String genero){
		TypedQuery<Estudiante> tq = this.em.createNamedQuery(
				Estudiante.BUSCAR_POR_GENERO, Estudiante.class).setParameter("genero", genero);
		return tq.getResultList();
	}
	
	public List<Estudiante> getEstudiantesByCarreraByCiudad(int carrera, String ciudad){
		TypedQuery<Estudiante> tp = this.em.createNamedQuery(
				Estudiante.BUSCAR_POR_CARRERA_Y_CIUDAD, Estudiante.class).setParameter("carrera", carrera).setParameter("ciudad", ciudad);
		return tp.getResultList();
	}

}
