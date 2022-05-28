package main.java.repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import main.java.interfaces.JPARepository;
import main.java.modelo.*;

public class CarreraRepository implements JPARepository<Carrera>{
	
	private static EntityManager em = null;
	
	public CarreraRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public void save(Carrera c) {
		if(!em.contains(c)) {
			em.persist(c);		// insert
		}
		else {
			em.merge(c);
		}
	}
	
	public List<Carrera> getAll() {
		TypedQuery<Carrera> tp = this.em.createNamedQuery(
				Carrera.BUSCAR_TODAS, Carrera.class);
		return tp.getResultList();
	}
	
	public Carrera getById(int id) {
		TypedQuery<Carrera> tp = this.em.createNamedQuery(
				Carrera.BUSCAR_POR_ID, Carrera.class).setParameter("idCarrera", id);
		return tp.getSingleResult();
	}

	public List<Carrera> getCarrerasConInscriptos() {
		TypedQuery<Carrera> tp = this.em.createNamedQuery(
				Carrera.BUSCAR_CON_INSCRIPTOS, Carrera.class);
		return tp.getResultList();
	}

	public List<DTOInscriptos> getReporteCarreras() {
		
		TypedQuery<Integer> ingreso = em.createNamedQuery(Carrera.BUSCAR_FECHAS_INGRESO, Integer.class);
		TypedQuery<Integer> egreso = em.createNamedQuery(Carrera.BUSCAR_FECHAS_EGRESO, Integer.class);
		
		List<Integer> fechasIngreso = ingreso.getResultList();
		List<Integer> fechasEgreso = egreso.getResultList();
		
		List<Carrera> carreras = this.getAll();
	
		ArrayList<DTOInscriptos> salida = new ArrayList<DTOInscriptos>();
		for (Carrera c : carreras){						
			HashMap<Integer, List<Estudiante>> inscriptos = new HashMap<Integer, List<Estudiante>>();
			HashMap<Integer, List<Estudiante>> egresados = new HashMap<Integer, List<Estudiante>>();
            
			for (Integer fi : fechasIngreso) {
				TypedQuery<Estudiante> tq1 = em.createNamedQuery(Carrera.BUSCAR_INSCRIPTOS_DE_CARRERA_POR_FECHA, Estudiante.class)
						.setParameter("carreraId", c.getIdCarrera()).setParameter("fecha", fi);
				List<Estudiante> estudianesInscriptos = tq1.getResultList();
				if(!estudianesInscriptos.isEmpty()) {	
					inscriptos.put(fi, estudianesInscriptos);
				}
			}
			     
			for(Integer fe : fechasEgreso){
				TypedQuery<Estudiante> tq2 = em.createNamedQuery(Carrera.BUSCAR_EGRESADOS_DE_CARRERA_POR_FECHA, Estudiante.class)
						.setParameter("carreraId", c.getIdCarrera()).setParameter("fecha", fe);
				List<Estudiante> estudianesEgresados = tq2.getResultList();
				if(!estudianesEgresados.isEmpty()) {	
					egresados.put(fe, estudianesEgresados);
				}
			}
			
			salida.add(new DTOInscriptos(c, inscriptos, egresados));
		}
		
		return salida;
	}

}
