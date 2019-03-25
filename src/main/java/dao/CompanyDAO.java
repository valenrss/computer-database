package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import model.Company;

@Repository
public interface CompanyDAO {

	public List<Company> getList();

	public List<Company> getPage(int pageNo, int objCount);

	public Company getById(int id);

	boolean deleteById(Company company);

}
