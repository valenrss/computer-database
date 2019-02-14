package service;

import java.util.List;

import model.Computer;

/**
 * The Class ComputerService.
 */
public abstract class ComputerService {

	/**
	 * Instantiates a new computer service.
	 */
	public ComputerService() {

	}

	/**
	 * Gets all the computers.
	 *
	 * @return all computers
	 */
	public abstract List<Computer> getAllComputers();
	
	/**
	 * List get computer pages
	 * 
	 * @param int pageNo, int objCount
	 * @return Computer List
	 */
	public abstract List<Computer> getComputerPage(int pageNo, int objCount);

	/**
	 * Adds the computer.
	 *
	 * @param cpInsert the cp insert
	 */
	public abstract void addComputer(Computer cpInsert);

	/**
	 * Update computer.
	 *
	 * @param cpInsert the cp insert
	 */
	public abstract void updateComputer(Computer cpInsert);

	/**
	 * Delete computer.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public abstract boolean deleteComputer(int id);

	/**
	 * Detail computer.
	 *
	 * @param id the id
	 * @return the computer
	 */
	public abstract Computer detailComputer(int id);

}
