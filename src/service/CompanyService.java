package service;

import java.util.List;

import model.Company;

public interface CompanyService {


	/**
	 * Gets all the companies.
	 *
	 * @return all companies
	 */
	public abstract List<Company> getAllCompanies();

}
