package repositorio;

import javax.persistence.EntityManager;
import interfaces.JPARepository;
import modelo.Inscripcion;

public class InscripcionRepository implements JPARepository<Inscripcion>{
	
	private static EntityManager em = null;
	
	public InscripcionRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public void save(Inscripcion i) {
		if(em.find(Inscripcion.class, i.getIdCarrera()) == null && em.find(Inscripcion.class, i.getLU()) == null) {
			em.persist(i);		// insert
		}
		else {
			// ver esto si va un refresh
			em.flush();		// update
		}
		
	}

}
