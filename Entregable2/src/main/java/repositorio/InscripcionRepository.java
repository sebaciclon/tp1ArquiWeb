package main.java.repositorio;

import jakarta.persistence.EntityManager;
import main.java.interfaces.JPARepository;
import main.java.modelo.Estudiante;
import main.java.modelo.Inscripcion;

public class InscripcionRepository implements JPARepository<Inscripcion>{
	
	private static EntityManager em = null;
	
	public InscripcionRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public void save(Inscripcion i) {
		em.getTransaction().begin();
		if(!em.contains(i)) {
			em.persist(i);		// insert
		}
		else {
			em.merge(i);
		}
		em.getTransaction().commit();
	}
}
