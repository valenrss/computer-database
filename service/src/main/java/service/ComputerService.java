package service;

import java.util.List;

import org.springframework.stereotype.Service;

import dto.ComputerDTO;
import exception.ComputerNameEmptyException;
import exception.DateOrderException;
import model.Company;
import model.Computer;

/**
 * The Interface ComputerService.
 */
@Service
public interface ComputerService {

	/**
	 * Gets all the computers.
	 *
	 * @return all computers
	 */
	public abstract List<ComputerDTO> getAll();


	/**
	 * Adds the computer.
	 *
	 * @param cpInsert the cp insert
	 * @throws ComputerNameEmptyException 
	 * @throws DateOrderException 
	 */
	public abstract void add(Computer cpInsert) throws DateOrderException, ComputerNameEmptyException;

	/**
	 * Update computer.
	 *
	 * @param cpInsert the cp insert
	 * @throws ComputerNameEmptyException 
	 * @throws DateOrderException 
	 */
	public abstract void update(Computer cpInsert) throws DateOrderException, ComputerNameEmptyException;

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
	public abstract ComputerDTO detail(int id);
	

	/**
	 * Gets the computers by name.
	 *
	 * @param pageNo the page no
	 * @param objCount the obj count
	 * @param name the name
	 * @return researched comptuers
	 */
	public List<ComputerDTO> getPageByName(int pageNo, int objCount, String name, String orderOption);

	/**
	 * Delete by company.
	 *
	 * @param company the company
	 * @return true, if successful
	 */
	public boolean deleteByCompany(Company company);


	public Long getCount(String name);

}
