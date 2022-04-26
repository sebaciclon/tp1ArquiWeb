package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import DAOFactory.ConexionMySQL;
import modelo.Factura;

public class DAOFactura implements DAO<Factura> {
	
	private Connection conn;
	

	@Override
	public void cargar(CSVParser datos) throws SQLException {
		conn = ConexionMySQL.conectar();
		String insert = "INSERT INTO factura (idFactura, idCliente) VALUES (?, ?)"; 
		
		for(CSVRecord fila: datos) {
			
			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setInt(1, Integer.parseInt(fila.get("idFactura")));
			ps.setInt(2, Integer.parseInt(fila.get("idCliente")));
			ps.executeUpdate();
			conn.commit();
			ps.close();
		}
		conn.close();
	}

	@Override
	public void crearTabla() throws SQLException {
		conn = ConexionMySQL.conectar();
		
		String factura = "CREATE TABLE factura(" +
				"idFactura INT," +
				"idCliente INT," +
				"PRIMARY KEY(idFactura)," +
				"FOREIGN KEY(idCliente) " +
				"references cliente(idCliente))";
		conn.prepareStatement(factura).execute();
		conn.commit();
		conn.close();	
	}
	

}
