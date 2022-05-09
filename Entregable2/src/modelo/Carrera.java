package modelo;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@OneToMany(mappedBy = "carrera" /*, fetch = FetchType.LAZY*/)	// Una carrera puede tener muchas inscripciones - Por defecto es Lazy
	private ArrayList<Carrera> carreras;
	// private ArrayList<Inscripcion> inscripciones --> podría ser asì;

	public Carrera(int idCarrera, String nombre, ArrayList<Carrera> carreras) {
		super();
		this.idCarrera = idCarrera;
		this.nombre = nombre;
		this.carreras = carreras;
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

	public ArrayList<Carrera> getCarreras() {
		return carreras;
	}

/* 	public ArrayList<Inscripcion> getCarreras() {
		return carreras;
	} */


	/* public void setCarreras(ArrayList<Carrera> carreras) {
		this.carreras = carreras;
	} */ //Innecesario

	public int getIdCarrera() {
		return idCarrera;
	}
	
	

}
