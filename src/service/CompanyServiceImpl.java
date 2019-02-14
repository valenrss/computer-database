package service;

import java.util.List;

import dao.CompanyDAOImpl;
import dao.DaoFactory;
import model.Company;

public class CompanyServiceImpl extends CompanyService {
	
	private DaoFactory fact;
	private CompanyDAOImpl cnydao;

	public CompanyServiceImpl(){
		fact = DaoFactory.getInstance();
		cnydao = new CompanyDAOImpl(fact.getConnect());
	}

	public List<Company> getAllCompanies() {

		return cnydao.getCompanyList();
	}

}
