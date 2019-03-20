package dao;

import model.Company;
import model.Computer;
import repository.ComputerRepository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zaxxer.hikari.HikariDataSource;
import mapper.ComputerRowMapper;

@Repository
@Transactional
public class ComputerDAOImpl implements ComputerDAO {

	private static final String SQL_DELETE_COMPUTER_WHERE_COMPANY_ID = "DELETE FROM  computer  WHERE  company_id  = ?";
	private static final String SQL_FIND_BY_ID = "SELECT  computer.id , computer.name , introduced , discontinued , company_id , company.name  FROM  computer LEFT JOIN company ON computer.company_id = company.id WHERE  computer.id  = ?";

	private static final String SQL_UPDATE = "UPDATE `computer` SET `name` = ?, `introduced` = ?, `discontinued` = ?, `company_id` = ? WHERE `id` = ?";
	private static final String SQL_DELETE_ID = "DELETE FROM `computer` WHERE `id` = ?";
	private static final String SQL_CREATE = "INSERT INTO `computer` (`name`,`introduced`,`discontinued`, `company_id`) VALUES (?,?,?,?)";
	private static final String SQL_GETCOUNT = "SELECT COUNT(*) FROM computer;";

	@Autowired
	private ComputerRowMapper computerRowMapper;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ComputerRepository computerRepository;

	@Autowired
	public void setDataSource(HikariDataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * Gets the computer list.
	 *
	 * @return the computer list
	 */
	public List<Computer> getList() {

		return computerRepository.getList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ComputerDAO#create(java.lang.Object)
	 */
	@Override
	public void create(Computer comp) {

		jdbcTemplate.update(SQL_CREATE, comp.getName(), new java.sql.Date(comp.getDateIntroduced().getTime()), new java.sql.Date(comp.getDateDiscontinued().getTime()),
				comp.getCompany().getId());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ComputerDAO#delete(int)
	 */
	@Override
	public boolean delete(int id) {

		return jdbcTemplate.update(SQL_DELETE_ID, id) > 0;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ComputerDAO#deleteByCompany(Company company)
	 */
	@Override
	public boolean deleteByCompany(Company company) {

		return jdbcTemplate.update(SQL_DELETE_COMPUTER_WHERE_COMPANY_ID, company.getId()) > 0;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ComputerDAO#update(java.lang.Object)
	 */
	@Override
	public void update(Computer comp) {

		jdbcTemplate.update(SQL_UPDATE, comp.getName(), new java.sql.Date(comp.getDateIntroduced().getTime()), new java.sql.Date(comp.getDateDiscontinued().getTime()),
				comp.getCompany().getId(), comp.getId());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ComputerDAO#find(int)
	 */
	@Override
	public Computer find(int id) {

		return jdbcTemplate.query(SQL_FIND_BY_ID, computerRowMapper, id).get(0);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ComputerDAO#getPageByName(int, int, String)
	 */
	@Override
	public List<Computer> getPageByName(int pageNo, int objCount, String name, String orderOption) {

		int minId = pageNo * objCount - objCount;
		

		return computerRepository.getPage(minId, objCount, name, orderOption);

	}

	@Override
	public Integer getCount() { // TODO add search parameter to make it work
		return jdbcTemplate.queryForObject(SQL_GETCOUNT, Integer.class);
	}

}
