package repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import interfaces.JPARepository;
import modelo.Carrera;
import modelo.DTOEstudianteCarrera;
import modelo.Estudiante;

public class CarreraRepository implements JPARepository<Carrera>{
	
	private static EntityManager em = null;
	
	public CarreraRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public void save(Carrera c) {
		if(!em.contains(c)) {
		//if(em.find(Carrera.class, c.getIdCarrera()) == null) {
			em.persist(c);		// insert
		}
		else {
			em.merge(c);
			//em.refresh(c);		// update
		}
		
	}

	@Override
	public List<Carrera> getAll() {
		TypedQuery<Carrera> tp = this.em.createNamedQuery(
				Carrera.BUSCAR_TODAS, Carrera.class);
		return tp.getResultList();
	}

	@Override
	public Carrera getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Carrera> getCarrerasConInscriptos() {
		TypedQuery<Carrera> tp = this.em.createNamedQuery(
				Carrera.BUSCAR_CON_INSCRIPTOS, Carrera.class);
		return tp.getResultList();
	}

	public List<DTOEstudianteCarrera> getReporteCarreras() {
		
		TypedQuery<Integer> tdq1 = em.createNamedQuery(Carrera.BUSCAR_FECHAS_INGRESO, Integer.class);
		TypedQuery<Integer> tdq2 = em.createNamedQuery(Carrera.BUSCAR_FECHAS_EGRESO, Integer.class);
		
		List<Integer> fechasIngreso = tdq1.getResultList();
		List<Integer> fechasEgreso = tdq2.getResultList();
		
		List<Carrera> carreras = this.getAll();
	
		ArrayList<DTOEstudianteCarrera> retorno = new ArrayList<DTOEstudianteCarrera>();
		for (Carrera c : carreras){						
			HashMap<Integer, List<Estudiante>> inscriptos = new HashMap<Integer, List<Estudiante>>();
			HashMap<Integer, List<Estudiante>> egresados = new HashMap<Integer, List<Estudiante>>();
            
			for (Integer fi : fechasIngreso) {
				TypedQuery<Estudiante> tq1 = 
						em.createNamedQuery(
								Carrera.BUSCAR_INSCRIPTOS_DE_CARRERA_POR_FECHA, Estudiante.class)
						.setParameter("carreraId", c.getIdCarrera()).setParameter("fecha", fi);
				List<Estudiante> estudianesInscriptos = tq1.getResultList();
				if(!estudianesInscriptos.isEmpty()) {	
					inscriptos.put(fi, estudianesInscriptos);
				}
			}
			     
			for(Integer fe : fechasEgreso){
				TypedQuery<Estudiante> tq2 = 
						em.createNamedQuery(
								Carrera.BUSCAR_EGRESADOS_DE_CARRERA_POR_FECHA, Estudiante.class)
						.setParameter("carreraId", c.getIdCarrera()).setParameter("fecha", fe);
				List<Estudiante> estudianesEgresados = tq2.getResultList();
				if(!estudianesEgresados.isEmpty()) {	
					egresados.put(fe, estudianesEgresados);
				}
			}
			
			retorno.add(new DTOEstudianteCarrera(c, inscriptos, egresados));
		}
		
		return retorno;
	}

}
