package dao;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import model.Computer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class ComputerDAO extends DAO<Computer>{
	
	protected Connection connect = null;
	private Statement stmt;
	private ResultSet rs;

	public ComputerDAO(Connection conn) {
		
		super(conn);		
	}
	
	public List<Computer> getComputerList() {
		
		List<Computer> cpList = new ArrayList<>();

        try {
			
        	stmt = super.connect.createStatement();
	        rs = stmt.executeQuery("SELECT * FROM `computer`");

	        while (rs.next()) {
	        	cpList.add(new Computer(rs.getInt("id"),rs.getString("name"),rs.getTimestamp("introduced"),rs.getTimestamp("discontinued"),rs.getInt("company_id")));
	        }

	        
		} catch (SQLException e) {
			System.out.println("Request Failed ! Error : "+e);
			e.printStackTrace();
		}
        
        return cpList;
		
	}

	@Override
	public boolean create(Computer comp) {
		
		try {
			
			PreparedStatement prep1 = super.connect.prepareStatement("INSERT INTO `computer` (`name`,`introduced`,`discontinued`, `company_id`) VALUES (?,?,?,?)");
			
			prep1.setString(1,comp.getName());
			prep1.setTimestamp(2, comp.getDateIntroduced());
			prep1.setTimestamp(3, comp.getDateDiscontinued());
			prep1.setInt(4, comp.getCompanyId());
			
			prep1.executeUpdate();
			//stmt = super.connect.createStatement();
			//stmt.executeUpdate("INSERT INTO `computer` (`name`,`introduced`,`discontinued`, `company_id`)"+ " VALUES ('"+comp.getName()+"','"+comp.getDateIntroduced()+"','"+comp.getDateDiscontinued()+"','"+comp.getCompanyId()+"');");
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		
		try {
			PreparedStatement prep1 = super.connect.prepareStatement("DELETE FROM `computer` WHERE `name` = ?");
			prep1.setInt(1,id);
		} catch (SQLException e) {
			System.out.println("Could not find Computer ID : "+id);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Computer obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Computer find(int id) {
		Computer comp = null;      
	      
		    try {
		      ResultSet result = super.connect.createStatement(
		        ResultSet.TYPE_SCROLL_INSENSITIVE,
		        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM `computer` WHERE `id` = " + id);
		      if(result.first())
		    	  comp = new Computer(id,result.getString("name"),result.getTimestamp("introduced"),result.getTimestamp("discontinued"),result.getInt("company_id"));         
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    return comp;
	}

}
