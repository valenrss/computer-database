import java.util.List;

import dao.*;
import model.*;

public class Main {

	public static void main(String[] args) {
		
		;
		
		DaoFactory fact = new DaoFactory();
		ComputerDAO compdao = new ComputerDAO(fact.getConnect());
		
		List<Computer> cpList  = compdao.getComputerList();
		
		Computer compIdSearch = compdao.find(36);
		
		System.out.println("\nId : "+compIdSearch.getId() + 
				"\nName : "+compIdSearch.getName() + 
				"\nDate Introduced : "+compIdSearch.getDateIntroduced() + 
				"\nDate Discontinued : "+compIdSearch.getDateDiscontinued() + 
				"\nCompany Id : "+compIdSearch.getCompanyId() 
				);
		
		Computer cpFromList = cpList.get(36);
		
		System.out.println("\nId : "+cpFromList.getId() + 
				"\nName : "+cpFromList.getName() + 
				"\nDate Introduced : "+cpFromList.getDateIntroduced() + 
				"\nDate Discontinued : "+cpFromList.getDateDiscontinued() + 
				"\nCompany Id : "+cpFromList.getCompanyId() 
				);

		Computer cpInsertTest = new Computer(0,"Oric Atmos",null,null,2);
		
		compdao.create(cpInsertTest);
		
	}

}
