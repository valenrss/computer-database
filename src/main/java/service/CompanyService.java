package service;

import java.sql.SQLException;
import java.util.List;

import model.Company;

public interface CompanyService {


	/**
	 * Gets all the companies.
	 *
	 * @return all companies
	 * @throws SQLException the SQL exception
	 */
	public abstract List<Company> getAll() throws SQLException;
	
	/**
	 * Gets the page.
	 *
	 * @param pageNo the page no
	 * @param objCount the obj count
	 * @return the page
	 * @throws SQLException the SQL exception
	 */
	public List<Company> getPage(int pageNo, int objCount) throws SQLException;
	
	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 * @throws SQLException the SQL exception
	 */
	public Company getById(int id) throws SQLException;

}
