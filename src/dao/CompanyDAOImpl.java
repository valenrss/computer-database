package dao;

import model.Company;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

/**
 * The Class CompanyDAOImpl.
 */
public class CompanyDAOImpl extends CompanyDAO<Company> {

	/** The connect. */
	protected Connection connect = null;
	
	/** The stmt. */
	private Statement stmt;
	
	/** The rs. */
	private ResultSet rs;

	/**
	 * Instantiates a new company DAO impl.
	 *
	 * @param conn the conn
	 */
	public CompanyDAOImpl(java.sql.Connection conn) {

		super(conn);
	}

	/**
	 * Gets the company list.
	 *
	 * @return the company list
	 */
	public List<Company> getCompanyList() {

		List<Company> compList = new ArrayList<>();

		try {

			stmt = super.connect.createStatement();
			rs = stmt.executeQuery("SELECT * FROM `company`");

			while (rs.next()) {
				compList.add(new Company(rs.getInt("id"), rs.getString("name")));
			}

		} catch (SQLException e) {
			System.out.println("Request Failed ! Error : " + e);
			e.printStackTrace();
		}

		return compList;

	}

}
