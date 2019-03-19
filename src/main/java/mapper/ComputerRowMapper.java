package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import model.Company;
import model.Computer;

@Component
public class ComputerRowMapper  implements RowMapper<Computer>  {

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {

		return new Computer(rs.getInt("computer.id"), rs.getString("computer.name"), rs.getTimestamp("introduced"),
				rs.getTimestamp("discontinued"), new Company(rs.getInt("company_id"),rs.getString("company.name")));
	}

}
