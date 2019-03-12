package dao;

import model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDAOImpl extends Dao implements CompanyDAO {

	private static final String SQL_GET_BY_ID = "SELECT `id`,`name` FROM `company` WHERE id = ?";
	private static final String SQL_LIST_ALL = "SELECT `id`,`name` FROM `company`";
	private static final String SQL_PAGE = "SELECT `id`,`name` FROM `company` WHERE id >= ? AND id < ?";

	private static Logger logger  = LoggerFactory.getLogger(CompanyDAOImpl.class);
	

	/**
	 * Instantiates a new company DAO impl.
	 *
	 * @param conn the Connection
	 */
	private CompanyDAOImpl() {
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
		try (Connection connect = connect()) {
			prep = connect.prepareStatement(SQL_LIST_ALL);
			prep.executeQuery();
			ResultSet rs = prep.getResultSet();

			while (rs.next()) {
				compList.add(new Company(rs.getInt("id"), rs.getString("name")));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
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

		try (Connection connect = connect()) {
			PreparedStatement prep1 = connect.prepareStatement(SQL_PAGE);

			prep1.setInt(1, minId);
			prep1.setInt(2, maxId);

			prep1.executeQuery();

			ResultSet rs = prep1.getResultSet();

			while (rs.next()) {
				cnyList.add(new Company(rs.getInt("id"), rs.getString("name")));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return cnyList;
	}

	@Override
	public Company getById(int id) {

		Company cmp;

		try (Connection connect = connect()) {
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
			logger.error(e.getMessage());
			return new Company(0, "-");
		}

	}

}
