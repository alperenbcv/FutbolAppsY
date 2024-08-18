package FootballApp.modules;

import FootballApp.entities.Manager;
import FootballApp.utility.DataGenerator;
import FootballApp.utility.DataIO;

import java.util.Optional;
import java.util.Scanner;

public class LogInModule {
	static Scanner sc = new Scanner(System.in);
	static Manager loggedManager = null;
	
	public static Manager managerLogIn() {
		System.out.println("Enter your Username:");
		String username = sc.nextLine();
		System.out.println("Enter your Password:");
		String password = sc.nextLine();
		Optional<Manager> byUsernameAndPassword = DataIO.managerDB.findByUsernameAndPassword(username, password);
		if (byUsernameAndPassword.isPresent()) {
			System.out.println("Login Successful!");
			loggedManager = byUsernameAndPassword.get();
			return byUsernameAndPassword.get();
		}
		System.out.println("Invalid credentials. Please try again.");
		return null;
	}
}