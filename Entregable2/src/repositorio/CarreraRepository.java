package repositorio;

import javax.persistence.EntityManager;

import interfaces.JPARepository;
import modelo.Carrera;
import modelo.Estudiante;

public class CarreraRepository implements JPARepository<Carrera>{
	
	private static EntityManager em = null;
	
	public CarreraRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public void save(Carrera c) {
		if(em.find(Carrera.class, c.getIdCarrera()) == null) {
			em.persist(c);		// insert
		}
		else {
			// ver esto si va un refresh
			em.flush();		// update
		}
		
	}
	
	
	
	// implementa dao

}
