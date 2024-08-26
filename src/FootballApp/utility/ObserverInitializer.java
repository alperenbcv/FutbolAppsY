package FootballApp.utility;

import FootballApp.databases.ManagerDB;
import FootballApp.databases.PlayerDB;
import FootballApp.databases.TeamDB;
import FootballApp.entities.*;
import FootballApp.models.DatabaseModels;

public class ObserverInitializer {
	private DatabaseModels databaseModels;
	
	public ObserverInitializer(DatabaseModels databaseModels) {
		this.databaseModels = databaseModels;
	}
	
	public void initializeObservers() {
		for (Match match : DatabaseModels.matchDB.listAll()) {
			match.addObserver(databaseModels);
		}
		for (Team team : DatabaseModels.teamDB.listAll()) {
			team.addObserver(databaseModels);
		}
		
		
		for (Player player : DatabaseModels.playerDB.listAll()) {
			player.addObserver(databaseModels);
		}
		
		
		for (Manager manager : DatabaseModels.managerDB.listAll()) {
			manager.addObserver(databaseModels);
		}
		
		for (League league : DatabaseModels.leagueDB.listAll()) {
			league.addObserver(databaseModels);
		}
		
		for (TeamStats ts : DatabaseModels.tsDB.findAll().get()) {
			ts.addObserver(databaseModels);
		}
		
		
	}
	public void addObserverToNewTeam(Team team) {
		team.addObserver(databaseModels);
	}
}