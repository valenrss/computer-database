package view;

import java.util.List;

import dao.CompanyDAO;
import dao.ComputerDAO;
import dao.DaoFactory;
import model.Company;
import model.Computer;

public class CommandInterface {
	
	private DaoFactory fact = new DaoFactory();
	private ComputerDAO compdao = new ComputerDAO(fact.getConnect());
	private CompanyDAO cnydao = new CompanyDAO(fact.getConnect());

	public CommandInterface() {
		
	}
	
	public boolean readCommand (String[] args) {
		
		if(args.length > 0) {
			
			switch (args[0]) {
				case "list" :
					if (args.length > 1) {
						switch (args[1]) {
							case "computers" :
								System.out.println("\nComputers list :\n");
								List<Computer> cpList  = compdao.getComputerList();
								for(Computer comp : cpList) {
									System.out.println(comp.toString());
								}
								break;
							case "companies" :
								System.out.println("\nCompanies list :\n");
								List<Company> cnyList  = cnydao.getList();
								for(Company comp : cnyList) {
									System.out.println(comp.toString());
								}
								break;
							default :
								System.out.println("Usage : list <computers|companies>");
								break;
							}
					}else {
						System.out.println("Usage : list <computers|companies>");
					}
					break;
				case "computer":
					switch (args[1]) {
						case "create" :
							break;
						case "update" :
							break;
						case "delete" :
							break;
						case "detail" :
							break;
						default : 
							System.out.println("Usage : computer <create|update|delete|detail>");
							break;
					}
			}
			
		return true;
		
		}else {
			System.out.println("Please enter a command.\n");
			return false;
		}
		
	}

}
