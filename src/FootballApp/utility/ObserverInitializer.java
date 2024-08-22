package FootballApp.utility;

import FootballApp.databases.ManagerDB;
import FootballApp.databases.PlayerDB;
import FootballApp.databases.TeamDB;
import FootballApp.entities.Manager;
import FootballApp.entities.Player;
import FootballApp.entities.Team;

public class ObserverInitializer {
	private DataIO databaseModels;
	private TeamDB teamDB;
	private PlayerDB playerDB;
	private ManagerDB managerDB;
	
	public ObserverInitializer(DataIO databaseModels, TeamDB teamDB, PlayerDB playerDB, ManagerDB managerDB) {
		this.databaseModels = databaseModels;
		this.teamDB = teamDB;
		this.playerDB = playerDB;
		this.managerDB = managerDB;
	}
	
	public void initializeObservers() {
		for (Team team : teamDB.listAll()) {
			team.addObserver(databaseModels);
		}
		
		
		for (Player player : playerDB.listAll()) {
			player.addObserver(databaseModels);
		}
		
		
		for (Manager manager : managerDB.listAll()) {
			manager.addObserver(databaseModels);
		}
	}
	public void addObserverToNewTeam(Team team) {
		team.addObserver(databaseModels);
	}
}