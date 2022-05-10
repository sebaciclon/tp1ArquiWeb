package repositorio;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import interfaces.JPARepository;

public class JPARepositoryFactory {
	
	private static EntityManager em = Persistence.createEntityManagerFactory("Entregable2").createEntityManager();
	// Singleton
	private static JPARepositoryFactory uniqueInstance = null;
	
	// Singleton
	public static JPARepositoryFactory getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new JPARepositoryFactory(em);
		}
		return uniqueInstance;
	}
	
	public JPARepositoryFactory(EntityManager em) {
		this.em = em;
	}
	
	public JPARepository createRepository(String nombre) {
		if(nombre.equals("Carrera")) {
			return new CarreraRepository(em);
		}
		else {
			if(nombre.equals("Estudiante")) {
				return new EstudianteRepository(em);
			}
			else {
				if(nombre.equals("Inscripcion"))
					return new InscripcionRepository(em);
			}
		}
		return null;
	}

}
