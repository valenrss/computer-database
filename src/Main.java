import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.*;
import model.*;
import view.CommandInterface;

public class Main {

	public static void main(String[] args) {
		
		System.out.flush();
		
		String UserInput = "";
		String[] UserCommands = {"0"};
		CommandInterface cmdint = new CommandInterface();
		
		System.out.println("Welcome to the Computer Database Command-Line Interface");
		System.out.println("Type 'help' to get started...\n");

		
		while(UserCommands[0]!="quit") {
			
			UserCommands = null;
			cmdint = new CommandInterface();
			
			Scanner sc = new Scanner(System.in);
			UserInput = sc.nextLine();
		    UserCommands = UserInput.split(" ");
		    
		    cmdint.readCommand(UserCommands);
			
		}
		

		
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
		
		Timestamp ts1 = Timestamp.valueOf("2009-10-20 00:00:00.000");
		Timestamp ts2 = new Timestamp(System.currentTimeMillis());
		

		Computer cpInsertTest = new Computer(0,"HP pavillon",ts1,ts2,10);
		
		
		compdao.create(cpInsertTest);
		
		CompanyDAO testcompany = new CompanyDAO(fact.getConnect());
		
		
		System.out.println(testcompany.getList().get(6).toString());
		
	}

}
