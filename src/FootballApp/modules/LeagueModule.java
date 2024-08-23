package FootballApp.modules;

import FootballApp.entities.League;
import FootballApp.entities.Manager;
import FootballApp.models.DatabaseModels;
import FootballApp.models.LeagueModel;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LeagueModule {

    static Scanner sc = new Scanner(System.in);
    static DatabaseModels databaseModel = new DatabaseModels();

    public static void startLeagueMenu() {
        int userInput;
        do {
            userInput = leagueMenuEntry();
            leagueMenuSelection(userInput);
        } while (userInput != 0);
    }

    private static int leagueMenuEntry() {
        int userInput=-1;
        boolean validInput = false;

        while(!validInput) {
            System.out.println("\n---------League Menu--------------");
            System.out.println("1-List of Leagues");
            System.out.println("2-Find League by ID");
            System.out.println("3-Find League by Name");
            System.out.println("0-Main Menu");
            System.out.print("Selection: ");
            try {
                userInput = sc.nextInt();
                sc.nextLine();
                if(userInput >= 0 && userInput <= 3) {
                    validInput = true;
                }
                else {
                    System.out.println("\nPlease enter a valid option!");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nPlease enter a numeric value!");
                sc.next();
            }
        }
        return userInput;
    }

    private static void leagueMenuSelection(int userInput) {
        switch (userInput) {
            case 1 -> displayAllLeagues();
            case 2 -> displayLeagueByID();
            case 3 -> displayLeagueByName();
            case 0 -> System.out.println("\nReturning to Main Menu...\n");
            default-> System.out.println("Please enter a valid value!");
        }
    }

    private static void displayLeagueByName() {
        System.out.println("Enter a League Name: (0=Back to Team Menu)");
        String leagueName=sc.nextLine();
        if(leagueName.equalsIgnoreCase("0")){
            return;
        }
        List<League> byPartialLeagueName = DatabaseModels.leagueDB.findByPartialLeagueName(leagueName);
        if(byPartialLeagueName.isEmpty()){
            System.out.println("No league found with the name you've entered!");
            return;
        }
        else {
            System.out.println(byPartialLeagueName.size()+" results found!");
            for (League league:byPartialLeagueName){
                System.out.println("\n"+"LeagueID: "+league.getId()+"- League Name: "+league.getLeagueName());
            }
        }
        displayLeagueByDetails();

    }
    
    private static void displayLeagueByDetails() {
        Integer leagueID = null;
        
        while (true) {
            try {
                System.out.print("\nWhich league do you want to select? Please enter the League ID (0=Back to Team Menu): ");
                leagueID = sc.nextInt();
                sc.nextLine();
                
                if (leagueID == 0) {
                    return;
                }
                
                Optional<League> byID = DatabaseModels.leagueDB.findByID(leagueID);
                if (byID.isPresent()) {
                    try {
                        LeagueModel lm = new LeagueModel(databaseModel, byID.get());
                        lm.displayLeagueInfo();
                        lm.displayLeagueTeams();
                        break;
                    } catch (Exception e) {
                        System.out.println("An error occurred while displaying league information: " + e.getMessage());
                    }
                } else {
                    System.out.println("League not found! Please enter a valid ID.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a numeric value!");
                sc.nextLine();
            }
        }
    }
    
    
    private static void displayLeagueByID() {
        Integer leagueID = null;
        
        while (true) {
            System.out.println("Enter a League ID: (0=Back to Team Menu)");
            
            try {
                leagueID = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Enter a numeric value!");
                sc.nextLine();
                continue;
            }
            
            if (leagueID == 0) {
                return;
            }
            
            Optional<League> byID = DatabaseModels.leagueDB.findByID(leagueID);
            if (byID.isPresent()) {
                try {
                    LeagueModel lm = new LeagueModel(databaseModel, byID.get());
                    lm.displayLeagueInfo();
                    lm.displayLeagueTeams();
                    break;
                } catch (Exception e) {
                    System.out.println("An error occurred while displaying league information: " + e.getMessage());
                }
            } else {
                System.out.println("League not found! Please enter a valid ID.");
            }
        }
    }
    
    
    protected static void displayAllLeagues() {
        List<League> leagues = DatabaseModels.leagueDB.listAll();
        for(League league:leagues){
            LeagueModel lm=new LeagueModel(databaseModel,league);
            lm.displayLeagueInfo();
        }
    }
    
    protected static Integer validLeagueIDControl() {
        Integer leagueID=null;
        boolean validLeagueID = false;
        do {
            try {
                System.out.print("Please Enter a League ID: ");
                leagueID = sc.nextInt();
                sc.nextLine();
                Optional<League> league = DatabaseModels.leagueDB.findByID(leagueID);
                if (league.isPresent()) {
                    validLeagueID = true;
                } else {
                    System.out.println("Please enter a valid League ID.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a numeric value!");
                sc.nextLine();
            }
        } while (!validLeagueID);
        
        return leagueID;
    }
}