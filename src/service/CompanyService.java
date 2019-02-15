package service;

import java.util.List;

import model.Company;

/**
 * The Class CompanyService.
 */
public interface CompanyService {


	/**
	 * Gets all the companies.
	 *
	 * @return all companies
	 */
	public abstract List<Company> getAllCompanies();

}
