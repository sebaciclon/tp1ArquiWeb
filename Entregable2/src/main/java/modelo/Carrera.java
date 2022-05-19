package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = Carrera.BUSCAR_TODAS, query = "SELECT c FROM Carrera c ORDER BY c.nombre"),
		@NamedQuery(name = Carrera.BUSCAR_CON_INSCRIPTOS, query = ""
				+ "SELECT  DISTINCT i.carrera FROM Inscripcion i,  Estudiante e, Carrera c WHERE c.idCarrera = i.carrera.idCarrera AND e.LU = i.estudiante.LU GROUP BY i.carrera.idCarrera ORDER BY COUNT(i.estudiante) DESC"),
		@NamedQuery(name = Carrera.BUSCAR_INSCRIPTOS_DE_CARRERA_POR_FECHA, query = "SELECT i.estudiante FROM Inscripcion i,  Estudiante e, Carrera c WHERE c.idCarrera = i.carrera.idCarrera AND e.LU = i.estudiante.LU AND c.idCarrera = :carreraId AND YEAR (i.fecha_ingreso) = : fecha"),
		@NamedQuery(name = Carrera.BUSCAR_EGRESADOS_DE_CARRERA_POR_FECHA, query = "SELECT i.estudiante FROM Inscripcion i,  Estudiante e, Carrera c WHERE c.idCarrera = i.carrera.idCarrera AND e.LU = i.estudiante.LU AND c.idCarrera = :carreraId AND YEAR (i.fecha_egreso) = : fecha"),
		@NamedQuery(name = Carrera.BUSCAR_FECHAS_INGRESO, query = "SELECT YEAR(i.fecha_ingreso) FROM Inscripcion i GROUP BY YEAR (i.fecha_ingreso) ORDER BY YEAR (i.fecha_ingreso)"),
		@NamedQuery(name = Carrera.BUSCAR_FECHAS_EGRESO, query = "SELECT YEAR(i.fecha_egreso) FROM Inscripcion i WHERE i.fecha_egreso IS NOT NULL GROUP BY YEAR(i.fecha_egreso) ORDER BY YEAR (i.fecha_egreso)")
})

public class Carrera {
	public static final String BUSCAR_TODAS = "Carrera.buscarTodas";
	public static final String BUSCAR_CON_INSCRIPTOS = "Carrera.buscarConInscriptos";
	public static final String BUSCAR_INSCRIPTOS_DE_CARRERA_POR_FECHA = "Carrera.buscarInscriptosDeCarreraPorFecha";
	public static final String BUSCAR_EGRESADOS_DE_CARRERA_POR_FECHA = "Carrera.buscarEgresadosDeCarreraPorFecha";
	public static final String BUSCAR_FECHAS_INGRESO = "Carrera.buscarFechasIngreso";
	public static final String BUSCAR_FECHAS_EGRESO = "Carrera.buscarFechasEgreso";
	
	@Id
	private int idCarrera;
	
	@Column(nullable=false)	
	private String nombre;
	
	@OneToMany(mappedBy = "carrera")	
	private List<Inscripcion> estudiantes;
	
	public Carrera(int idCarrera, String nombre) {
		super();
		this.idCarrera = idCarrera;
		this.nombre = nombre;
		estudiantes = new ArrayList<Inscripcion>();
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

	public List<Inscripcion> getInscripciones() {
		return estudiantes;
	}

	public int getIdCarrera() {
		return idCarrera;
	}

	@Override
	public String toString() {
		return "Carrera [idCarrera=" + idCarrera + ", nombre=" + nombre + "]";
	}
}
