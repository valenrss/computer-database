package dao;

import model.Computer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComputerDAOImpl implements ComputerDAO {

	private static final String SQL_FIND_BY_ID = "SELECT * FROM `computer` WHERE `id` = ?";
	private static final String SQL_GETLIST = "SELECT `id`,`name`,`introduced`,`discontinued`, `company_id` FROM `computer`";
	private static final String SQL_PAGE = "SELECT `id`,`name`,`introduced`,`discontinued`, `company_id` FROM `computer` WHERE id >= ? AND id <= ?";
	private static final String SQL_UPDATE = "UPDATE `computer` SET `name` = ?, `introduced` = ?, `discontinued` = ?, `company_id` = ? WHERE `id` = ?";
	private static final String SQL_DELETE_ID = "DELETE FROM `computer` WHERE `id` = ?";
	private static final String SQL_CREATE = "INSERT INTO `computer` (`name`,`introduced`,`discontinued`, `company_id`) VALUES (?,?,?,?)";
	private ResultSet rs;
	private Connection connect = null;
	
	private static ComputerDAOImpl computerDAOImpl;

	private ComputerDAOImpl(Connection conn) {

		connect = conn;
	}

	/**
	 * Gets the computer list.
	 *
	 * @return the computer list
	 */
	public List<Computer> getList() {

		List<Computer> cpList = new ArrayList<>();

		try {

			PreparedStatement prep = connect.prepareStatement(SQL_GETLIST);
			prep.executeQuery();			
			ResultSet rs = prep.getResultSet();
			
			while (rs.next()) {
				cpList.add(new Computer(rs.getInt("id"), rs.getString("name"), rs.getTimestamp("introduced"),
						rs.getTimestamp("discontinued"), rs.getInt("company_id")));
			}

		} catch (SQLException e) {
			System.out.println("Request Failed ! Error : " + e);
			e.printStackTrace();
		}

		return cpList;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ComputerDAO#create(java.lang.Object)
	 */
	@Override
	public boolean create(Computer comp) {

		try {

			PreparedStatement prep1 = connect.prepareStatement(SQL_CREATE);

			prep1.setString(1, comp.getName());
			prep1.setTimestamp(2, comp.getDateIntroduced());
			prep1.setTimestamp(3, comp.getDateDiscontinued());
			prep1.setInt(4, comp.getCompanyId());

			prep1.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
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
			PreparedStatement prep1 = connect.prepareStatement(SQL_DELETE_ID);
			prep1.setInt(1, id);
			success = prep1.executeUpdate();
			if (success == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println("Could not execute command.");
			e.printStackTrace();
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ComputerDAO#update(java.lang.Object)
	 */
	@Override
	public boolean update(Computer comp) {
		try {

			PreparedStatement prep1 = connect.prepareStatement(SQL_UPDATE);

			prep1.setString(1, comp.getName());
			prep1.setTimestamp(2, comp.getDateIntroduced());
			prep1.setTimestamp(3, comp.getDateDiscontinued());
			prep1.setInt(4, comp.getCompanyId());
			prep1.setInt(5, comp.getId());

			prep1.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
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
			PreparedStatement prep= connect.prepareStatement(SQL_FIND_BY_ID);
			prep.setInt(1, id);
			prep.executeQuery();
			ResultSet result = prep.getResultSet();
			
			if (result.first())
				comp = new Computer(id, result.getString("name"), result.getTimestamp("introduced"),
						result.getTimestamp("discontinued"), result.getInt("company_id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.ComputerDAO#getPage(int, int)
	 */
	@Override
	public List<Computer> getPage(int pageNo, int objCount) {
		List<Computer> cpList = new ArrayList<>();

		try {
			int minId = pageNo * objCount - objCount;
			int maxId = minId + objCount;

			PreparedStatement prep1 = connect.prepareStatement(SQL_PAGE);

			prep1.setInt(1, minId);
			prep1.setInt(2, maxId);

			prep1.executeQuery();
			
			rs = prep1.getResultSet();

			while (rs.next()) {
				cpList.add(new Computer(rs.getInt("id"), rs.getString("name"), rs.getTimestamp("introduced"),
						rs.getTimestamp("discontinued"), rs.getInt("company_id")));
			}

		} catch (SQLException e) {
			System.out.println("Request Failed ! Error : " + e);
			e.printStackTrace();
		}

		return cpList;

	}

	public static ComputerDAOImpl getInstance(Connection conn) {
		if (computerDAOImpl == null) {
			computerDAOImpl = new ComputerDAOImpl(conn);
		}
		return computerDAOImpl;
	}


}
