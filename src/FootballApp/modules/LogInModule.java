package FootballApp.modules;

import FootballApp.entities.Manager;
import FootballApp.models.DatabaseModels;
import FootballApp.utility.DataIO;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LogInModule {
	static Scanner sc = new Scanner(System.in);
	static Manager loggedManager = null;

	public static Manager managerLogIn() {
		System.out.println("\nWelcome to the Football Manager App!");
		System.out.println("\n---------------Available Managers------------------");
		Optional<List<Manager>> all = DatabaseModels.managerDB.findAll();
		if (all.isPresent()) {
			all.get().forEach(manager -> {
				System.out.println(manager.getId() + " " + manager.getName() + " " + manager.getSurName());
			});
		}

		boolean validInput = false;
		do {
			System.out.print("Enter a Manager ID to see login information: ");
			Optional<Integer> managerID = Optional.empty();

			try {
				managerID = Optional.of(sc.nextInt());
			} catch (InputMismatchException e) {
				System.out.println("Please enter a numeric value!");
				sc.nextLine();
				continue;
			}

			sc.nextLine();

			if (managerID.isPresent()) {
				int id = managerID.get();
				if(id == 0){
					return null;
				}
				if (id > 0 && id <= DatabaseModels.managerDB.listAll().size()) {
					Optional<Manager> byID = DatabaseModels.managerDB.findByID(id);
					if (byID.isPresent()) {
						System.out.println("\n------------------------------------------------");
						System.out.println("Username: " + byID.get().getManagerUserName() + ", Password: " + byID.get().getManagerPassword());
						validInput = true;
						System.out.println("------------------------------------------------");
					} else {
						System.out.println("Manager not found! Please enter a valid ID.");
					}
				} else {
					System.out.println("Invalid ID. Please enter a value within the valid range.");
				}
			}
		} while (!validInput);

		do {
			System.out.print("\nEnter your Username: ");
			String username = sc.nextLine();
			System.out.print("Enter your Password: ");
			String password = sc.nextLine();

			Optional<Manager> byUsernameAndPassword = DatabaseModels.managerDB.findByUsernameAndPassword(username, password);
			if (byUsernameAndPassword.isPresent()) {
				System.out.println("Login Successful!");
				System.out.println("\nYou are playing as: " + byUsernameAndPassword.get().getName() + " " + byUsernameAndPassword.get().getSurName() + "\n");
				loggedManager = byUsernameAndPassword.get();
				return loggedManager;
			} else {
				System.out.println("\nInvalid credentials. Please try again.");
			}
		} while (true);
	}

	public static Manager managerLogOut(){
		loggedManager=null;
		System.out.println("You are logged out!");
		return loggedManager;
	}
}