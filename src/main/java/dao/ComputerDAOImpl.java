package dao;

import model.Company;
import model.Computer;
import service.CompanyServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ComputerDAOImpl extends Dao implements ComputerDAO {

	private static final String SQL_DELETE_COMPUTER_WHERE_COMPANY_ID = "DELETE FROM `computer` WHERE `company_id` = ?";
	private static final String SQL_DELETE_FROM_COMPANY_WHERE_ID = "DELETE FROM `company` WHERE `id` = ?";
	private static final String SQL_FIND_BY_ID = "SELECT `id`,`name`,`introduced`,`discontinued`, `company_id` FROM `computer` WHERE `id` = ?";
	private static final String SQL_GETLIST = "SELECT `id`,`name`,`introduced`,`discontinued`, `company_id` FROM `computer`";
	private static final String SQL_PAGE = "SELECT `id`,`name`,`introduced`,`discontinued`, `company_id` FROM `computer` WHERE id >= ? AND id < ?";
	
	private static final String SQL_PAGE_ID = "SELECT * FROM `computer` LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY ISNULL(computer.id), computer.id ASC limit ? offset ?";
	private static final String SQL_PAGE_NAME = "SELECT * FROM `computer` LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY ISNULL(computer.name), computer.name ASC limit ? offset ?";
	private static final String SQL_PAGE_NAME_DESC = "SELECT * FROM `computer` LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY ISNULL(computer.name), computer.name DESC limit ? offset ?";

	private static final String SQL_PAGE_INTRODUCED = "SELECT * FROM `computer` LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY ISNULL(computer.introduced), computer.introduced ASC limit ? offset ?";
	private static final String SQL_PAGE_DISCONTINUED = "SELECT * FROM `computer` LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY ISNULL(computer.discontinued), computer.discontinued ASC limit ? offset ?";
	private static final String SQL_PAGE_COMPANY = "SELECT * FROM `computer` LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY ISNULL(company.name), company.name ASC limit ? offset ?";
	private static final String SQL_UPDATE = "UPDATE `computer` SET `name` = ?, `introduced` = ?, `discontinued` = ?, `company_id` = ? WHERE `id` = ?";
	private static final String SQL_DELETE_ID = "DELETE FROM `computer` WHERE `id` = ?";
	private static final String SQL_CREATE = "INSERT INTO `computer` (`name`,`introduced`,`discontinued`, `company_id`) VALUES (?,?,?,?)";
	private static ResultSet rs;
	
	private static Logger logger = LoggerFactory.getLogger(ComputerDAOImpl.class);
	@Autowired
	private CompanyServiceImpl cpnyService;


	private ComputerDAOImpl() {	
	}

	/**
	 * Gets the computer list.
	 *
	 * @return the computer list
	 */
	public List<Computer> getList() {

		List<Computer> cpList = new ArrayList<>();

		PreparedStatement prep;
		try {
			prep = connect().prepareStatement(SQL_GETLIST);
			prep.executeQuery();
			ResultSet rs = prep.getResultSet();

			while (rs.next()) {
				cpList.add(new Computer(rs.getInt("id"), rs.getString("name"), rs.getTimestamp("introduced"),
						rs.getTimestamp("discontinued"),
						cpnyService.getById(rs.getInt("company_id"))));
			}

			return cpList;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return cpList;

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ComputerDAO#create(java.lang.Object)
	 */
	@Override
	public void create(Computer comp) {

		try {
			PreparedStatement prep1 = connect().prepareStatement(SQL_CREATE);

			prep1.setString(1, comp.getName());

			try {
				prep1.setDate(2, new java.sql.Date(comp.getDateIntroduced().getTime()));
			} catch (NullPointerException e) {
				prep1.setDate(2, null);
			}
			try {
				prep1.setDate(3, new java.sql.Date(comp.getDateDiscontinued().getTime()));
			} catch (NullPointerException e) {
				prep1.setDate(3, null);
			}

			prep1.setInt(4, comp.getCompany().getId());

			prep1.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ComputerDAO#delete(int)
	 */
	@Override
	public boolean delete(int id) {

		try {

			int success = 0;
			PreparedStatement prep1 = connect().prepareStatement(SQL_DELETE_ID);
			prep1.setInt(1, id);
			success = prep1.executeUpdate();
			if (success == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ComputerDAO#deleteByCompany(Company company)
	 */
	@Override
	public boolean deleteByCompany(Company company) {
		try { Connection connect = connect();
			int success = 0;
			PreparedStatement prep1 = connect.prepareStatement(SQL_DELETE_FROM_COMPANY_WHERE_ID);
			PreparedStatement prep2 = connect.prepareStatement(SQL_DELETE_COMPUTER_WHERE_COMPANY_ID);
			connect.setAutoCommit(false);
			prep1.setInt(1, company.getId());
			prep2.setInt(1, company.getId());
			success = prep2.executeUpdate() + prep1.executeUpdate();
			if (success > 0) {
				connect.commit();
				connect.setAutoCommit(true); // only 1 connect
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ComputerDAO#update(java.lang.Object)
	 */
	@Override
	public void update(Computer comp) {

		try {

			PreparedStatement prep1 = connect().prepareStatement(SQL_UPDATE);

			prep1.setString(1, comp.getName());
			try {
				prep1.setDate(2, new java.sql.Date(comp.getDateIntroduced().getTime()));
			} catch (NullPointerException e) {
				prep1.setDate(2, null);
			}
			try {
				prep1.setDate(3, new java.sql.Date(comp.getDateDiscontinued().getTime()));
			} catch (NullPointerException e) {
				prep1.setDate(3, null);
			}

			prep1.setInt(4, comp.getCompany().getId());
			prep1.setInt(5, comp.getId());

			prep1.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ComputerDAO#find(int)
	 */
	@Override
	public Computer find(int id) {
		Computer comp = null;

		try {

			PreparedStatement prep = connect().prepareStatement(SQL_FIND_BY_ID);
			prep.setInt(1, id);
			prep.executeQuery();
			ResultSet result = prep.getResultSet();

			if (result.first())
				comp = new Computer(id, result.getString("name"), result.getTimestamp("introduced"),
						result.getTimestamp("discontinued"),
						cpnyService.getById(result.getInt("company_id")));
			return comp;

		} catch (SQLException e) {
			logger.error(e.getMessage());
			return comp;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ComputerDAO#getPage(int, int)
	 */
	@Override
	public List<Computer> getPage(int pageNo, int objCount) {
		List<Computer> cpList = new ArrayList<>();

		int minId = pageNo * objCount - objCount;
		int maxId = minId + objCount;

		try {

			PreparedStatement prep1 = connect().prepareStatement(SQL_PAGE);

			prep1.setInt(1, minId);
			prep1.setInt(2, maxId);

			prep1.executeQuery();

			rs = prep1.getResultSet();

			while (rs.next()) {
				cpList.add(new Computer(rs.getInt("id"), rs.getString("name"), rs.getTimestamp("introduced"),
						rs.getTimestamp("discontinued"),
						cpnyService.getById(rs.getInt("company_id"))));
			}

			return cpList;

		} catch (SQLException e) {
			logger.error(e.getMessage());
			return cpList;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ComputerDAO#getPageByName(int, int, String)
	 */
	@Override
	public List<Computer> getPageByName(int pageNo, int objCount, String name, String orderOption) {

		List<Computer> cpList = new ArrayList<>();

		int minId = pageNo * objCount - objCount;

		try {Connection connect = connect();
			PreparedStatement prep1;

			if (orderOption != null) {
				switch (orderOption) {
				case "name":
					prep1 = connect.prepareStatement(SQL_PAGE_NAME);
					break;
				case "nameDesc":
					prep1 = connect.prepareStatement(SQL_PAGE_NAME_DESC);
					break;
				case "introdate":
					prep1 = connect.prepareStatement(SQL_PAGE_INTRODUCED);
					break;
				case "discondate":
					prep1 = connect.prepareStatement(SQL_PAGE_DISCONTINUED);
					break;
				case "company":
					prep1 = connect.prepareStatement(SQL_PAGE_COMPANY);
					break;
				default:
					prep1 = connect.prepareStatement(SQL_PAGE_ID);
					break;
				}
			} else {
				prep1 = connect.prepareStatement(SQL_PAGE_ID);
			}

			prep1.setString(1, "%" + name + "%");
			prep1.setString(2, "%" + name + "%");
			prep1.setInt(3, objCount);
			prep1.setInt(4, minId);

			logger.info(prep1.toString());

			prep1.executeQuery();

			rs = prep1.getResultSet();

			while (rs.next()) {
				cpList.add(new Computer(rs.getInt("id"), rs.getString("name"), rs.getTimestamp("introduced"),
						rs.getTimestamp("discontinued"),
						cpnyService.getById(rs.getInt("company_id"))));
			}
			logger.debug(cpList.toString());

			return cpList;

		} catch (SQLException e) {
			logger.error(e.getMessage());
			return cpList;
		}

	}

}
