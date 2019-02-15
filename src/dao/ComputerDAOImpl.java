package dao;

import model.Computer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ComputerDAOImpl.
 */
public class ComputerDAOImpl implements ComputerDAO {

	/** The stmt. */
	private Statement stmt;

	/** The rs. */
	private ResultSet rs;
	
	/** The connenct. */
	private Connection connect = null;

	/**
	 * Instantiates a new computer DAO impl.
	 *
	 * @param conn the conn
	 */
	public ComputerDAOImpl(Connection conn) {

		connect = conn;
	}

	/**
	 * Gets the computer list.
	 *
	 * @return the computer list
	 */
	public List<Computer> getComputerList() {

		List<Computer> cpList = new ArrayList<>();

		try {

			stmt = connect.createStatement();
			rs = stmt.executeQuery("SELECT * FROM `computer`");

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

			PreparedStatement prep1 = connect.prepareStatement(
					"INSERT INTO `computer` (`name`,`introduced`,`discontinued`, `company_id`) VALUES (?,?,?,?)");

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
			PreparedStatement prep1 = connect.prepareStatement("DELETE FROM `computer` WHERE `id` = ?");
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

			PreparedStatement prep1 = connect.prepareStatement(
					"UPDATE `computer` SET `name` = ?, `introduced` = ?, `discontinued` = ?, `company_id` = ? WHERE `id` = ?");

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
			ResultSet result = connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM `computer` WHERE `id` = " + id);
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
	 * @see dao.ComputerDAO#getComputerPage(int, int)
	 */
	@Override
	public List<Computer> getComputerPage(int pageNo, int objCount) {
		List<Computer> cpList = new ArrayList<>();

		try {
			int minId = pageNo * objCount;
			int maxId = minId + objCount;

			PreparedStatement prep1 = connect
					.prepareStatement("SELECT * FROM `computer` WHERE id > ? AND id < ?");

			prep1.setInt(1, minId);
			prep1.setInt(2, maxId);

			prep1.executeUpdate();

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

}
