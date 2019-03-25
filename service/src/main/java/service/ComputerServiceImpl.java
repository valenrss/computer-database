package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.CompanyDAOImpl;
import dao.ComputerDAOImpl;
import dto.ComputerDTO;
import mapper.Mapper;
import exception.ComputerNameEmptyException;
import exception.DateOrderException;
import model.Company;
import model.Computer;
import validator.Validator;

@Service
public class ComputerServiceImpl implements ComputerService {

	@Autowired
	private ComputerDAOImpl comptdao;
	@Autowired
	private CompanyDAOImpl cnydao;
	@Autowired
	private Validator validator;
	@Autowired
	private Mapper mapper;

	public ComputerServiceImpl() {
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.ComputerService#getAllComputers()
	 */
	@Override
	public List<ComputerDTO> getAll() {

		return mapper.mapListComputer(comptdao.getList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see service.ComputerService#getAllComputers()
	 */
	@Override
	public List<ComputerDTO> getPageByName(int pageNo, int objCount, String name, String orderOption) {

		return mapper.mapListComputer(comptdao.getPageByName(pageNo, objCount, name, orderOption));
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
	public ComputerDTO detail(int id) {
		return mapper.mapComputer(comptdao.find(id));
	}

	
	/**
	 * Delete by company.
	 *
	 * @param company the company
	 * @return true, if successful
	 */
	@Override
	@Transactional
	public boolean deleteByCompany(Company company) {
		return comptdao.deleteByCompany(company) && cnydao.deleteById(company);
		
	}

	@Override
	public Long getCount(String name) {
		return comptdao.getCount(name);
	}
	
}
