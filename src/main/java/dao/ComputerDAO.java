package dao;

import java.sql.SQLException;
import java.util.List;

import model.Computer;

public interface ComputerDAO {

	/**
	 * List getter
	 * 
	 * @return Computer List
	 * @throws SQLException
	 */
	public List<Computer> getList();

	/**
	 * List get computer pages
	 * 
	 * @param int pageNo, int objCount
	 * @return Computer List
	 * @throws SQLException
	 */
	public abstract List<Computer> getPage(int pageNo, int objCount);

	/**
	 * List get computer pages
	 * 
	 * @param int pageNo, int objCount , String name
	 * @return Computer List
	 * @throws SQLException
	 */
	public abstract List<Computer> getPageByName(int pageNo, int objCount, String name);

	/**
	 * Creation method
	 * 
	 * @param obj
	 * @return boolean
	 * @throws SQLException
	 */
	public abstract void create(Computer comp);

	/**
	 * Delete method
	 * 
	 * @param id
	 * @return boolean
	 * @throws SQLException
	 */
	public abstract boolean delete(int id);

	/**
	 * Update method
	 * 
	 * @param obj
	 * @return boolean
	 * @throws SQLException
	 */
	public abstract void update(Computer comp);

	/**
	 * Find method
	 * 
	 * @param id
	 * @return T
	 * @throws SQLException
	 */
	public abstract Computer find(int id);

}
