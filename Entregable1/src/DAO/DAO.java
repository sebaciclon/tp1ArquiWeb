package DAO;

import java.sql.SQLException;
import org.apache.commons.csv.CSVParser;

public interface DAO<T> {
	
	public void cargar(CSVParser datos) throws SQLException;
	public void crearTabla() throws SQLException;

}
