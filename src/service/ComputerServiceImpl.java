package service;

import java.util.List;

import dao.ComputerDAOImpl;
import dao.DaoFactory;
import model.Computer;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerServiceImpl.
 */
public class ComputerServiceImpl implements ComputerService {

	/** The fact. */
	private DaoFactory fact;

	/** The comptdao. */
	private ComputerDAOImpl comptdao;

	/**
	 * Instantiates a new computer service impl.
	 */
	public ComputerServiceImpl() {
		fact = DaoFactory.getInstance();
		comptdao = new ComputerDAOImpl(fact.getConnect());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.ComputerService#getAllComputers()
	 */
	@Override
	public List<Computer> getAllComputers() {

		return comptdao.getComputerList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.ComputerService#addComputer(model.Computer)
	 */
	@Override
	public void addComputer(Computer cpInsert) {
		comptdao.create(cpInsert);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.ComputerService#updateComputer(model.Computer)
	 */
	@Override
	public void updateComputer(Computer cpInsert) {
		comptdao.update(cpInsert);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.ComputerService#deleteComputer(int)
	 */
	@Override
	public boolean deleteComputer(int id) {

		return comptdao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.ComputerService#detailComputer(int)
	 */
	@Override
	public Computer detailComputer(int id) {
		return comptdao.find(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.ComputerService#getComputerPage(int, int)
	 */
	@Override
	public List<Computer> getComputerPage(int pageNo, int objCount) {

		return comptdao.getComputerPage(pageNo, objCount);
	}

}
