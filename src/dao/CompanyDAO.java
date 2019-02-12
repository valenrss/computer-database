package dao;

import model.Company;
import model.Computer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class CompanyDAO extends DAO<Company> {
	
	protected Connection connect = null;
	private Statement stmt;
	private ResultSet rs;

	public CompanyDAO(java.sql.Connection conn) {
		
		super(conn);
	}
	
	
	public List<Company> getList() {
		
		List<Company> compList = new ArrayList<>();

        try {
			
        	stmt = super.connect.createStatement();
	        rs = stmt.executeQuery("SELECT * FROM `company`");

	        while (rs.next()) {
	        	compList.add(new Company(rs.getInt("id"),rs.getString("name")));
	        }

	        
		} catch (SQLException e) {
			System.out.println("Request Failed ! Error : "+e);
			e.printStackTrace();
		}
        
        return compList;
		
	}
	
	
	

	@Override
	public boolean create(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Company obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Company find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
