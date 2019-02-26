package dao;

import java.sql.SQLException;
import java.util.List;

import model.Company;

public interface CompanyDAO {

	public List<Company> getList() throws SQLException;
	
	public List<Company> getPage(int pageNo, int objCount) throws SQLException;
	
	public Company getById(int id) throws SQLException;

}
