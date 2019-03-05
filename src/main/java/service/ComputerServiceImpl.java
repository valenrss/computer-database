package service;

import java.util.List;

import dao.ComputerDAOImpl;
import dao.DaoFactory;
import model.Company;
import model.Computer;
import model.SortOptions;

public class ComputerServiceImpl implements ComputerService {

	private DaoFactory fact;
	private ComputerDAOImpl comptdao;

	private static ComputerServiceImpl computerServiceImpl;

	private ComputerServiceImpl() {
		fact = DaoFactory.getInstance();
		comptdao = ComputerDAOImpl.getInstance(fact.getConnect());
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
	public List<Computer> getPageByName(int pageNo, int objCount, String name) {

		return comptdao.getPageByName(pageNo, objCount, name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.ComputerService#addComputer(model.Computer)
	 */
	@Override
	public void add(Computer cpInsert) {
		comptdao.create(cpInsert);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.ComputerService#updateComputer(model.Computer)
	 */
	@Override
	public void update(Computer cpInsert) {
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


	/*
	 * (non-Javadoc)
	 * 
	 * @see service.ComputerService#compareBy
	 */
	@Override
	public List<Computer> compareBy(List<Computer> cList, SortOptions option) {
		
		
		switch (option) {
		case NAME:
			cList.sort((Computer p1, Computer p2) -> p1.getName() != null ?  p1.getName().compareTo(p2.getName()) : -1);
			return cList;
		case DATEINTRODUCED:
			cList.sort((Computer p1, Computer p2) -> (p1.getDateIntroduced() == null || p2.getDateIntroduced() == null) ? -700 : p1.getDateIntroduced().compareTo(p2.getDateIntroduced()));
			return cList;
		case DATEDISCONTINUED:
			cList.sort((Computer p1, Computer p2) -> (p1.getDateDiscontinued() == null || p2.getDateDiscontinued() == null) ? -700 :  p1.getDateDiscontinued().compareTo(p2.getDateDiscontinued()));
			return cList;
		case COMPANY:
			cList.sort((Computer p1, Computer p2) -> p1.getCompany().compareTo(p2.getCompany()));
			return cList;
		default:
			cList.sort((Computer p1, Computer p2) -> Integer.valueOf(p1.getId()).compareTo(p2.getId()));
			return cList;
		}
		
	}
	
	
	public static ComputerServiceImpl getInstance() {
		if (computerServiceImpl == null) {
			computerServiceImpl = new ComputerServiceImpl();
		}
		return computerServiceImpl;
	}
	
}
