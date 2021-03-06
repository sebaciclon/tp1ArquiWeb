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
		if(!em.contains(i)) {
			em.persist(i);		// insert
		}
		else {
			em.merge(i);
		}
	}
}
