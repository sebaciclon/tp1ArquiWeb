package repositorio;

import javax.persistence.EntityManager;

import interfaces.JPARepository;
import modelo.Estudiante;

public class EstudianteRepository implements JPARepository<Estudiante> {
	
	private static EntityManager em = null;
	
	public EstudianteRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public void save(Estudiante e) {
		if(em.find(Estudiante.class, e.getLU()) == null) {
			em.persist(e);		// insert
		}
		else {
			// ver esto si va un refresh
			em.flush();		// update
		}
	}

	
	
	

	

	
	
	// implementa dao

}
