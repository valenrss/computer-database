package main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.SpringConfig;
import controller.Controller;

public class Main {

	/**
	 * The method that looks after every user command until quit
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		Controller controller;
		
		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

		String userInput = "";
		String[] userCommands;
		Scanner scanner = new Scanner(System.in);
		controller = applicationContext.getBean("controller",Controller.class);

		do {
			userInput = scanner.nextLine();

			userCommands = controller.inputSplitter(userInput);

			controller.readCommand(userCommands);

		} while (!userInput.equals("quit"));

		scanner.close();
		
		
		((ConfigurableApplicationContext)applicationContext).close();

	}
	
}