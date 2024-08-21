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
	
	public static void main(String[] args) {
		DataIO.dataIOInitialize();
		List<Integer> teamIds = new ArrayList<>(DataIO.teamDB.listAll().stream().map(team -> team.getId()).toList());
		League league1 = new League("Trendyol Super Lig", 1, teamIds, 1, "2024-2025", 1, ERegion.TURKISH_LEAGUE);
		DataIO.leagueDB.save(league1);

		Fixture fixture = new Fixture(teamIds.size());
		fixture.generateFixtures(teamIds, fixture.getTeamCount() * 2 - 2);
		DataIO.fixtureDB.save(fixture);

		DataIO dataIO = new DataIO();
		ObserverInitializer oi = new ObserverInitializer(dataIO, DataIO.teamDB, DataIO.playerDB, DataIO.managerDB);
		oi.initializeObservers();
		Menu.startMenu();
	}
}