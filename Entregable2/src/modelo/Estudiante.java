package modelo;

import java.util.ArrayList;

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
	
	// domicilio no va!! ahi va el macheo con inscripto
	@OneToMany(mappedBy = "domicilio", fetch = FetchType.LAZY)	// Un estudiante puede tener muchas carreras
	private ArrayList<Carrera> carreras;	// Para manejar el mapeo many estamos obligados a usar algun tipo 
											// de coleccion, como una lista por ejemplo

}
