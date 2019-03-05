package service;

import java.util.List;

import model.Company;
import model.Computer;
import model.SortOptions;

/**
 * The Interface ComputerService.
 */
public interface ComputerService {

	/**
	 * Gets all the computers.
	 *
	 * @return all computers
	 */
	public abstract List<Computer> getAll();

	/**
	 * List get computer pages.
	 *
	 * @param pageNo the page no
	 * @param objCount the obj count
	 * @return Computer List
	 */
	public abstract List<Computer> getPage(int pageNo, int objCount);

	/**
	 * Adds the computer.
	 *
	 * @param cpInsert the cp insert
	 */
	public abstract void add(Computer cpInsert);

	/**
	 * Update computer.
	 *
	 * @param cpInsert the cp insert
	 */
	public abstract void update(Computer cpInsert);

	/**
	 * Delete computer.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public abstract boolean delete(int id);

	/**
	 * Detail computer.
	 *
	 * @param id the id
	 * @return the computer
	 */
	public abstract Computer detail(int id);
	
	/**
	 * Compare by.
	 *
	 * @param cList the c list
	 * @param option the option
	 * @return the list
	 */
	public List<Computer> compareBy(List<Computer> cList, SortOptions option);

	/**
	 * Gets the computers by name.
	 *
	 * @param pageNo the page no
	 * @param objCount the obj count
	 * @param name the name
	 * @return researched comptuers
	 */
	List<Computer> getPageByName(int pageNo, int objCount, String name);

	/**
	 * Delete by company.
	 *
	 * @param company the company
	 * @return true, if successful
	 */
	boolean deleteByCompany(Company company);

}
