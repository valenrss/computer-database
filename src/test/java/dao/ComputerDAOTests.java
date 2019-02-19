package dao;

import static org.junit.Assert.assertEquals;
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
	ComputerDAOImpl cpmpt = ComputerDAOImpl.getInstance(fact.getConnect());

	@Test
	public void createComputer() {
		try {
			Computer comp = new Computer(0,"IBZ d433",ts,td,4);
			cpmpt.create(comp);
			assertEquals(comp,cpmpt.getList().get(cpmpt.getList().size()-1));
			
		} catch (SQLException e) {
			fail();
		}
	}
	
	@Test
	public void deleteComputer() {
		
		
		try {
			Computer lastPC = cpmpt.getList().get(cpmpt.getList().size()-1);
			int lastPCid = lastPC.getId();
			
			cpmpt.delete(lastPCid);
			
			assertEquals(false,cpmpt.getList().contains(lastPC));
		} catch (SQLException e) {
			fail();
		}
		
		
	}
	
	@Test
	public void updateComputer() {
		
		try {
			Computer comp = new Computer(605,"IBZ Modded v2",ts,td,4);
			cpmpt.update(comp);
			assertEquals(true,cpmpt.getList().contains(comp));
			
		} catch (SQLException e) {
			fail();
		}
		
	}
	
	@Test
	public void findComputer() {
		try {
			assertEquals(4,cpmpt.find(4).getId());
		} catch (SQLException e) {
			fail();
		}
	}
	
	@Test
	public void getPage() {
		try {
			assertEquals(5,cpmpt.getPage(3,5).size());
			} catch (SQLException e) {
			fail();
		}
		
	}


	@After
	public void deleteController() {
		
	}

}
