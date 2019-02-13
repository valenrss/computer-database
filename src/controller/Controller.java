package controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import view.CommandInterface;

/**
 * The Class Controller.
 */
public class Controller {

	/** The Constant MAX_ARGUMENTS. */
	private static final int MAX_ARGUMENTS = 20;

	/**
	 * The main method that loops after every command until quit
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		String UserInput = "";
		String[] UserCommands = { "0" };
		CommandInterface cmdint = new CommandInterface();
		Scanner sc = new Scanner(System.in);

		while (!UserCommands[0].equals("quit")) {

			UserInput = sc.nextLine();

			UserCommands = inputSplitter(UserInput);

			cmdint.readCommand(UserCommands);

		}
		sc.close();

	}

	/**
	 * Method to process user input by splitting keywords and removing quotes.
	 *
	 * @param usrInpt String inputed by the user
	 * @return String[] of commands
	 */
	public static String[] inputSplitter(String usrInpt) {

		String[] usrCmd = new String[MAX_ARGUMENTS];
		List<String> matchList = new ArrayList<String>();
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

}
