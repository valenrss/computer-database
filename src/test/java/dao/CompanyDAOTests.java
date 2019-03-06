package dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;



public class CompanyDAOTests {

	private DaoFactory fact;
	private CompanyDAOImpl cpdao;

	@Before
	public void init() {
		fact = DaoFactory.getInstance();
		cpdao = CompanyDAOImpl.getInstance(fact.getConnect());
	}
		
		
	
	@Test
	public void page() {
		assertEquals(5,cpdao.getPage(3,5).size());
		
	}

}
