package dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.*;

public class DaoFactory {
	
	private Connection connect;

	public DaoFactory() {
		
		MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
                                                                                                             dataSource.setPassword("Build23805");
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(3306);
		dataSource.setDatabaseName("computer-database-db");
        try {
			connect = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Could not initialize DaoFactory !");
		}
        
        

	}


	public Connection getConnect() {
		return connect;
	}

	public void setConnect(Connection connect) {
		this.connect = connect;
	}

}
