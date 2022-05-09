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
	
	// domicilio no va!! ahi va el macheo con inscripto
	@OneToMany(mappedBy = "domicilio", fetch = FetchType.LAZY)
	private ArrayList<Estudiante> estudiantes;
	
	

}
