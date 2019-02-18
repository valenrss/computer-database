package view;

import model.Company;
import model.Computer;


public class View {

	public View() {
		System.out.flush();
		System.out.println(" ____     ____    ____      \n" + "/\\  _`\\  /\\  _`\\ /\\  _`\\    \n"
				+ "\\ \\ \\/\\_\\\\ \\ \\/\\ \\ \\ \\L\\ \\  \n" + " \\ \\ \\/_/_\\ \\ \\ \\ \\ \\  _ <' \n"
				+ "  \\ \\ \\L\\ \\\\ \\ \\_\\ \\ \\ \\L\\ \\\n" + "   \\ \\____/ \\ \\____/\\ \\____/\n"
				+ "    \\/___/   \\/___/  \\/___/ \n" + "                            \n");
		System.out.println("Welcome to the Computer Database Command-Line Interface 2000 Ultimate Reloaded");
		System.out.println("Type 'help' to get started...\n");
	}

	/**
	 * Computer.
	 *
	 * @param cp the cp
	 */
	public void computer(Computer cp) {
		System.out.println(cp);
	}

	/**
	 * List usage.
	 */
	public void listUsage() {
		System.out.println("Usage : list <computers|companies>");
	}

	/**
	 * Computer usage.
	 */
	public void computerUsage() {
		System.out.println("Usage : computer <create|update|delete|detail>");
	}

	/**
	 * Help.
	 */
	public void help() {
		System.out.println("list <computers|companies>              ---  Display all computers/companies");
		System.out.println("computer <create|update|delete|detail>  ---  Update the computer table\n");
	}

	/**
	 * Quit.
	 */
	public void quit() {
		System.out.println("Goodbye !");

	}

	/**
	 * Empty.
	 */
	public void empty() {
		System.out.println("Please enter a command.\n");

	}

	/**
	 * Company.
	 *
	 * @param comp the comp
	 */
	public void company(Company comp) {
		System.out.println(comp);

	}

	/**
	 * Computers list header.
	 */
	public void computersListHeader() {
		System.out.println("\nComputers list :\n");

	}

	/**
	 * Companies list header.
	 */
	public void companiesListHeader() {
		System.out.println("\nCompanies list :\n");

	}

	/**
	 * Computer add success.
	 */
	public void computerAddSuccess() {
		System.out.println("Computer sucessfully added.");

	}

	/**
	 * Company id format.
	 */
	public void companyIdFormat() {
		System.out.println("Company ID must be a number.");

	}

	/**
	 * Computer create usage.
	 */
	public void computerCreateUsage() {
		System.out.println("Usage : computer create <name> <introduction date> <discontinuation date> <company ID>");

	}

	/**
	 * Date format.
	 */
	public void dateFormat() {
		System.out.println("date must be of format : yyyy-[m]m-[d]d");

	}

	/**
	 * Computer update success.
	 */
	public void computerUpdateSuccess() {
		System.out.println("Computer sucessfully updated.");

	}

	/**
	 * Id format.
	 */
	public void idFormat() {
		System.out.println("ID must be a number.");

	}

	/**
	 * Computer update usage.
	 */
	public void computerUpdateUsage() {
		System.out.println(
				"Usage : computer update <computer id> <name> <introduction date> <discontinuation date> <company ID>");

	}

	/**
	 * Computer delete success.
	 *
	 * @param string the string
	 */
	public void computerDeleteSuccess(String string) {
		System.out.println("Computer ID " + string + " sucessfully deleted.");

	}

	/**
	 * Computer delete fail.
	 *
	 * @param string the string
	 */
	public void computerDeleteFail(String string) {
		System.out.println("Could not find computer ID " + string);

	}

	/**
	 * Computer delete usage.
	 */
	public void computerDeleteUsage() {
		System.out.println("Usage : computer delete <ID>");

	}

	/**
	 * Computer detail usage.
	 */
	public void computerDetailUsage() {
		System.out.println("Usage : computer detail <ID>");

	}

	/**
	 * Computer detail fail.
	 *
	 * @param string the string
	 */
	public void computerDetailFail(String string) {
		System.out.println("Could not find computer ID " + string);

	}

	public void pageUsage() {
		System.out.println("Usage : page <computers|companies> <page number> <objects per page>");
		
	}

	public void computersPageHeader(int pageNo, int objCount) {
		System.out.println("Computers at page "+pageNo+" ("+objCount+" computers per page)");
		
	}

	public void companyPageHeader(int pageNo, int objCount) {
		System.out.println("Companies at page "+pageNo+" ("+objCount+" companies per page)");		
	}

}
