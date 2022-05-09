package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Inscripto {
	
	@Id
	private int LU;
	
	@Id
	private int idCarrera;
	
	@Column(nullable=false)	
	private int antiguedad;
	
	@Column(nullable=false)	
	private boolean graduado;

}
