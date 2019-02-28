package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

/**
 * A factory for creating Dao objects.
 */
public class DaoFactory {

	private static final String PASSWORD = "password";
	private static final String USERNAME = "root";
	private static final String URL = "jdbc:mysql://localhost:3306/computer-database-db";

	private Connection connect;
	private static DaoFactory factoryInstace;

	/**
	 * Instantiates a new dao factory.
	 */
	private DaoFactory() {

		try {

			Driver monDriver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(monDriver);
			connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		} catch (SQLException e) {
			System.out.println("Could not initialize DaoFactory : " + e);
		}

	}

	/**
	 * Gets the connect.
	 *
	 * @return the connect
	 */
	public Connection getConnect() {
		return connect;
	}

	/**
	 * Sets the connect.
	 *
	 * @param connect the new connect
	 */
	public void setConnect(Connection connect) {
		this.connect = connect;
	}

	public static DaoFactory getInstance() {
		if (factoryInstace == null) {
			factoryInstace = new DaoFactory();
		}
		return factoryInstace;
	}

}
