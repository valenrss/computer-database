package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import model.Company;
import model.Computer;

@Repository
public interface ComputerDAO {

	/**
	 * List getter.
	 *
	 * @return Computer List
	 */
	public List<Computer> getList();


	/**
	 * List get computer pages.
	 *
	 * @param pageNo the page no
	 * @param objCount the obj count
	 * @param name the name
	 * @return Computer List
	 */
	public abstract List<Computer> getPageByName(int pageNo, int objCount, String name, String orderOption);

	/**
	 * Creation method.
	 *
	 * @param comp the comp
	 * @return boolean
	 */
	public abstract void create(Computer comp);

	/**
	 * Delete method.
	 *
	 * @param id the id
	 * @return boolean
	 */
	public abstract boolean delete(int id);

	/**
	 * Update method.
	 *
	 * @param comp the comp
	 * @return boolean
	 */
	public abstract void update(Computer comp);

	/**
	 * Find method.
	 *
	 * @param id the id
	 * @return T
	 */
	public abstract Computer find(int id);

	/**
	 * Delete by company.
	 *
	 * @param company the company
	 * @return true, if successful
	 */
	public boolean deleteByCompany(Company company);

	/**
	 * Get computer count
	 *
	 * @return computer count
	 */
	public Long getCount(String name);

}
