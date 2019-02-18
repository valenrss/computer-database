package service;

import java.util.List;

import dao.ComputerDAOImpl;
import dao.DaoFactory;
import model.Computer;

public class ComputerServiceImpl implements ComputerService {

	private DaoFactory fact;
	private ComputerDAOImpl comptdao;
	
	private static ComputerServiceImpl computerServiceImpl;

	public ComputerServiceImpl() {
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
	
	public static ComputerServiceImpl getInstance() {
		if (computerServiceImpl == null) {
			computerServiceImpl = new ComputerServiceImpl();
		}
		return computerServiceImpl;
	}

}
