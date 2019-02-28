package dao;

import java.util.List;

import model.Company;

public interface CompanyDAO {

	public List<Company> getList();

	public List<Company> getPage(int pageNo, int objCount);

	public Company getById(int id);

}
