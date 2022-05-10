package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Inscripcion implements Serializable{
	
	@Id
	private int LU;
	
	@Id
	private int idCarrera;
	
	@Column(nullable=false)	
	private int antiguedad;
	
	@Column(nullable=false)	
	private boolean graduado;
	

	@ManyToOne(fetch = FetchType.LAZY)		// Muchas inscripciones pertenecen a un alumno
	@JoinColumn
	private Estudiante estudiante;
	
	@ManyToOne(fetch = FetchType.LAZY)			// Muchas inscripciones pertenecen a una carrera
	@JoinColumn
	private Carrera carrera;

	public Inscripcion(int lU, int idCarrera, int antiguedad, boolean graduado, Estudiante estudiante,
			Carrera carrera) {
		super();
		LU = lU;
		this.idCarrera = idCarrera;
		this.antiguedad = antiguedad;
		this.graduado = graduado;
		this.estudiante = estudiante;
		this.carrera = carrera;
	}

	public Inscripcion() {
		super();
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public boolean isGraduado() {
		return graduado;
	}

	public void setGraduado(boolean graduado) {
		this.graduado = graduado;
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

	public int getLU() {
		return LU;
	}

	public int getIdCarrera() {
		return idCarrera;
	}

	@Override
	public String toString() {
		return "Inscripcion [LU=" + LU + ", idCarrera=" + idCarrera + ", antiguedad=" + antiguedad + ", graduado="
				+ graduado + ", estudiante=" + estudiante + ", carrera=" + carrera + "]";
	}
	
	

}
