package main.java.main;

/**
 * Los ejercicios 2 y 3 se resolvieron mediante el uso de createNamedQuery
 * 
 * Paquetes:
 * interfaces: contiene la interfaz que implementan los repositorios Carrera, Estudiante e Inscripcion
 * main: contiene el codigo en el que se instancian las clases
 * modelo: contiene las clases java mapeadas a sql   
 *  Repositorio: Contiene las clases del patr�n repository
 *  
 */

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Date;  
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import main.java.modelo.Carrera;
import main.java.modelo.DTOInscriptos;
import main.java.modelo.Estudiante;
import main.java.repositorio.CarreraRepository;
import main.java.repositorio.EstudianteRepository;
import main.java.repositorio.InscripcionRepository;
import main.java.modelo.Inscripcion;

public class Main {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entregable2");
		EntityManager em = emf.createEntityManager();
		
		EstudianteRepository er = new EstudianteRepository(em);
		CarreraRepository cr = new CarreraRepository(em);
		InscripcionRepository ir = new InscripcionRepository(em);
		
		// Se crean y se cargan los estudiantes a la base de datos.
		em.getTransaction().begin();
		Estudiante e = new Estudiante(10, "Sebastian", "Esains", 41, "M", "27.830.954", "Rauch");
		Estudiante e1 = new Estudiante(20, "Leonardo", "Esains", 51, "M", "28.830.954", "Rauch");
		Estudiante e2 = new Estudiante(30, "Loli", "Pe�a", 61, "F", "29.830.954", "Rauch");
		Estudiante e3 = new Estudiante(40, "Judit", "Meaca", 71, "F", "30.830.954", "Rauch");
		Estudiante e4 = new Estudiante(50, "Mauricio", "Oruezabal", 81, "M", "31.830.954", "Tandil");
		Estudiante e5 = new Estudiante(11, "Sebastian", "Lopez", 41, "M", "27.840.954", "Tandil");
		Estudiante e6 = new Estudiante(21, "Leonardo", "Juan", 51, "M", "28.450.954", "Rauch");
		Estudiante e7 = new Estudiante(31, "Loli", "Ardito", 61, "F", "39.830.954", "Tandil");
		Estudiante e8 = new Estudiante(41, "Judit", "Meacaaaa", 71, "F", "30.830.954", "Rauch");
		Estudiante e9 = new Estudiante(51, "Mauricio", "Gonzalez", 81, "M", "31.830.954", "Tandil");
		er.save(e);
		er.save(e1);
		er.save(e2);
		er.save(e3);
		er.save(e4);
		er.save(e5);
		er.save(e6);
		er.save(e7);
		er.save(e8);
		er.save(e9);
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
		
		
		// 2) EJERCICIO 2: Implementar consultas para:
		// a) dar de alta un estudiante
		
		em.getTransaction().begin();
		Estudiante Guillermo = new Estudiante(150, "Guillermo", "Perez", 45, "M", "39.830.954", "Tandil");
		er.save(Guillermo);
		em.getTransaction().commit();
		
		// b) matricular un estudiante en una carrera
		
		em.getTransaction().begin();
		Date date = new Date("2015/08/09");
		Date date1 = new Date("2000/08/09");
		Date date2 = new Date("2004/08/09");
		
		Inscripcion i = new Inscripcion(e2, c2,  new Timestamp(date1.getTime()),  new Timestamp(date.getTime()));
		Inscripcion i1 = new Inscripcion(e4, c2, new Timestamp(date1.getTime()), new Timestamp(date.getTime()));
		Inscripcion i2 = new Inscripcion(e4, c1, new Timestamp(date.getTime()), null);
		Inscripcion i3 = new Inscripcion(e, c3, new Timestamp(date2.getTime()), new Timestamp(date.getTime()));
		ir.save(i);
		ir.save(i1);
		ir.save(i2);
		ir.save(i3);
		em.getTransaction().commit();
		
		// c) recuperar todos los estudiantes, y especificar alg�n criterio de ordenamiento simple.
		// El criterio de ordenamiento es por apellido
		
		em.getTransaction().begin();
		System.out.println("Estudiantes ordenados de menor a mayor por apellido");
		System.out.println(er.getAll());
		System.out.println(" ");
		em.getTransaction().commit();
		
		// d) recuperar un estudiante, en base a su n�mero de libreta universitaria
		
		em.getTransaction().begin();
		System.out.println("Estudiante cuyo numero de libreta es 40");
		System.out.println(er.getByLU(40));
		System.out.println(" ");
		em.getTransaction().commit();
		
		// e) recuperar todos los estudiantes, en base a su g�nero.
		
		em.getTransaction().begin();
		System.out.println("Estudiantes cuyo genero es masculino");
		System.out.println(er.getByGenre("M"));
		System.out.println(" ");
		em.getTransaction().commit();
		
		// f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
		em.getTransaction().begin();
		System.out.println("Carreras con estudiantes inscriptos ordenadas por cantidad de inscriptos");
		System.out.println(cr.getCarrerasConInscriptos());
		System.out.println(" ");
		em.getTransaction().commit();
		
		// g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
		em.getTransaction().begin();
		System.out.println("Estudiantes de la carrera Base de datos que viven en Rauch");
		System.out.println(er.getEstudiantesByCarreraByCiudad(3, "Rauch"));
		System.out.println(" ");
		em.getTransaction().commit();
		
		// 3) Generar un reporte de las carreras, que para cada carrera incluya informaci�n de los
		// inscriptos y egresados por a�o. Se deben ordenar las carreras alfab�ticamente, y presentar
		// los a�os de manera cronol�gica.
		
		Iterator<DTOInscriptos> carreras = cr.getReporteCarreras().iterator();
		System.out.println("Carreras ordenadas alfabeticamente con inscriptos y egresados, ordenadas cronologicamente");
		while(carreras.hasNext())
			System.out.println(carreras.next());
		
		emf.close();
		em.close();
	}
	
	

}
