package dao;

import java.sql.SQLException;
import java.util.List;

import model.Company;

public interface CompanyDAO {

	public List<Company> getList() throws SQLException;

}
