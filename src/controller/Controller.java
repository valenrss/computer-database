package controller;

import java.sql.Timestamp;
import java.util.List;

import model.Company;
import model.Computer;
import service.CompanyServiceImpl;
import service.ComputerServiceImpl;
import view.View;

/**
 * The Class CommandInterface.
 */
public class Controller {

	private static final int DEFAULT_COMPUTER_ID = 0;
	private static final String UNKNOWN_INPUT = "?";
	private static final String TIME_HOURS = " 00:00:00.0";

	
	private View view;
	private ComputerServiceImpl cmptService;
	private CompanyServiceImpl cpnyService;

	public Controller() {
		view = new View();
		cmptService = new ComputerServiceImpl();
		cpnyService = new CompanyServiceImpl();
	}

	/**
	 * Method to process the user commands and call specific methods to execute the
	 * desired actions.
	 *
	 * @param args Commands requested by user
	 * @return boolean is the command recognized
	 */
	public void readCommand(String[] args) {

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
						view.listUsage();
						break;
					}
				} else {
					view.listUsage();
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
						view.computerUsage();
						break;
					}
				} else {
					view.computerUsage();
				}
				break;
			case "help":
				view.help();
				break;
			case "quit":
				view.quit();
				break;
			default:
				view.empty();
				break;
			}

		} else {
			view.empty();
		}

	}

	/**
	 * List computers.
	 */
	private void listComputers() {
		view.computersListHeader();
		List<Computer> cpList = cmptService.getAllComputers();
		for (Computer comp : cpList) {
			view.computer(comp);
		}
	}

	/**
	 * List companies.
	 */
	private void listCompanies() {
		view.companiesListHeader();
		List<Company> cnyList = cpnyService.getAllCompanies();
		for (Company comp : cnyList) {
			view.company(comp);
		}
	}

	/**
	 * Creates the computer.
	 *
	 * @param args the args
	 */
	private void createComputer(String[] args) {
		try {
			Timestamp ts1, ts2;

			if (args[3].equals(UNKNOWN_INPUT)) {
				ts1 = null;
			} else {
				ts1 = Timestamp.valueOf(args[3] + TIME_HOURS);
			}
			if (args[4].equals(UNKNOWN_INPUT)) {
				ts2 = null;
			} else {
				ts2 = Timestamp.valueOf(args[4] + TIME_HOURS);
			}
			if (args[5].equals(UNKNOWN_INPUT)) {
				args[5] = "1";
			}

			Computer cpInsert = new Computer(DEFAULT_COMPUTER_ID, args[2], ts1, ts2, Integer.parseInt(args[5]));
			cmptService.addComputer(cpInsert);
			view.computer(cpInsert);
			view.computerAddSuccess();
		} catch (NumberFormatException e) {
			view.companyIdFormat();
			view.computerCreateUsage();
		} catch (IllegalArgumentException e) {
			if (args[2] != null) {
				view.dateFormat();
			}
			view.computerCreateUsage();
		} catch (NullPointerException e) {
			view.computerCreateUsage();
		}
	}

	/**
	 * Update computer.
	 *
	 * @param args the args
	 */
	private void updateComputer(String[] args) {
		try {
			Timestamp ts1, ts2;

			if (args[3].equals(UNKNOWN_INPUT)) {
				ts1 = null;
			} else {
				ts1 = Timestamp.valueOf(args[3] + TIME_HOURS);
			}
			if (args[4].equals(UNKNOWN_INPUT)) {
				ts2 = null;
			} else {
				ts2 = Timestamp.valueOf(args[4] + TIME_HOURS);
			}
			if (args[5].equals(UNKNOWN_INPUT)) {
				args[5] = "1";
			}

			Computer cpInsert = new Computer(Integer.parseInt(args[2]), args[3], ts1, ts2, Integer.parseInt(args[6]));
			cmptService.updateComputer(cpInsert);
			view.computer(cpInsert);
			view.computerUpdateSuccess();
		} catch (NumberFormatException e) {
			view.idFormat();
			view.computerUpdateUsage();
		} catch (IllegalArgumentException e) {
			if (args[2] != null) {
				view.dateFormat();
			}
			view.computerUpdateUsage();
		}
	}

	/**
	 * Delete computer.
	 *
	 * @param args the args
	 */
	private void deleteComputer(String[] args) {
		try {
			boolean deletesuccess = cmptService.deleteComputer(Integer.parseInt(args[2]));
			if (deletesuccess) {
				view.computerDeleteSuccess(args[2]);
			} else {
				view.computerDeleteFail(args[2]);
			}

		} catch (NumberFormatException e) {
			view.idFormat();
			view.computerDeleteUsage();
		}

	}

	/**
	 * Detail computer.
	 *
	 * @param args the args
	 */
	private void detailComputer(String[] args) {
		try {
			Computer compIdSearch = cmptService.detailComputer(Integer.parseInt(args[2]));
			view.computer(compIdSearch);
		} catch (NumberFormatException e) {
			view.idFormat();
			view.computerDetailUsage();
		} catch (NullPointerException e) {
			view.computerDetailFail(args[2]);
		}
	}
}
