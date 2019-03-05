package dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * A factory for creating Dao objects.
 */
public class DaoFactory {


	private static DaoFactory factoryInstace;
	
	private static String configFile = "/config.properties";
	private static HikariConfig cfg;
	private static HikariDataSource ds;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    
	/**
	 * Gets the connect.
	 *
	 * @return the connect
	 */
	
	private DaoFactory() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		}
		cfg = new HikariConfig(configFile);
		ds = new HikariDataSource(cfg);
	}
	

	public Connection getConnect() {

			try {
				return ds.getConnection();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				return null;
			}
		
	}


	public static DaoFactory getInstance() {
		if (factoryInstace == null) {
			factoryInstace = new DaoFactory();
		}
		return factoryInstace;
	}

}
