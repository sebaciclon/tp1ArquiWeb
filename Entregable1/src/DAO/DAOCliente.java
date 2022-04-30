package DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import DAOFactory.ConexionMySQL;
import modelo.Cliente;

// FILMINAS 3 PAGINA 7
// Estos serian el ConcreteProduct

public class DAOCliente implements DAO<Cliente> {
	
	private Connection conn;
	
	
	@Override
	public void cargar(CSVParser datos) throws SQLException {
		conn = ConexionMySQL.conectar();
		String insert = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?, ?, ?)"; 
		
		for(CSVRecord fila: datos) {
			
			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setInt(1, Integer.parseInt(fila.get("idCliente")));
			ps.setString(2, fila.get("nombre"));
			ps.setString(3, fila.get("email"));
			ps.executeUpdate();
			conn.commit();
			ps.close();
		}
		conn.close();
	}

	@Override
	public void crearTabla() throws SQLException {
		conn = ConexionMySQL.conectar();
		
		String cliente = "CREATE TABLE cliente(" +
				"idCliente INT," +
				"nombre VARCHAR(500)," +
				"email VARCHAR(150)," +
				"PRIMARY KEY (idCliente))";
		conn.prepareStatement(cliente).execute();
		conn.commit();
		conn.close();
	}
	
	public ArrayList<Cliente> clientesMasFacturo() throws SQLException {
		conn = ConexionMySQL.conectar();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		String select = "SELECT c.*, SUM(p.valor * fp.cantidad) as MejoresClientes " +
				"FROM cliente c JOIN factura f ON (c.idCliente = f.idCliente) " +
				"JOIN facturaproducto fp ON(f.idFactura = fp.idFactura) " +
				"JOIN producto p ON (p.idProducto = fp.idProducto) " +
				"WHERE c.idCliente = f.idCliente " + 
				"GROUP BY c.idCliente " + 
				"ORDER BY `MejoresClientes` DESC";
		PreparedStatement ps = conn.prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Cliente cliente = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3));
			clientes.add(cliente);	
		}
		conn.commit();
		conn.close();
		ps.close();
		rs.close();
		return clientes;
	}

	

}
