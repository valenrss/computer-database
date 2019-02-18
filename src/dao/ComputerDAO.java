package dao;

import java.util.List;

import model.Computer;

public interface ComputerDAO {

	

	/**
	 * List getter
	 * 
	 * @return Computer List
	 */
	public List<Computer> getComputerList();
	
	/**
	 * List get computer pages
	 * 
	 * @param int pageNo, int objCount
	 * @return Computer List
	 */
	public abstract List<Computer> getComputerPage(int pageNo, int objCount);

	/**
	 * Creation method
	 * 
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean create(Computer comp);

	/**
	 * Delete method
	 * 
	 * @param id
	 * @return boolean
	 */
	public abstract boolean delete(int id);

	/**
	 * Update method
	 * 
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean update(Computer comp);

	/**
	 * Find method
	 * 
	 * @param id
	 * @return T
	 */
	public abstract Computer find(int id);
	

}
