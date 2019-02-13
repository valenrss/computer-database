package dao;

import java.sql.Connection;
import java.util.List;

import model.Computer;

public abstract class ComputerDAO<T> {

	protected Connection connect = null;

	public ComputerDAO(Connection conn) {

		this.connect = conn;

	}
	
	/**
	 * List getter
	 * 
	 * @param obj
	 * @return boolean
	 */
	public List<Computer> getComputerList;
	
	/**
	 * Creation method
	 * 
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean create(T obj);

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
	public abstract boolean update(T obj);

	/**
	 * Find method
	 * 
	 * @param id
	 * @return T
	 */
	public abstract T find(int id);

}
