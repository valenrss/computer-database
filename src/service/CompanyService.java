package service;

import java.util.List;

import model.Company;

/**
 * The Class CompanyService.
 */
public abstract class CompanyService {

	/**
	 * Instantiates a new company service.
	 */
	public CompanyService() {

	}

	/**
	 * Gets all the companies.
	 *
	 * @return all companies
	 */
	public abstract List<Company> getAllCompanies();

}
