package service;

import java.sql.SQLException;

import java.util.List;

import org.springframework.stereotype.Service;

import dto.CompanyDTO;
import model.Company;

@Service
public interface CompanyService {

	/**
	 * Gets all the companies.
	 *
	 * @return all companies
	 * @throws SQLException the SQL exception
	 */
	public abstract List<CompanyDTO> getAll();

	/**
	 * Gets the page.
	 *
	 * @param pageNo   the page no
	 * @param objCount the obj count
	 * @return the page
	 * @throws SQLException the SQL exception
	 */
	public List<CompanyDTO> getPage(int pageNo, int objCount);

	/**
	 * Gets the by id.
	 *
	 * @param id the id
	 * @return the by id
	 * @throws SQLException the SQL exception
	 */
	public Company getById(int id);

}
