package DAOFactory;

import java.sql.SQLException;

import DAO.DAOCliente;
import DAO.DAOFactura;
import DAO.DAOFacturaProducto;
import DAO.DAOProducto;

public abstract class AbstractFactory {

	public abstract DAOCliente getDAOCliente() throws SQLException;
	public abstract DAOFactura getDAOFactura() throws SQLException;
	public abstract DAOFacturaProducto getDAOFacturaProducto() throws SQLException;
	public abstract DAOProducto getDAOProducto() throws SQLException;
	
}
