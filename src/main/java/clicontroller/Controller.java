package clicontroller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import dao.CompanyDAOImpl;
import exception.ComputerNameEmptyException;
import exception.DateOrderException;
import model.Company;
import model.Computer;
import service.CompanyServiceImpl;
import service.ComputerServiceImpl;
import view.View;

/**
 * The Class CommandInterface.
 */
@Component
public class Controller {

	private static final int MAX_ARGUMENTS = 20;
	private static final int DEFAULT_COMPUTER_ID = 0;
	private static final String UNKNOWN_INPUT = "?";
	private static final String TIME_HOURS = " 00:00:00.0";
	private static Logger logger  = LoggerFactory.getLogger(CompanyDAOImpl.class);

	@Autowired
	private View view;
	
	@Autowired
	private ComputerServiceImpl cmptService;
	
	@Autowired
	private CompanyServiceImpl cpnyService;

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
			case "page":
				if (args[1] != null && args[2] != null && args[3] != null) {
					switch (args[1]) {
					case "computers":
						pageComputers(args[2], args[3]);
						break;
					case "companies":
						pageCompanies(args[2], args[3]);
						break;
					default:
						view.pageUsage();
						break;
					}
				} else {
					view.pageUsage();
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
			case "company":
				if (args[1] != null) {
					switch (args[1]) {
					case "delete":
						deleteCompany(args);
						break;
					default:
						view.companyUsage();
						break;
					}
				} else {
					view.companyUsage();
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
	 * Method to process user input by splitting keywords and removing quotes.
	 *
	 * @param usrInpt String inputed by the user
	 * @return String[] of commands
	 */
	public String[] inputSplitter(String usrInpt) {

		String[] usrCmd = new String[MAX_ARGUMENTS];
		List<String> matchList = new ArrayList<>();
		Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
		Matcher regexMatcher = regex.matcher(usrInpt);

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

		usrCmd = matchList.toArray(usrCmd);

		return usrCmd;
	}

	/**
	 * List computers.
	 */
	public void listComputers() {
		view.computersListHeader();
		List<Computer> cpList;

		cpList = cmptService.getAll();
		for (Computer comp : cpList) {
			view.computer(comp);
		}

	}

	/**
	 * List companies.
	 */
	public void listCompanies() {
		view.companiesListHeader();
		List<Company> cnyList;

		cnyList = cpnyService.getAll();
		for (Company comp : cnyList) {
			view.company(comp);
		}

	}

	/**
	 * Page computers.
	 * 
	 * @param int pageNo
	 * @param int objCount
	 */
	public void pageComputers(String pageNo, String objCount) {
		try {
			view.computersPageHeader(Integer.parseInt(pageNo), Integer.parseInt(objCount));
			List<Computer> cpList = cmptService.getPageByName(Integer.parseInt(pageNo), Integer.parseInt(objCount),"","");
			for (Computer comp : cpList) {
				view.computer(comp);
			}
			if (cpList.isEmpty()) {
				view.pageEmpty();
			}
		} catch (NumberFormatException e) {
			view.pageUsage();
		}

	}

	/**
	 * Page computers.
	 * 
	 * @param int pageNo
	 * @param int objCount
	 */
	public void pageCompanies(String pageNo, String objCount) {
		try {
			view.companyPageHeader(Integer.parseInt(pageNo), Integer.parseInt(objCount));
			List<Company> cnyList = cpnyService.getPage(Integer.parseInt(pageNo), Integer.parseInt(objCount));
			for (Company cny : cnyList) {
				view.company(cny);
			}
			if (cnyList.isEmpty()) {
				view.pageEmpty();
			}
		} catch (NumberFormatException e) {
			view.pageUsage();
		}
	}

	/**
	 * Creates the computer.
	 *
	 * @param args the args
	 */
	public void createComputer(String[] args) {
		try {
			Timestamp ts1;
			Timestamp ts2;

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

			Computer cpInsert = new Computer(DEFAULT_COMPUTER_ID, args[2], ts1, ts2,
					cpnyService.getById(Integer.parseInt(args[5])));
			try {
				cmptService.add(cpInsert);
			} catch (DateOrderException | ComputerNameEmptyException e) {
				logger.error(e.getMessage());
			}
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
	public void updateComputer(String[] args) {
		try {
			Timestamp ts1;
			Timestamp ts2;

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

			Computer cpInsert = new Computer(Integer.parseInt(args[2]), args[3], ts1, ts2,
					cpnyService.getById(Integer.parseInt(args[6])));
			try {
				cmptService.update(cpInsert);
			} catch (DateOrderException | ComputerNameEmptyException e) {
				logger.error(e.getMessage());
			}
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
	public void deleteComputer(String[] args) {
		
		try {
			boolean deletesuccess = cmptService.delete(Integer.parseInt(args[2]));
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
	 * Delete company AND all related computers.
	 *
	 * @param args the args
	 */
	private void deleteCompany(String[] args) {
		
		try {
			Company cmpny = cpnyService.getById(Integer.valueOf(args[2]));
			boolean deletesuccess = cmptService.deleteByCompany(cmpny);
			if (deletesuccess) {
				view.companyDeleteSuccess(args[2]);
			} else {
				view.companyDeleteFail(args[2]);
			}

		} catch (NumberFormatException e) {
			view.idFormat();
			view.companyDeleteUsage();
		}
		
	}

	/**
	 * Detail computer.
	 *
	 * @param args the args
	 */
	public void detailComputer(String[] args) {
		try {
			Computer compIdSearch = cmptService.detail(Integer.parseInt(args[2]));
			if (compIdSearch != null) {
				view.computer(compIdSearch);
			} else {
				view.computerDetailFail(args[2]);
			}

		} catch (NullPointerException e) {
			view.computerDetailFail(args[2]);
		} catch (NumberFormatException e) {
			view.idFormat();
			view.computerDetailUsage();
		}
	}
}
