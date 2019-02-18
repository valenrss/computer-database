package service;

import java.util.List;

import model.Computer;

public interface ComputerService {


	/**
	 * Gets all the computers.
	 *
	 * @return all computers
	 */
	public abstract List<Computer> getAll();
	
	/**
	 * List get computer pages
	 * 
	 * @param int pageNo, int objCount
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

}
