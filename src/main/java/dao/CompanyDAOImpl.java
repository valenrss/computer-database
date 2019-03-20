package dao;

import model.Company;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.zaxxer.hikari.HikariDataSource;
import mapper.CompanyRowMapper;

@Repository
public class CompanyDAOImpl implements CompanyDAO {

	private static final String SQL_GET_BY_ID = "SELECT `id`,`name` FROM `company` WHERE id = ?";
	private static final String SQL_LIST_ALL = "SELECT `id`,`name` FROM `company`";
	private static final String SQL_PAGE = "SELECT `id`,`name` FROM `company` WHERE id >= ? AND id < ?";
	private static final String SQL_DELETE_FROM_COMPANY_WHERE_ID = "DELETE FROM  company  WHERE  id  = ?";
	
	@Autowired
	private CompanyRowMapper companyRowMapper;
	
	private JdbcTemplate jdbcTemplate;
	

	
	/**
	 * Instantiates a new company DAO impl.
	 *
	 * @param conn the Connection
	 */
	
	@Autowired
    public void setDataSource(HikariDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


	/**
	 * Gets the company list.
	 *
	 * @return the company list
	 */
	@Override
	public List<Company> getList() {

		return jdbcTemplate.query(SQL_LIST_ALL,companyRowMapper);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.CompanyDAO#getPage(int, int)
	 */
	@Override
	public List<Company> getPage(int pageNo, int objCount) {
		
		int minId = pageNo * objCount - objCount;
		int maxId = minId + objCount;
			
		return jdbcTemplate.query(SQL_PAGE,companyRowMapper,minId,maxId);

	}

	@Override
	public Company getById(int id) {

		return jdbcTemplate.query(SQL_GET_BY_ID,companyRowMapper,id).get(0);

	}
	
	@Override
	public boolean deleteById(Company company) {
		
		return jdbcTemplate.update(SQL_DELETE_FROM_COMPANY_WHERE_ID,company.getId()) > 0;
		
	}

}
