package dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.zaxxer.hikari.HikariDataSource;

public class Dao {

	private static Connection connect = null;
	
	@Autowired
	private HikariDataSource ds;
	

	public Connection connect() throws SQLException {
		
		connect = ds.getConnection();
		return connect;
	}

}
