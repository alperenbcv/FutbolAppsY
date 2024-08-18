package FootballApp.modules;

import FootballApp.entities.Manager;
import FootballApp.utility.DataGenerator;
import FootballApp.utility.DataIO;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LogInModule {
	static Scanner sc = new Scanner(System.in);
	static Manager loggedManager = null;
	
	public static Manager managerLogIn() {
		System.out.println("\nWelcome to the Football Manager App!");
		System.out.println("Available Managers: ");
		Optional<List<Manager>> all = DataIO.managerDB.findAll();
		if(all.isPresent()) {
			all.get().forEach(manager -> {
				System.out.println(manager.getId()+" "+manager.getName() + " " + manager.getSurName());
			});
		}
		boolean validInput = false;
		do{
			System.out.println("Enter a Manager ID to see login informations: ");
			Integer managerID = sc.nextInt();
			sc.nextLine();
			if(managerID<=0 || managerID > DataIO.managerDB.listAll().size()) {
			
			}
			Optional<Manager> byID = DataIO.managerDB.findByID(managerID);
			if (byID.isPresent()) {
				System.out.println(byID.get().getManagerUserName()+ " "+byID.get().getManagerPassword());
				validInput=true;
			}
			else {
				System.out.println("Manager not found! Please enter a valid ID.");
			}
		} while(!validInput);
		
		System.out.println("Enter your Username:");
		String username = sc.nextLine();
		System.out.println("Enter your Password:");
		String password = sc.nextLine();
		Optional<Manager> byUsernameAndPassword = DataIO.managerDB.findByUsernameAndPassword(username, password);
		if (byUsernameAndPassword.isPresent()) {
			System.out.println("Login Successful!");
			System.out.println("You are playing as: " + byUsernameAndPassword.get().getName() + " " + byUsernameAndPassword.get().getSurName());
			loggedManager = byUsernameAndPassword.get();
			return byUsernameAndPassword.get();
		}
		System.out.println("Invalid credentials. Please try again.");
		return null;
	}
}