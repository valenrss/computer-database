package view;

import java.sql.Timestamp;
import java.util.Arrays;
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
		
		if(args[0]!=null) {	
			switch (args[0]) {
				case "list" :
					if (args[1]!=null) {
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
					if (args[1]!=null) {
						switch (args[1]) {
						case "create" :						
							try {
								Timestamp ts1 = Timestamp.valueOf(args[3]);
								Timestamp ts2 = Timestamp.valueOf(args[4]);
								Computer cpInsert = new Computer(0,args[2],ts1,ts2,Integer.parseInt(args[5]));
								compdao.create(cpInsert);
								System.out.println(cpInsert.toString());
								System.out.println("Computer sucessfully added.");
							}catch(NumberFormatException e) {
								System.out.println("Company ID must be a number.");
								System.out.println("Usage : computer create <name> <introduction date> <discontinuation date> <company ID>");
							}
							catch(IllegalArgumentException e) {
								if (args[2]!=null) {
									System.out.println("date must be of format : yyyy-[m]m-[d]d hh:mm:ss");
								}
								System.out.println("Usage : computer create <name> <introduction date> <discontinuation date> <company ID>");
							}
							
							
							break;
						case "update" :
							break;
						case "delete" :
							try {
								compdao.delete(Integer.parseInt(args[2]));
							}catch(NumberFormatException e){
								System.out.println("Computer ID must be a number.");
								System.out.println("Usage : computer update <ID>");
							}
							
							break;
						case "detail" :
							try {
								List<Computer> cpList  = compdao.getComputerList();
								Computer compIdSearch = compdao.find(Integer.parseInt(args[2]));
								System.out.println(compIdSearch.toString());
							}catch(NumberFormatException e) {
								System.out.println("Computer ID must be a number.");
								System.out.println("Usage : computer detail <ID>");
							}catch(NullPointerException e) {
								System.out.println("Could not find computer ID "+args[2]);
							}
							break;
						default : 
							System.out.println("Usage : computer <create|update|delete|detail>");
							break;
						}
					}else {
						System.out.println("Usage : computer <create|update|delete|detail>");
					}
					break;
				case "help" :
					System.out.println("list <computers|companies>              ---  Display all computers/companies");
					System.out.println("computer <create|update|delete|detail>  ---  Update the computer table\n");
					break;
				default :
					System.out.println("Please enter a command.\n");
					break;
			}
			
		return true;
		
		}else {
			System.out.println("Please enter a command.\n");
			return false;
		}
		
	}

}
