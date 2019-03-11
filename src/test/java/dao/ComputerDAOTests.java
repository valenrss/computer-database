package dao;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.After;
import org.junit.Test;

import dao.ComputerDAOImpl;
import dao.DaoFactory;
import model.Company;
import model.Computer;


public class ComputerDAOTests {

	Date ts = Timestamp.valueOf("2012-01-01 00:00:00.0");
	Date td = Timestamp.valueOf("2013-01-01 00:00:00.0");
	Computer cp = new Computer(0, "Delll Laptop", ts, td, new Company(1,"Apple inc."));
	DaoFactory fact;
	ComputerDAOImpl cpmpt;

	@Test
	public void createComputer() {
		Computer comp = new Computer(0, "Delll Laptop", ts, td, new Company(1,"Apple inc."));
		cpmpt.create(comp);
		assertEquals(comp,cpmpt.getList().get(cpmpt.getList().size()-1));
	}
	
	@Test
	public void deleteComputer() {
		
		
		Computer lastPC = cpmpt.getList().get(cpmpt.getList().size()-1);
		int lastPCid = lastPC.getId();
		
		cpmpt.delete(lastPCid);
		
		assertEquals(false,cpmpt.getList().contains(lastPC));
		
		
	}
	
	@Test
	public void updateComputer() {
		
		Computer comp = new Computer(0, "IBM Laptop", ts, td, new Company(3,"IBM"));
		cpmpt.update(comp);
		assertEquals(true,cpmpt.getList().contains(comp));
		
	}
	
	@Test
	public void findComputer() {
		assertEquals(4,cpmpt.find(4).getId());
	}
	
	@Test
	public void getPage() {
		assertEquals(5,cpmpt.getPage(3,5).size());
		
	}


	@After
	public void deleteController() {
		
	}

}
