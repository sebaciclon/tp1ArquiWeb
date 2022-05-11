package repositorio;

import java.util.List;

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
		//if(em.find(Inscripcion.class, i.getCarrera()) == null && em.find(Inscripcion.class, i.getEstudiante()) == null) {
			em.persist(i);		// insert
		}
		else {
			em.merge(i);
			//em.refresh(i);		// update
		}
		
	}

	@Override
	public List<Inscripcion> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inscripcion getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
