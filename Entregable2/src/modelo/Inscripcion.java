package modelo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Inscripcion implements Serializable{
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)		// Muchas inscripciones pertenecen a un estudiante
	@JoinColumn
	private Estudiante estudiante;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)			// Muchas inscripciones pertenecen a una carrera
	@JoinColumn
	private Carrera carrera;
	
	@Column
	private Timestamp fecha_ingreso;
	
	@Column(nullable = true)
	private Timestamp fecha_egreso;

	public Timestamp getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(Timestamp fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public Timestamp getFecha_egreso() {
		return fecha_egreso;
	}

	public void setFecha_egreso(Timestamp fecha_egreso) {
		this.fecha_egreso = fecha_egreso;
	}

	public Inscripcion() {
		super();
	}

	public Inscripcion(Estudiante estudiante, Carrera carrera, Timestamp ingreso, Timestamp egreso) {
		super();
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.fecha_ingreso = ingreso;
		this.fecha_egreso = egreso;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	@Override
	public String toString() {
		return "Inscripcion [estudiante=" + estudiante + ", carrera=" + carrera + ", fecha_ingreso=" + fecha_ingreso
				+ ", fecha_egreso=" + fecha_egreso + "]";
	}
}
