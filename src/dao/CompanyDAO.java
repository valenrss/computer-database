package dao;

import java.sql.Connection;
import java.util.List;

import model.Company;

/**
 * The Class CompanyDAO.
 *
 * @param <T> the generic type
 */
public abstract class CompanyDAO<T> {

	protected Connection connect = null;

	/**
	 * Instantiates a new company DAO.
	 *
	 * @param conn the Connection
	 */
	public CompanyDAO(Connection conn) {

		this.connect = conn;

	}

	/** The get company list. */
	public List<Company> getCompanyList;

}
