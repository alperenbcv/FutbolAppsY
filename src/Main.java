import FootballApp.entities.*;
import FootballApp.enums.ERegion;
import FootballApp.models.DatabaseModels;
import FootballApp.models.LeagueModel;
import FootballApp.modules.Menu;
import FootballApp.utility.DataIO;
import FootballApp.utility.ObserverInitializer;

import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		
		DataIO.dataIOInitialize();
		List<Integer> teamIds = new ArrayList<>(DatabaseModels.teamDB.listAll().stream().map(team -> team.getId()).toList());
		Fixture fixture = new Fixture(teamIds.size());
		fixture.generateFixtures(teamIds, fixture.getTeamCount() * 2 - 2);
		DatabaseModels.fixtureDB.save(fixture);
		League league = new League("Trendyol Super Lig", 1, teamIds, 1,"2024-2025",1, ERegion.TURKISH_LEAGUE);
		DatabaseModels.leagueDB.save(league);
//		DatabaseModels database = new DatabaseModels();
//		LeagueModel leagueModel = new LeagueModel(database, league);
//		leagueModel.displayClubInfo();
		
		DataIO databaseModels = new DataIO();
		ObserverInitializer oi = new ObserverInitializer(databaseModels, DatabaseModels.teamDB, DatabaseModels.playerDB, DatabaseModels.managerDB);
		oi.initializeObservers();
		Menu.startMenu();
	}
}