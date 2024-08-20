import FootballApp.databases.ManagerDB;
import FootballApp.databases.PlayerDB;
import FootballApp.databases.TeamDB;
import FootballApp.entities.*;
import FootballApp.enums.ERegion;
import FootballApp.modules.Menu;
import FootballApp.modules.PlayerModule;
import FootballApp.modules.TeamModule;
import FootballApp.utility.DataGenerator;
import FootballApp.utility.DataIO;
import FootballApp.utility.ObserverInitializer;

import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

public class Main {
	static PlayerDB playerDB = new PlayerDB();
	static Scanner sc = new Scanner(System.in);
	static TeamDB teamDB = new TeamDB();
	
	public static void main(String[] args) {
		DataIO.dataIOInitialize();
		List<Integer> list1 = new ArrayList<>(DataIO.teamDB.listAll().stream().map(team -> team.getId()).toList());
		League league1 = new League("Trendyol Super Lig", 1, list1, 1, "2024-2025", 1, ERegion.TURKISH_LEAGUE);
		
		Fixture fixture1 = new Fixture(20);
		System.out.println(list1);
		
		
		List<String> fixtureList = new ArrayList<>();
		LocalDate matchDate = fixture1.getSeasonStartDate();
		
		List<Integer> teams = league1.getLeagueTeamIDList();
		int count = fixture1.getTeamCount();
		for (int round = 1; round < count; round++) {
			fixtureList.add("Week " + round + ":");
			LocalDate[] matchDates = getWeekMatchDates(matchDate);
			for (int i = 0; i < count / 2; i++) {
				int home = teams.get(i);
				int away = teams.get(count - 1 - i);
				fixtureList.add(home + " - " + away + " on " + matchDates[i % 4]);
			}
			Collections.rotate(teams.subList(1,count),1);
			matchDate = matchDate.plusWeeks(1);
		}
		
		for (String fixtureInfo : fixtureList) {
			if (fixtureInfo.startsWith("Week")) {
				System.out.println("FixtureID: " + fixture1.getId() + " - " + fixtureInfo);
			}
			else {
				System.out.println(fixtureInfo);
			}
		}

//
//		DataIO dataIO = new DataIO();
//		ObserverInitializer oi = new ObserverInitializer(dataIO, DataIO.teamDB, DataIO.playerDB, DataIO.managerDB);
//		oi.initializeObservers();
//		Menu.startMenu();
	}
	
	private static LocalDate getNextMatchDate(LocalDate currentDate) {
		DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
		switch (dayOfWeek) {
			case FRIDAY:
				return currentDate.plusDays(1);
			case SATURDAY:
				return currentDate.plusDays(1);
			case SUNDAY:
				return currentDate.plusDays(1);
			case MONDAY:
				return currentDate.plusDays(4);
			default:
				return currentDate.plusDays(7);
		}
	}
	
	private static LocalDate[] getWeekMatchDates(LocalDate startDate) {
		return new LocalDate[]{startDate, // Cuma
				startDate.plusDays(1), // Cumartesi
				startDate.plusDays(2), // Pazar
				startDate.plusDays(3) // Pazartesi
		};
	}
}