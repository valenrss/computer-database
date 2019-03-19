package dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;



public class CompanyDAOTests {



	private CompanyDAOImpl cpdao;

	@Before
	public void init() {
	}
		
		
	
	@Test
	public void page() {
		assertEquals(5,cpdao.getPage(3,5).size());
		
	}

}
