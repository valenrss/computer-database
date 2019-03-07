package service;

import java.util.List;

import dao.ComputerDAOImpl;
import dao.DaoFactory;
import exception.ComputerNameEmptyException;
import exception.DateOrderException;
import model.Company;
import model.Computer;
import validator.Validator;

public class ComputerServiceImpl implements ComputerService {

	private DaoFactory fact;
	private ComputerDAOImpl comptdao;
	private Validator validator;
	private static ComputerServiceImpl computerServiceImpl;

	private ComputerServiceImpl() {
		fact = DaoFactory.getInstance();
		comptdao = ComputerDAOImpl.getInstance(fact.getConnect());
		validator = Validator.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.ComputerService#getAllComputers()
	 */
	@Override
	public List<Computer> getAll() {

		return comptdao.getList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.ComputerService#getAllComputers()
	 */
	@Override
	public List<Computer> getPageByName(int pageNo, int objCount, String name, String orderOption) {

		return comptdao.getPageByName(pageNo, objCount, name, orderOption);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.ComputerService#addComputer(model.Computer)
	 */
	@Override
	public void add(Computer cpInsert) throws DateOrderException, ComputerNameEmptyException{
		
		validator.checkDate(cpInsert.getDateIntroduced(), cpInsert.getDateDiscontinued());
		validator.checkName(cpInsert.getName());
		
		comptdao.create(cpInsert);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.ComputerService#updateComputer(model.Computer)
	 */
	@Override
	public void update(Computer cpInsert) throws DateOrderException, ComputerNameEmptyException{
		
		validator.checkDate(cpInsert.getDateIntroduced(), cpInsert.getDateDiscontinued());
		validator.checkName(cpInsert.getName());
		
		comptdao.update(cpInsert);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.ComputerService#deleteComputer(int)
	 */
	@Override
	public boolean delete(int id) {

		return comptdao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.ComputerService#detailComputer(int)
	 */
	@Override
	public Computer detail(int id) {
		return comptdao.find(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.ComputerService#getComputerPage(int, int)
	 */
	@Override
	public List<Computer> getPage(int pageNo, int objCount) {

		return comptdao.getPage(pageNo, objCount);
	}
	
	/**
	 * Delete by company.
	 *
	 * @param company the company
	 * @return true, if successful
	 */
	@Override
	public boolean deleteByCompany(Company company) {
		return comptdao.deleteByCompany(company);
		
	}
	
	
	public static ComputerServiceImpl getInstance() {
		if (computerServiceImpl == null) {
			computerServiceImpl = new ComputerServiceImpl();
		}
		return computerServiceImpl;
	}
	
}
