import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.*;
import model.*;
import view.CommandInterface;

public class Main {

	public static void main(String[] args) {
		
		System.out.flush();
		
		String UserInput = "";
		String[] UserCommands = {"0"};
		CommandInterface cmdint = new CommandInterface();
		List<String> matchList = new ArrayList<String>();
		
		System.out.println(" ____     ____    ____      \n" + 
				"/\\  _`\\  /\\  _`\\ /\\  _`\\    \n" + 
				"\\ \\ \\/\\_\\\\ \\ \\/\\ \\ \\ \\L\\ \\  \n" + 
				" \\ \\ \\/_/_\\ \\ \\ \\ \\ \\  _ <' \n" + 
				"  \\ \\ \\L\\ \\\\ \\ \\_\\ \\ \\ \\L\\ \\\n" + 
				"   \\ \\____/ \\ \\____/\\ \\____/\n" + 
				"    \\/___/   \\/___/  \\/___/ \n" + 
				"                            \n");
		System.out.println("Welcome to the Computer Database Command-Line Interface 2000 Ultimate Reloaded");
		System.out.println("Type 'help' to get started...\n");

		
		while(UserCommands[0]!="quit") {
			
			UserCommands = null;
			UserCommands = new String[20];
			cmdint = new CommandInterface();
			matchList.clear();
			
			Scanner sc = new Scanner(System.in);
			UserInput = sc.nextLine();
		    
			
		    Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
		    Matcher regexMatcher = regex.matcher(UserInput);
		    while (regexMatcher.find()) {
		        if (regexMatcher.group(1) != null) {
		            // Add double-quoted string without the quotes
		            matchList.add(regexMatcher.group(1));
		        } else if (regexMatcher.group(2) != null) {
		            // Add single-quoted string without the quotes
		            matchList.add(regexMatcher.group(2));
		        } else {
		            // Add unquoted word
		            matchList.add(regexMatcher.group());
		        }
		    }

		    UserCommands = matchList.toArray(UserCommands);
		    
		    cmdint.readCommand(UserCommands);
			
		}
		//computer create "macbook pro" "2012-02-09 00:00:00.000" "2017-02-09 00:00:00.000" 13

		
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
