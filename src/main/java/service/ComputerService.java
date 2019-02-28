package service;

import java.sql.SQLException;
import java.util.List;

import model.Computer;

public interface ComputerService {

	/**
	 * Gets all the computers.
	 *
	 * @return all computers
	 * @throws SQLException
	 */
	public abstract List<Computer> getAll();

	/**
	 * List get computer pages
	 * 
	 * @param int pageNo, int objCount
	 * @return Computer List
	 * @throws SQLException
	 */
	public abstract List<Computer> getPage(int pageNo, int objCount);

	/**
	 * Adds the computer.
	 *
	 * @param cpInsert the cp insert
	 * @throws SQLException
	 */
	public abstract void add(Computer cpInsert);

	/**
	 * Update computer.
	 *
	 * @param cpInsert the cp insert
	 * @throws SQLException
	 */
	public abstract void update(Computer cpInsert);

	/**
	 * Delete computer.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws SQLException
	 */
	public abstract boolean delete(int id);

	/**
	 * Detail computer.
	 *
	 * @param id the id
	 * @return the computer
	 * @throws SQLException
	 */
	public abstract Computer detail(int id);

	/**
	 * Gets the computers by name.
	 *
	 * @return researched comptuers
	 * @throws SQLException
	 */
	List<Computer> getPageByName(int pageNo, int objCount, String name);

}
