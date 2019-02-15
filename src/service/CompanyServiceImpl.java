package service;

import java.util.List;

import dao.CompanyDAOImpl;
import dao.DaoFactory;
import model.Company;

/**
 * The Class CompanyServiceImpl.
 */
public class CompanyServiceImpl implements CompanyService {

	private DaoFactory fact;
	private CompanyDAOImpl cnydao;

	/**
	 * Instantiates a new company service impl.
	 */
	public CompanyServiceImpl() {
		fact = DaoFactory.getInstance();
		cnydao = new CompanyDAOImpl(fact.getConnect());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.CompanyService#getAllCompanies()
	 */
	@Override
	public List<Company> getAllCompanies() {

		return cnydao.getCompanyList();
	}

}
