package main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import interfaces.JPARepository;
import modelo.Estudiante;
import repositorio.CarreraRepository;
import repositorio.EstudianteRepository;
import repositorio.InscripcionRepository;
import repositorio.JPARepositoryFactory;

public class Main {

	public static void main(String[] args) {
		
		/*
		JPARepositoryFactory factory = JPARepositoryFactory.getInstance();
		EstudianteRepository e = (EstudianteRepository) factory.createRepository("Estudiante");
		CarreraRepository c = (CarreraRepository) factory.createRepository("Carrera");
		InscripcionRepository i = (InscripcionRepository) factory.createRepository("Inscripcion");*/
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entregable2");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Estudiante e = new Estudiante(10, "Sebastian", "Esains", 41, "M", "27.830.954", "Rauch");
		
		em.persist(e);
		em.getTransaction().commit();
		em.close();
		emf.close();
		
		//cargarEstudiantes(e);

	}
	
	public static void cargarEstudiantes(EstudianteRepository er) {
		
		Estudiante e = new Estudiante(10, "Sebastian", "Esains", 41, "M", "27.830.954", "Rauch");
		Estudiante e1 = new Estudiante(20, "Leonardo", "Esains", 51, "M", "28.830.954", "Rauch");
		Estudiante e2 = new Estudiante(30, "Loli", "Peña", 61, "F", "29.830.954", "Rauch");
		Estudiante e3 = new Estudiante(40, "Judit", "Meaca", 71, "F", "30.830.954", "Rauch");
		Estudiante e4 = new Estudiante(50, "Mauricio", "Oruezabal", 81, "M", "31.830.954", "Rauch");
		
		er.save(e);
		er.save(e1);
		er.save(e2);
		er.save(e3);
		er.save(e4);
	}

}
