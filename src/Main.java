import FootballApp.databases.ManagerDB;
import FootballApp.databases.PlayerDB;
import FootballApp.databases.TeamDB;
import FootballApp.entities.Player;
import FootballApp.entities.Team;
import FootballApp.modules.Menu;
import FootballApp.modules.PlayerModule;
import FootballApp.modules.TeamModule;
import FootballApp.utility.DataGenerator;
import FootballApp.utility.DataIO;
import FootballApp.utility.ObserverInitializer;

import java.io.File;
import java.time.LocalDate;
import java.util.*;

public class Main {
	static PlayerDB playerDB = new PlayerDB();
	static Scanner sc = new Scanner(System.in);
	static TeamDB teamDB = new TeamDB();

	public static void main(String[] args) {
		DataIO.dataIOInitialize();
		DataIO dataIO = new DataIO();
		ObserverInitializer oi = new ObserverInitializer(dataIO, DataIO.teamDB, DataIO.playerDB, DataIO.managerDB);
		oi.initializeObservers();
		Menu.startMenu();
	}
}