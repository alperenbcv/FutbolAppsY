package FootballApp.utility;

import FootballApp.databases.ManagerDB;
import FootballApp.databases.PlayerDB;
import FootballApp.databases.TeamDB;
import FootballApp.entities.Manager;
import FootballApp.entities.Player;
import FootballApp.entities.Team;
import FootballApp.models.DatabaseModels;

public class ObserverInitializer {
	private DatabaseModels databaseModels;
	
	public ObserverInitializer(DatabaseModels databaseModels) {
		this.databaseModels = databaseModels;
	}
	
	public void initializeObservers() {
		for (Team team : DatabaseModels.teamDB.listAll()) {
			team.addObserver(databaseModels);
		}
		
		
		for (Player player : DatabaseModels.playerDB.listAll()) {
			player.addObserver(databaseModels);
		}
		
		
		for (Manager manager : DatabaseModels.managerDB.listAll()) {
			manager.addObserver(databaseModels);
		}
	}
	public void addObserverToNewTeam(Team team) {
		team.addObserver(databaseModels);
	}
}