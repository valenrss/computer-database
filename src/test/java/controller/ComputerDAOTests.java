package controller;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.sql.Timestamp;

import org.junit.After;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dao.ComputerDAOImpl;
import dao.DaoFactory;
import model.Computer;

@SpringBootTest
public class ComputerDAOTests {

	Timestamp ts = Timestamp.valueOf("2012-01-01 00:00:00.0");
	Timestamp td = Timestamp.valueOf("2013-01-01 00:00:00.0");
	Computer cp = new Computer(0, "Delll Laptop", ts, td, 4);
	DaoFactory fact = DaoFactory.getInstance();
	ComputerDAOImpl cpdao = ComputerDAOImpl.getInstance(fact.getConnect());

	@Test
	public void createComputer() {
		try {
			cpdao.create(cp);
		} catch (SQLException e) {
			fail();
		}
	}


	@After
	public void deleteController() {
		
	}

}
