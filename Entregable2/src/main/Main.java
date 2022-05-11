package main;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Date;  

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import interfaces.JPARepository;
import modelo.Carrera;
import modelo.DTOEstudianteCarrera;
import modelo.Estudiante;
import modelo.Inscripcion;
import repositorio.CarreraRepository;
import repositorio.EstudianteRepository;
import repositorio.InscripcionRepository;
import repositorio.JPARepositoryFactory;

public class Main {

	public static void main(String[] args) {
		
		/*JPARepositoryFactory factory = JPARepositoryFactory.getInstance();
		
		EstudianteRepository e = (EstudianteRepository) factory.createRepository("Estudiante");
		CarreraRepository c = (CarreraRepository) factory.createRepository("Carrera");
		InscripcionRepository i = (InscripcionRepository) factory.createRepository("Inscripcion");*/
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entregable2");
		EntityManager em = emf.createEntityManager();
		
		EstudianteRepository er = new EstudianteRepository(em);
		CarreraRepository cr = new CarreraRepository(em);
		InscripcionRepository ir = new InscripcionRepository(em);
		
		// Se crean y se cargan los estudiantes a la base de datos.
		em.getTransaction().begin();
		Estudiante e = new Estudiante(10, "Sebastian", "Esains", 41, "M", "27.830.954", "Rauch", null);
		Estudiante e1 = new Estudiante(20, "Leonardo", "Esains", 51, "M", "28.830.954", "Rauch", null);
		Estudiante e2 = new Estudiante(30, "Loli", "Peña", 61, "F", "29.830.954", "Rauch", null);
		Estudiante e3 = new Estudiante(40, "Judit", "Meaca", 71, "F", "30.830.954", "Rauch", null);
		Estudiante e4 = new Estudiante(50, "Mauricio", "Oruezabal", 81, "M", "31.830.954", "Tandil", null);
		er.save(e);
		er.save(e1);
		er.save(e2);
		er.save(e3);
		er.save(e4);
		em.getTransaction().commit();
		
		// Se crean y se cargan las carreras a la base de datos.
		em.getTransaction().begin();
		Carrera c = new Carrera(1, "Arquitectura Web");
		Carrera c1 = new Carrera(2, "Programacion 3");
		Carrera c2 = new Carrera(3, "Base de Datos");
		Carrera c3 = new Carrera(4, "Tecnicas");
		Carrera c4 = new Carrera(5, "Web 2");
		cr.save(c);
		cr.save(c1);
		cr.save(c2);
		cr.save(c3);
		cr.save(c4);
		em.getTransaction().commit();
		
		
		// 2) Implementar consultas para:
		// a) dar de alta un estudiante
		em.getTransaction().begin();
		Estudiante Guillermo = new Estudiante(150, "Guillermo", "Perez", 45, "M", "39.830.954", "Tandil");
		er.save(Guillermo);
		em.getTransaction().commit();
		
		// b) matricular un estudiante en una carrera
		em.getTransaction().begin();
		Date date = new Date("2015/08/09");
		Date date2 = new Date("2000/08/09");
		
		Inscripcion i = new Inscripcion(e2, c2,  new Timestamp(date.getTime()),  new Timestamp(date2.getTime()));
		Inscripcion i2 = new Inscripcion(e4, c2, new Timestamp(date.getTime()), new Timestamp(date2.getTime()));
		Inscripcion i3 = new Inscripcion(e4, c1, new Timestamp(date.getTime()), null);
		ir.save(i);
		ir.save(i2);
		ir.save(i3);
		em.getTransaction().commit();
		
		// c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
		em.getTransaction().begin();
		System.out.println(er.getAll());
		em.getTransaction().commit();
		
		// d) recuperar un estudiante, en base a su número de libreta universitaria
		em.getTransaction().begin();
		System.out.println(er.getById(10));
		em.getTransaction().commit();
		
		// e) recuperar todos los estudiantes, en base a su género.
		em.getTransaction().begin();
		System.out.println(er.getByGenre("M"));
		em.getTransaction().commit();
		
		// f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
		em.getTransaction().begin();
		System.out.println(cr.getCarrerasConInscriptos());
		em.getTransaction().commit();
		
		// g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
		em.getTransaction().begin();
		System.out.println(er.getEstudiantesByCarreraByCiudad(3, "Rauch"));
		em.getTransaction().commit();
		
		// 3) Generar un reporte de las carreras, que para cada carrera incluya información de los
		// inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar
		// los años de manera cronológica.
		
		Iterator<DTOEstudianteCarrera> carreras = cr.getReporteCarreras().iterator();
		while(carreras.hasNext())
			System.out.println(carreras.next());

	}
	
	

}
