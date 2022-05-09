package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Inscripcion {
	
	@Id
	private int LU;
	
	@Id
	private int idCarrera;
	
	@Column(nullable=false)	
	private int antiguedad;
	
	@Column(nullable=false)	
	private boolean graduado;
	
	@ManyToOne		// Muchas inscripciones pertenecen a un alumno
	@JoinColumn
	private Estudiante estudiante;
	
	@ManyToOne		// Muchas inscripciones pertenecen a una carrera
	@JoinColumn
	private Carrera carrera;

}
