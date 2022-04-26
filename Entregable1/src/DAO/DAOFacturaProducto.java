package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import DAOFactory.ConexionMySQL;
import modelo.FacturaProducto;

public class DAOFacturaProducto implements DAO<FacturaProducto>{
	
	private Connection conn;
	

	@Override
	public void cargar(CSVParser datos) throws SQLException {
		conn = ConexionMySQL.conectar();
		String insert = "INSERT INTO facturaProducto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)"; 
		
		for(CSVRecord fila: datos) {
			
			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setInt(1, Integer.parseInt(fila.get("idFactura")));
			ps.setInt(2, Integer.parseInt(fila.get("idProducto")));
			ps.setInt(3, Integer.parseInt(fila.get("cantidad")));
			ps.executeUpdate();
			conn.commit();
			ps.close();
		}
		conn.close();
		
	}

	@Override
	public void crearTabla() throws SQLException {
		conn = ConexionMySQL.conectar();
		
		String facturaProducto = "CREATE TABLE facturaproducto(" +
				"idFactura INT," +
				"idProducto INT," +
				"cantidad INT," +
				"PRIMARY KEY (idFactura, idProducto)," +
				"FOREIGN KEY(idFactura)" +
				"references factura(idFactura)," +
				"FOREIGN KEY(idProducto)" +
				"references producto(idProducto))";
				/*"FOREIGN KEY(idFactura) references factura(idFactura)," +
				"FOREIGN KEY(idProducto) references producto(idProducto))";*/
		conn.prepareStatement(facturaProducto).execute();
		conn.commit();
		conn.close();
		
	}

}
