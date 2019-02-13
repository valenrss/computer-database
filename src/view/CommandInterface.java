package view;

import java.sql.Timestamp;
import java.util.List;

import dao.CompanyDAOImpl;
import dao.ComputerDAOImpl;
import dao.DaoFactory;
import model.Company;
import model.Computer;

/**
 * The Class CommandInterface.
 */
public class CommandInterface {


	private static final int DEFAULT_COMPUTER_ID = 0;
	private static final String UNKNOWN_INPUT = "?";
	private static final String TIME_HOURS = " 00:00:00.0";

	private DaoFactory fact = new DaoFactory();
	private ComputerDAOImpl comptdao = new ComputerDAOImpl(fact.getConnect());
	private CompanyDAOImpl cnydao = new CompanyDAOImpl(fact.getConnect());

	public CommandInterface() {
		System.out.flush();
		System.out.println(" ____     ____    ____      \n" + "/\\  _`\\  /\\  _`\\ /\\  _`\\    \n"
				+ "\\ \\ \\/\\_\\\\ \\ \\/\\ \\ \\ \\L\\ \\  \n" + " \\ \\ \\/_/_\\ \\ \\ \\ \\ \\  _ <' \n"
				+ "  \\ \\ \\L\\ \\\\ \\ \\_\\ \\ \\ \\L\\ \\\n" + "   \\ \\____/ \\ \\____/\\ \\____/\n"
				+ "    \\/___/   \\/___/  \\/___/ \n" + "                            \n");
		System.out.println("Welcome to the Computer Database Command-Line Interface 2000 Ultimate Reloaded");
		System.out.println("Type 'help' to get started...\n");
	}
	
	/**
	 * Method to process the user commands and call specific
	 * methods to execute the desired actions.
	 *
	 * @param args Commands requested by user
	 * @return boolean is the command recognized
	 */
	public boolean readCommand(String[] args) {

		if (args[0] != null) {
			switch (args[0]) {
			case "list":
				if (args[1] != null) {
					switch (args[1]) {
					case "computers":
						listComputers();
						break;
					case "companies":
						listCompanies();
						break;
					default:
						System.out.println("Usage : list <computers|companies>");
						break;
					}
				} else {
					System.out.println("Usage : list <computers|companies>");
				}
				break;
			case "computer":
				if (args[1] != null) {
					switch (args[1]) {
					case "create":
						createComputer(args);
						break;
					case "update":
						updateComputer(args);
						break;
					case "delete":
						deleteComputer(args);
						break;
					case "detail":
						detailComputer(args);
						break;
					default:
						System.out.println("Usage : computer <create|update|delete|detail>");
						break;
					}
				} else {
					System.out.println("Usage : computer <create|update|delete|detail>");
				}
				break;
			case "help":
				System.out.println("list <computers|companies>              ---  Display all computers/companies");
				System.out.println("computer <create|update|delete|detail>  ---  Update the computer table\n");
				break;
			default:
				System.out.println("Please enter a command.\n");
				break;
			}

			return true;

		} else {
			System.out.println("Please enter a command.\n");
			return false;
		}

	}

	/**
	 * List computers.
	 */
	private void listComputers() {
		System.out.println("\nComputers list :\n");
		List<Computer> cpList = comptdao.getComputerList();
		for (Computer comp : cpList) {
			System.out.println(comp.toString());
		}
	}
	
	/**
	 * List companies.
	 */
	private void listCompanies() {
		System.out.println("\nCompanies list :\n");
		List<Company> cnyList = cnydao.getCompanyList();
		for (Company comp : cnyList) {
			System.out.println(comp.toString());
		}
	}
	
	/**
	 * Creates the computer.
	 *
	 * @param args the args
	 */
	private void createComputer(String[] args) {
		try {
			Timestamp ts1,ts2;
			
			if (args[3].equals(UNKNOWN_INPUT)) {
				ts1 = null;
			}else {
				ts1 = Timestamp.valueOf(args[3] + TIME_HOURS);
			}
			if (args[4].equals(UNKNOWN_INPUT)) {
				ts2 = null;
			}else {
				ts2 = Timestamp.valueOf(args[4] + TIME_HOURS);
			}
			if (args[5].equals(UNKNOWN_INPUT)) {
				args[5] = "1";
			}

			Computer cpInsert = new Computer(DEFAULT_COMPUTER_ID, args[2], ts1, ts2, Integer.parseInt(args[5]));
			comptdao.create(cpInsert);
			System.out.println(cpInsert.toString());
			System.out.println("Computer sucessfully added.");
		} catch (NumberFormatException e) {
			System.out.println("Company ID must be a number.");
			System.out.println(
					"Usage : computer create <name> <introduction date> <discontinuation date> <company ID>");
		} catch (IllegalArgumentException e) {
			if (args[2] != null) {
				System.out.println("date must be of format : yyyy-[m]m-[d]d");
			}
			System.out.println(
					"Usage : computer create <name> <introduction date> <discontinuation date> <company ID>");
		}
	}
	
	/**
	 * Update computer.
	 *
	 * @param args the args
	 */
	private void updateComputer(String[] args){
		try {
			Timestamp ts1 = Timestamp.valueOf(args[4] + TIME_HOURS);
			Timestamp ts2 = Timestamp.valueOf(args[5] + TIME_HOURS);
			Computer cpInsert = new Computer(Integer.parseInt(args[2]), args[3], ts1, ts2,
					Integer.parseInt(args[6]));
			comptdao.update(cpInsert);
			System.out.println(cpInsert.toString());
			System.out.println("Computer sucessfully updated.");
		} catch (NumberFormatException e) {
			System.out.println("ID must be a number.");
			System.out.println(
					"Usage : computer update <computer id> <name> <introduction date> <discontinuation date> <company ID>");
		} catch (IllegalArgumentException e) {
			if (args[2] != null) {
				System.out.println("date must be of format : yyyy-[m]m-[d]d");
			}
			System.out.println(
					"Usage : computer update <computer id> <name> <introduction date> <discontinuation date> <company ID>");
		}
	}
	
	/**
	 * Delete computer.
	 *
	 * @param args the args
	 */
	private void deleteComputer(String[] args) {
		try {
			boolean deletesuccess = comptdao.delete(Integer.parseInt(args[2]));
			if (deletesuccess) {
				System.out.println("Computer ID " + args[2] + " sucessfully deleted.");
			} else {
				System.out.println("Could not find computer ID " + args[2]);
			}

		} catch (NumberFormatException e) {
			System.out.println("Computer ID must be a number.");
			System.out.println("Usage : computer update <ID>");
		}

	}
	
	/**
	 * Detail computer.
	 *
	 * @param args the args
	 */
	private void detailComputer(String[] args) {
		try {
			Computer compIdSearch = comptdao.find(Integer.parseInt(args[2]));
			System.out.println(compIdSearch);
		} catch (NumberFormatException e) {
			System.out.println("Computer ID must be a number.");
			System.out.println("Usage : computer detail <ID>");
		} catch (NullPointerException e) {
			System.out.println("Could not find computer ID " + args[2]);
		}
	}
}
