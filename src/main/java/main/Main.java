package main;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import config.SpringConfig;
import controller.Controller;

@Component
public class Main {
	
	private static Controller controller;

	/**
	 * The method that loops after every command until quit
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

		String UserInput = "";
		String[] UserCommands = { "0" };
		Scanner sc = new Scanner(System.in);
		controller = applicationContext.getBean("controller",Controller.class);

		do {
			UserInput = sc.nextLine();

			UserCommands = controller.inputSplitter(UserInput);

			controller.readCommand(UserCommands);

		} while (!UserInput.equals("quit"));

		sc.close();

	}

}
