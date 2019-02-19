package service;

import java.sql.SQLException;
import java.util.List;

import model.Company;

public interface CompanyService {


	/**
	 * Gets all the companies.
	 *
	 * @return all companies
	 * @throws SQLException 
	 */
	public abstract List<Company> getAll() throws SQLException;

}
