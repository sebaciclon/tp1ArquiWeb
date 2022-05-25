package main.java.modelo;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = Estudiante.ORDENAR_POR_APELLIDO, query = "SELECT e FROM Estudiante e ORDER BY e.apellidos"),
		@NamedQuery(name = Estudiante.BUSCAR_POR_LU, query = "SELECT e FROM Estudiante e WHERE e.LU = :num_lu"),
		@NamedQuery(name = Estudiante.BUSCAR_POR_GENERO, query = "SELECT e FROM Estudiante e WHERE e.genero = :genero"),
		@NamedQuery(name = Estudiante.BUSCAR_POR_CARRERA_Y_CIUDAD, query = "SELECT i.estudiante FROM Inscripcion i,  Estudiante e, Carrera c WHERE c.idCarrera = i.carrera.idCarrera AND e.LU = i.estudiante.LU AND c.idCarrera = :carrera AND i.estudiante.ciudad =: ciudad")
})
public class Estudiante {
	public static final String ORDENAR_POR_APELLIDO = "Estudiante.ordenarPorApellido";
	public static final String BUSCAR_POR_LU = "Estudiante.buscarPorLU";
	public static final String BUSCAR_POR_GENERO = "Estudiante.buscarPorGenero";
	public static final String BUSCAR_POR_CARRERA_Y_CIUDAD = "Estudiante.buscarPorCarreraYCiudad";
			
	@Id		
	private int LU;
	
	@Column(nullable=false)		
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
	
	@OneToMany(mappedBy = "estudiante")	
	private List<Inscripcion> carreras;	

	public Estudiante(int lU, String nombres, String apellidos, int edad, String genero, String dni, String ciudad,
			List<Inscripcion> carreras) {
		super();
		LU = lU;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.edad = edad;
		this.genero = genero;
		this.dni = dni;
		this.ciudad = ciudad;
		this.carreras = carreras;
	}
	
	public Estudiante(int lU, String nombres, String apellidos, int edad, String genero, String dni, String ciudad) {
		super();
		LU = lU;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.edad = edad;
		this.genero = genero;
		this.dni = dni;
		this.ciudad = ciudad;
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
				+ ", genero=" + genero + ", dni=" + dni + ", ciudad=" + ciudad + "]";
	} 
	 
	 
}
