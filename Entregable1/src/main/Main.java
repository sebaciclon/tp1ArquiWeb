package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;


import DAO.DAOCliente;
import DAO.DAOFactura;
import DAO.DAOFacturaProducto;
import DAO.DAOProducto;
import DAOFactory.AbstractFactory;
import DAOFactory.ConexionMySQL;

public class Main {
	
	private static DAOCliente daoCliente;
	private static DAOFactura daoFactura ;
	private static DAOFacturaProducto daoFacturaProducto;
	private static DAOProducto daoProducto;
	
	private static AbstractFactory fabricaMySQL = new ConexionMySQL();
	
		
	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException  {
		
		//AbstractFactory mysqlFactory = AbstractFactory.getDAOFactory(AbstractFactory.MYSQL_DB);
		daoCliente = fabricaMySQL.getDAOCliente();
		daoFactura = fabricaMySQL.getDAOFactura();
		daoFacturaProducto = fabricaMySQL.getDAOFacturaProducto();
		daoProducto = fabricaMySQL.getDAOProducto();
		
		daoCliente.crearTabla();
		daoFactura.crearTabla();
		daoProducto.crearTabla();
		daoFacturaProducto.crearTabla();
		
		
		@SuppressWarnings("deprecation")
		CSVParser clientes = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/csv/clientes.csv"));
		
		@SuppressWarnings("deprecation")
		CSVParser facturas = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/csv/facturas.csv"));
		
		@SuppressWarnings("deprecation")
		CSVParser facturasProductos = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/csv/facturas-productos.csv"));
		
		@SuppressWarnings("deprecation")
		CSVParser productos = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/csv/productos.csv"));
		
		daoCliente.cargar(clientes);
		daoFactura.cargar(facturas);
		daoProducto.cargar(productos);
		daoFacturaProducto.cargar(facturasProductos);
		
		
		System.out.println("Lista de clientes que mas facturan: ");
		//System.out.println(daoCliente.clientesMasFacturo());
		
		System.out.println("Producto que mas recaudò: ");
		System.out.println(daoProducto.productoMasRecaudo());
		
		
		
	}	
		
}
