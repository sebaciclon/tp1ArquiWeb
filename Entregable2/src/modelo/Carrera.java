package modelo;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Carrera {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idCarrera;
	
	@Column(nullable=false)	
	private String nombre;
	
	// ver esta relacion si esta bien asi
	@OneToMany(mappedBy = "carrera")	// Una carrera puede tener muchas inscripciones - Por defecto es Lazy
	private ArrayList<Inscripcion> inscripciones;
	
	public Carrera(int idCarrera, String nombre, ArrayList<Inscripcion> inscripciones) {
		super();
		this.idCarrera = idCarrera;
		this.nombre = nombre;
		this.inscripciones = null;
	}

	public Carrera() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Inscripcion> getInscripciones() {
		return inscripciones;
	}

	public int getIdCarrera() {
		return idCarrera;
	}

	@Override
	public String toString() {
		return "Carrera [idCarrera=" + idCarrera + ", nombre=" + nombre + ", inscripciones=" + inscripciones + "]";
	}
	
	

}
