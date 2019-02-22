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
	public List<Computer> getList() throws SQLException;
	
	/**
	 * List get computer pages
	 * 
	 * @param int pageNo, int objCount
	 * @return Computer List
	 * @throws SQLException 
	 */
	public abstract List<Computer> getPage(int pageNo, int objCount) throws SQLException;
	
	/**
	 * List get computer pages
	 * 
	 * @param int pageNo, int objCount , String name
	 * @return Computer List
	 * @throws SQLException 
	 */
	public abstract List<Computer> getPageByName(int pageNo, int objCount, String name) throws SQLException;

	/**
	 * Creation method
	 * 
	 * @param obj
	 * @return boolean
	 * @throws SQLException 
	 */
	public abstract void create(Computer comp) throws SQLException;

	/**
	 * Delete method
	 * 
	 * @param id
	 * @return boolean
	 * @throws SQLException 
	 */
	public abstract boolean delete(int id) throws SQLException;

	/**
	 * Update method
	 * 
	 * @param obj
	 * @return boolean
	 * @throws SQLException 
	 */
	public abstract void update(Computer comp) throws SQLException;

	/**
	 * Find method
	 * 
	 * @param id
	 * @return T
	 * @throws SQLException 
	 */
	public abstract Computer find(int id) throws SQLException;
	

}
