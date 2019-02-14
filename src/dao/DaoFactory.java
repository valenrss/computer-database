package dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.*;

/**
 * A factory for creating Dao objects.
 */
public class DaoFactory {

	private Connection connect;
	private static DaoFactory factoryInstace = new DaoFactory();

	/**
	 * Instantiates a new dao factory.
	 */
	public DaoFactory() {

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("password");
		dataSource.setServerName("localhost");
		dataSource.setPortNumber(3306);
		dataSource.setDatabaseName("computer-database-db");
		try {
			connect = dataSource.getConnection();
		} catch (SQLException e) {
			System.out.println("Could not initialize DaoFactory !");
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
		return factoryInstace;
	}

}
