package dao;


import java.sql.*;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 * A factory for creating Dao objects.
 */
public class DaoFactory {

	private Connection connect;
	private static DaoFactory factoryInstace = new DaoFactory();

	/**
	 * Instantiates a new dao factory.
	 */
	private DaoFactory() {

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
