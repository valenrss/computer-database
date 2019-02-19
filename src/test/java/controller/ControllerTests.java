package controller;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ControllerTests {
	
	Controller controller;
	
	@Before
	public void createController() {
		controller = Mockito.mock(Controller.class);
	}

	@Test
	public void inputSplitter() {

		assertEquals("tes",    Controller.inputSplitter("tes t jesuis")[0]);
		assertEquals("t",      Controller.inputSplitter("tes t jesuis")[1]);
		assertEquals("jesuis", Controller.inputSplitter("tes t jesuis")[2]);

	}
	
	@Test
	public void addCp() {
		
		String[] string = {"computer","create","Dell Detravaille","?","?","3"};
		
		controller.createComputer(string);
	}
	
	@After
	public void deleteController() {
		controller = null;
	}

}
