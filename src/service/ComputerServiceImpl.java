package service;

import java.util.List;

import dao.ComputerDAOImpl;
import dao.DaoFactory;
import model.Computer;

public class ComputerServiceImpl extends ComputerService{
	
	private DaoFactory fact;
	private ComputerDAOImpl comptdao;

	public ComputerServiceImpl() {
		fact=DaoFactory.getInstance();
		comptdao = new ComputerDAOImpl(fact.getConnect());
	}

	@Override
	public List<Computer> getAllComputers() {
		
		return comptdao.getComputerList();
	}

	public void addComputer(Computer cpInsert) {
		comptdao.create(cpInsert);
		
	}

	public void updateComputer(Computer cpInsert) {
		comptdao.update(cpInsert);
		
	}

	public boolean deleteComputer(int id) {
		
		return comptdao.delete(id);
	}

	public Computer detailComputer(int id) {
		return comptdao.find(id);
	}

}
