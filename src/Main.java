import FootballApp.entities.*;
import FootballApp.enums.ERegion;
import FootballApp.models.DatabaseModels;
import FootballApp.models.LeagueModel;
import FootballApp.models.TeamModel;
import FootballApp.modules.Menu;
import FootballApp.utility.DataIO;
import FootballApp.utility.FixtureGenerator;
import FootballApp.utility.ObserverInitializer;

import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		
		DataIO.dataIOInitialize();
		Menu.startMenu();
	}
}