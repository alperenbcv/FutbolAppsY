package FootballApp.utility;

import FootballApp.databases.ManagerDB;
import FootballApp.databases.PlayerDB;
import FootballApp.databases.TeamDB;
import FootballApp.entities.Manager;
import FootballApp.entities.Observer;
import FootballApp.entities.Player;
import FootballApp.entities.Team;

import java.util.Optional;

public class ObserverInitializer {
	private DataIO dataIO;
	private TeamDB teamDB;
	private PlayerDB playerDB;
	private ManagerDB managerDB;
	
	public ObserverInitializer(DataIO dataIO, TeamDB teamDB, PlayerDB playerDB, ManagerDB managerDB) {
		this.dataIO = dataIO;
		this.teamDB = teamDB;
		this.playerDB = playerDB;
		this.managerDB = managerDB;
	}
	
	public void initializeObservers() {
		for (Team team : teamDB.listAll()) {
			team.addObserver(dataIO);
		}
		
		
		for (Player player : playerDB.listAll()) {
			player.addObserver(dataIO);
		}
		
		
		for (Manager manager : managerDB.listAll()) {
			manager.addObserver(dataIO);
		}
	}
}