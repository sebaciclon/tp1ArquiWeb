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
	//private int LU;
	@ManyToOne(fetch = FetchType.LAZY)		// Muchas inscripciones pertenecen a un alumno
	@JoinColumn
	private Estudiante estudiante;
	
	@Id
	//private int idCarrera;
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
	
	/*@ManyToOne(fetch = FetchType.LAZY)		// Muchas inscripciones pertenecen a un alumno
	@JoinColumn
	private Estudiante estudiante;
	
	@ManyToOne(fetch = FetchType.LAZY)			// Muchas inscripciones pertenecen a una carrera
	@JoinColumn
	private Carrera carrera;*/

	/*public Inscripcion(int lU, int idCarrera, int antiguedad, boolean graduado, Estudiante estudiante,
			Carrera carrera) {
		super();
		LU = lU;
		this.idCarrera = idCarrera;
		this.antiguedad = antiguedad;
		this.graduado = graduado;
		this.estudiante = estudiante;
		this.carrera = carrera;
	}*/
	
	

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

	/*public int getLU() {
		return LU;
	}

	public int getIdCarrera() {
		return idCarrera;
	}

	@Override
	public String toString() {
		return "Inscripcion [LU=" + LU + ", idCarrera=" + idCarrera + ", antiguedad=" + antiguedad + ", graduado="
				+ graduado + ", estudiante=" + estudiante + ", carrera=" + carrera + "]";
	}*/
	
	

}
