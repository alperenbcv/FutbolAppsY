import FootballApp.entities.*;
import FootballApp.enums.ERegion;
import FootballApp.models.DatabaseModels;
import FootballApp.models.LeagueModel;
import FootballApp.modules.Menu;
import FootballApp.utility.DataIO;
import FootballApp.utility.FixtureGenerator;
import FootballApp.utility.ObserverInitializer;

import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		
		DataIO.dataIOInitialize();
		DataIO databaseModels = new DataIO();
		ObserverInitializer oi = new ObserverInitializer(databaseModels, DatabaseModels.teamDB, DatabaseModels.playerDB, DatabaseModels.managerDB);
		oi.initializeObservers();
		Menu.startMenu();
	}
}