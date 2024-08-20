package FootballApp.modules;

import FootballApp.databases.PlayerDB;
import FootballApp.entities.Player;
import FootballApp.entities.Team;
import FootballApp.utility.DataGenerator;
import FootballApp.utility.DataIO;

import javax.xml.crypto.Data;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PlayerModule {
    static Scanner sc = new Scanner(System.in);

    public static void playerMenu() {
        int userInput;
        do {
            userInput = playerMenuEntry();
            playerMenuSelection(userInput);
        } while (userInput != 0);
    }

    private static int playerMenuEntry() {
        int userInput = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("\n--------------Player Menu---------------------");
            System.out.println("1-List of all Player ID's by Team");
            System.out.println("2-Find Player by ID");
            System.out.println("3-Find Player by Name");
            System.out.println("4-Find Player by Team");
            System.out.println("5-Find Player by Rating");
            System.out.println("0-Main Menu");
            System.out.print("Selection: ");
            try {
                userInput = sc.nextInt();
                sc.nextLine();
                if (userInput >= 0 && userInput <= 5) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a valid option!");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nPlease enter a numeric value!");
                sc.next();
            }
        }
        return userInput;
    }

    private static void playerMenuSelection(int userInput) {
        switch (userInput) {
            case 1 -> displayPlayersByTeam();
            case 2 -> displayPlayerByID();
            case 3 -> displayPlayerByName();
            case 4 -> displayPlayersByTeamName();
            case 5 -> displayPlayersByRating();
            case 0 -> System.out.println("\nReturning to Main Menu...\n");
            default -> System.out.println("\nPlease enter a valid value!");
        }
    }

    private static void displayPlayersByTeam() {
        System.out.println("\n------------------List of all Player ID's by Team-----------------------");
        List<Team> teams = DataIO.teamDB.listAll();
        teams.forEach(team -> {
            if(!team.getTeamName().equalsIgnoreCase("bye")) {
                System.out.println("\nTeam ID: " + team.getId() + ", Team Name: " + team.getTeamName());
                System.out.println("Player ID's: ");
                DataIO.playerDB.findByTeamID(team.getId()).forEach(player -> System.out.print(player.getId() + " "));
                System.out.println();
                System.out.println("---------------------------------------------------------------------------------");
            }
            });


        displayPlayerDetails();
    }

    private static void displayPlayerByID() {
        int size = DataIO.playerDB.listAll().size();
        System.out.print("\nEnter a Player ID 1-" + size + " (0=Back to Player Menu): ");
        try {
            int playerID = sc.nextInt();
            if (playerID == 0) {
                return;
            }
            Optional<Player> byID = DataIO.playerDB.findByID(playerID);
            if (byID.isPresent()) {
                Player player = byID.get();
                System.out.println(player);
            } else {
                System.out.println("Player not found!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a numeric value between 1-" + size);
        }
        sc.nextLine();
    }


    private static void displayPlayerByName() {
        System.out.print("\nEnter a Player Name (0=Back to Player Menu): ");
        String playerName = sc.nextLine();
        if (playerName.equalsIgnoreCase("0")) {
            return;
        }
        if (playerName.matches(".*\\d.*")) {
            System.out.println("Name can't contain a numeric value!");
            return;
        }

        List<Player> byPlayerName = DataIO.playerDB.findByPlayerName(playerName);
        if (byPlayerName.isEmpty()) {
            System.out.println("Player not found!");
            return;
        }
        System.out.println("\n-----------------------Player List----------------------------------------");
        byPlayerName.forEach(player -> System.out.println("PlayerID: " + player.getId() + " PlayerNameSurname: " + player.getName() + " " + player.getSurName()));
        displayPlayerDetails();
    }

    private static void displayPlayersByTeamName() {
        System.out.print("\nEnter a Team Name (0=Back to Player Menu): ");
        String teamName = sc.nextLine();
        if (teamName.equalsIgnoreCase("0")) {
            return;
        }
        List<Player> byTeamName = DataIO.playerDB.findByTeamName(teamName);
        byTeamName.forEach(System.out::println);
    }

    private static void displayPlayerDetails() {
        boolean validInput = false;
        Integer playerID = null;

        do {
            System.out.print("\nWhich player you'd like to see by detail? Please enter the Player ID (0=Back to Player Menu): ");
            try {
                playerID = sc.nextInt();
                sc.nextLine(); // buffer'ı temizlemek için
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a numeric value!");
                sc.nextLine(); // buffer'ı temizlemek için
            }
        } while (!validInput);

        if (playerID == 0) {
            return;
        }

        Optional<Player> player = DataIO.playerDB.findByID(playerID);
        if (player.isPresent()) {
            System.out.println("\n-----------------Player Details------------------------");
            System.out.println(player.get());
        } else {
            System.out.println("Player not found!");
        }
    }


    private static void displayPlayersByRating() {
        System.out.print("\nEnter a minimum rating (0=Back to Player Menu): ");
        Optional<Integer> optionalRating = Optional.empty();

        try {
            int inputRating = sc.nextInt();
            if (inputRating == 0) {
                return;
            }
            optionalRating = Optional.of(inputRating);
        } catch (InputMismatchException e) {
            System.out.println("Please enter a numeric value between 0-100");
            sc.nextLine();
            return;
        }

        int rating = optionalRating.get();
        List<Player> players = DataIO.playerDB.listAll();
        List<Player> list = players.stream().filter(player -> player.getPlayerOverallRating() >= rating).toList();
        if (list.isEmpty()) {
            System.out.println("No players found with rating higher than or equal to " + rating + "!");
        } else {
            System.out.println("\n" + list.size() + " players found with rating higher than or equal to " + rating + ":");
            list.forEach(System.out::println);
        }
    }

}