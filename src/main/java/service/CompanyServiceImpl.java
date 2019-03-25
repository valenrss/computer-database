package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CompanyDAOImpl;
import model.Company;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyDAOImpl cnydao;

	/**
	 * Instantiates a new company service impl.
	 */
	private CompanyServiceImpl() {
	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.CompanyService#getAllCompanies()
	 */
	@Override
	public List<Company> getAll() {

		return cnydao.getList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.CompanyService#getPage()
	 */
	@Override
	public List<Company> getPage(int pageNo, int objCount) {

		return cnydao.getPage(pageNo, objCount);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.CompanyService#getById()
	 */
	@Override
	public Company getById(int id) {
		return cnydao.getById(id);
	}

}
