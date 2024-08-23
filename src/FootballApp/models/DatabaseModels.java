package FootballApp.models;


import FootballApp.databases.*;
import FootballApp.entities.*;
import FootballApp.utility.DataIO;

public class DatabaseModels implements Observer {
	
	private static DatabaseModels instance = new DatabaseModels();
	
	public static TeamDB teamDB = new TeamDB();
	public static ManagerDB managerDB = new ManagerDB();
	public static PlayerDB playerDB = new PlayerDB();
	public static LeagueDB leagueDB = new LeagueDB();
	public static MatchDB matchDB = new MatchDB();
	
	
	private DatabaseModels() {
	}
	
	public static DatabaseModels getInstance() {
		return instance;
	}
	
	private void initializeObservers() {
		for (Team team : teamDB.listAll()) {
			team.addObserver(this);
		}
		for (Manager manager : managerDB.listAll()) {
			manager.addObserver(this);
		}
		for (Player player : playerDB.listAll()) {
			player.addObserver(this);
		}
		for (League league : leagueDB.listAll()) {
			league.addObserver(this);
		}
		for (Match match : matchDB.listAll()) {
			match.addObserver(this);
		}
	}
	
	@Override
	public void update(Observable observable) {
		if (observable instanceof Team) {
			DataIO.saveTeamsToFile();
		} else if (observable instanceof Manager) {
			DataIO.saveManagersToFile();
		}
		else if (observable instanceof Player) {
			DataIO.savePlayersToFile();
		}
		else if (observable instanceof League) {
            DataIO.saveLeaguesToFile();
        }
	}
}