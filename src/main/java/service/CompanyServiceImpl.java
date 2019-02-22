package service;

import java.sql.SQLException;
import java.util.List;

import dao.CompanyDAOImpl;
import dao.DaoFactory;
import model.Company;

public class CompanyServiceImpl implements CompanyService {

	private DaoFactory fact;
	private CompanyDAOImpl cnydao;
	
	private static CompanyServiceImpl companyServiceImpl;

	/**
	 * Instantiates a new company service impl.
	 */
	private CompanyServiceImpl() {
		fact = DaoFactory.getInstance();
		cnydao = CompanyDAOImpl.getInstance(fact.getConnect());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.CompanyService#getAllCompanies()
	 */
	@Override
	public List<Company> getAll() throws SQLException {

		return cnydao.getList();
	}
	

	public static CompanyServiceImpl getInstance() {
		if (companyServiceImpl == null) {
			companyServiceImpl = new CompanyServiceImpl();
		}
		return companyServiceImpl;
	}

	public List<Company> getPage(int pageNo, int objCount) throws SQLException{
		
		return cnydao.getPage(pageNo,objCount);
	}
	
	

}
