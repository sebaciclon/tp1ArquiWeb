package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Estudiante {
	
	@Id		// Decimos que esta variable es el id de la entidad
	private int LU;
	
	@Column(nullable=false)		// Decimos que esta variable es una columna de la entidad y no puede ser null
	private String nombres;
	
	@Column(nullable=false)	
	private String apellidos;
	
	@Column(nullable=false)	
	private int edad;
	
	@Column(nullable=false)	
	private String genero;
	
	@Column(nullable=false)	
	private String dni;
	
	@Column(nullable=false)	
	private String ciudad;
	
	// ver esta relacion si esta bien asi
	@OneToMany(mappedBy = "estudiante")	// Un estudiante puede tener muchas inscripciones
	private List<Inscripcion> carreras;	// Para manejar el mapeo many estamos obligados a usar algun tipo 
											// de coleccion, como una lista por ejemplo

	public Estudiante(int lU, String nombres, String apellidos, int edad, String genero, String dni, String ciudad) {
		super();
		LU = lU;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.edad = edad;
		this.genero = genero;
		this.dni = dni;
		this.ciudad = ciudad;
		this.carreras = null;
	}
	
	public Estudiante() {
		super();
	}

	public int getLU() {
		return LU;
	}

	public void setLU(int lU) {
		LU = lU;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	 public List<Inscripcion> getCarreras() {
		return carreras;
	}

	@Override
	public String toString() {
		return "Estudiante [LU=" + LU + ", nombres=" + nombres + ", apellidos=" + apellidos + ", edad=" + edad
				+ ", genero=" + genero + ", dni=" + dni + ", ciudad=" + ciudad + ", carreras=" + carreras + "]";
	} 
	 
	 
}
