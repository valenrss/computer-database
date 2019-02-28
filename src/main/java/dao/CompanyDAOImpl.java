package dao;

import model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

public class CompanyDAOImpl implements CompanyDAO {

	private static final String SQL_GET_BY_ID = "SELECT `id`,`name` FROM `company` WHERE id = ?";
	private static final String SQL_LIST_ALL = "SELECT `id`,`name` FROM `company`";
	private static final String SQL_PAGE = "SELECT `id`,`name` FROM `company` WHERE id >= ? AND id < ?";

	private Connection connect = null;
	private static CompanyDAOImpl companyDAOImpl;
	private Logger logger;

	/**
	 * Instantiates a new company DAO impl.
	 *
	 * @param conn the conn
	 */
	private CompanyDAOImpl(Connection conn) {

		connect = conn;
	}

	/**
	 * Gets the company list.
	 *
	 * @return the company list
	 */
	@Override
	public List<Company> getList() {

		List<Company> compList = new ArrayList<>();

		PreparedStatement prep;
		try {
			prep = connect.prepareStatement(SQL_LIST_ALL);
			prep.executeQuery();
			ResultSet rs = prep.getResultSet();

			while (rs.next()) {
				compList.add(new Company(rs.getInt("id"), rs.getString("name")));
			}
		} catch (SQLException e) {
			logger.debug(e.toString());
		}

		return compList;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.CompanyDAO#getPage(int, int)
	 */
	@Override
	public List<Company> getPage(int pageNo, int objCount) {
		List<Company> cnyList = new ArrayList<>();

		int minId = pageNo * objCount - objCount;
		int maxId = minId + objCount;

		try {
			PreparedStatement prep1 = connect.prepareStatement(SQL_PAGE);

			prep1.setInt(1, minId);
			prep1.setInt(2, maxId);

			prep1.executeQuery();

			ResultSet rs = prep1.getResultSet();

			while (rs.next()) {
				cnyList.add(new Company(rs.getInt("id"), rs.getString("name")));
			}
		} catch (SQLException e) {
			logger.debug(e.toString());
		}

		return cnyList;
	}

	@Override
	public Company getById(int id) {

		Company cmp;

		try {
			if (id != 0) {
				PreparedStatement prep2 = connect.prepareStatement(SQL_GET_BY_ID);
				prep2.setString(1, id + "");
				prep2.executeQuery();
				ResultSet rs = prep2.getResultSet();

				if (rs.next()) {
					cmp = new Company(rs.getInt("id"), rs.getString("name"));
					return cmp;
				} else {
					return new Company(0, "-");
				}

			} else {
				return new Company(0, "-");
			}
		} catch (SQLException e) {
			logger.debug(e.toString());
			return new Company(0, "-");
		}

	}

	/**
	 * 
	 * @param conn
	 * @return instange of CompanyDAO
	 */
	public static CompanyDAOImpl getInstance(Connection conn) {
		if (companyDAOImpl == null) {
			companyDAOImpl = new CompanyDAOImpl(conn);
		}
		return companyDAOImpl;
	}

}
