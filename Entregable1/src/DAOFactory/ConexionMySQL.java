package DAOFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import DAO.DAOCliente;
import DAO.DAOFactura;
import DAO.DAOFacturaProducto;
import DAO.DAOProducto;


public class ConexionMySQL extends AbstractFactory{

	private static Connection conn;
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URI = "jdbc:mysql://localhost:3306/entregable1arquiweb";	
	private static final String USER = "root";
	private static final String PASS = "";
	
	// FILMINAS 3 PAGINA 7 
	// Este seria el ConcreteCreator
	
	public static Connection conectar() {
		try {
			Class.forName(DRIVER).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e1) {
			e1.printStackTrace();
			System.exit(1);
		}
		
		try {
			conn = DriverManager.getConnection(URI, USER, PASS);
			conn.setAutoCommit(false);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public DAOCliente getDAOCliente() throws SQLException {
		return new DAOCliente();
	}

	@Override
	public DAOFactura getDAOFactura() throws SQLException {
		return new DAOFactura();
	}

	@Override
	public DAOFacturaProducto getDAOFacturaProducto() throws SQLException {
		return new DAOFacturaProducto();
	}

	@Override
	public DAOProducto getDAOProducto() throws SQLException {
		return new DAOProducto();
	}

	
	
}
