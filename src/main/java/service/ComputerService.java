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
	public abstract List<Computer> getAll() throws SQLException;
	
	
	
	/**
	 * List get computer pages
	 * 
	 * @param int pageNo, int objCount
	 * @return Computer List
	 * @throws SQLException 
	 */
	public abstract List<Computer> getPage(int pageNo, int objCount) throws SQLException;

	/**
	 * Adds the computer.
	 *
	 * @param cpInsert the cp insert
	 * @throws SQLException 
	 */
	public abstract void add(Computer cpInsert) throws SQLException;

	/**
	 * Update computer.
	 *
	 * @param cpInsert the cp insert
	 * @throws SQLException 
	 */
	public abstract void update(Computer cpInsert) throws SQLException;

	/**
	 * Delete computer.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws SQLException 
	 */
	public abstract boolean delete(int id) throws SQLException;

	/**
	 * Detail computer.
	 *
	 * @param id the id
	 * @return the computer
	 * @throws SQLException 
	 */
	public abstract Computer detail(int id) throws SQLException;


	/**
	 * Gets the computers by name.
	 *
	 * @return researched comptuers
	 * @throws SQLException 
	 */
	List<Computer> getPageByName(int pageNo, int objCount, String name) throws SQLException;

}
