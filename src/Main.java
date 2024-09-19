
import FootballApp.entities.League;
import FootballApp.entities.Match;
import FootballApp.entities.Player;
import FootballApp.entities.Team;
import FootballApp.entities.attributes.GKAttributes;
import FootballApp.entities.attributes.MentalAttributes;
import FootballApp.entities.attributes.PhysicalAttributes;
import FootballApp.entities.attributes.TechnicalAttributes;
import FootballApp.enums.EMatchStatus;
import FootballApp.enums.EPosition;
import FootballApp.enums.ERegion;
import FootballApp.models.DatabaseModels;
import FootballApp.modules.Menu;
import FootballApp.utility.DataIO;
import FootballApp.utility.DatabaseManager;
import FootballApp.utility.MatchEngine;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;


public class Main {
	
	public static void main(String[] args) {
//	generator();
//		Match match1 = new Match(1,2,LocalDate.now(), EMatchStatus.SCHEDULED,1);
//		Match match2 = new Match(1,3,LocalDate.now(), EMatchStatus.SCHEDULED,1);
//		Match match3 = new Match(1,4,LocalDate.now(), EMatchStatus.SCHEDULED,1);
//		Match match4 = new Match(3,4,LocalDate.now(), EMatchStatus.SCHEDULED,1);

//		simulateGames(match3);
//		DataIO.dataIOInitialize();
		
		Menu.startMenu();
		
		
//		Player player = DatabaseModels.playerDB.findByID(2).get();
//		TechnicalAttributes playerTechnicalAttributes = player.getPlayerTechnicalAttributes();
//		Integer finishing = playerTechnicalAttributes.getFinishing();
//		System.out.println(finishing);

//		DataIO.savePlayersToFile();
		
	}
	
	public static void simulateGames(Match match) {
					MatchEngine matchEngine = new MatchEngine();
					matchEngine.simulateMatch(match);
					match.setStatus(EMatchStatus.PLAYED);
		
			}
	
			
	
//	public static void generator(){
//		League league1 = new League("League1", 1, "2024-2025", 1, ERegion.PREMIER_LEAGUE);
//		Team team1 = new Team(1, "Team1", "Istanbul", "Stadium1", 1d, 1d);
//		Team team2 = new Team(1, "Team2", "Istanbul", "Stadium2", 1d, 1d);
//		Team team3 = new Team(1, "Team3", "Istanbul", "Stadium3", 1d, 1d);
//		Team team4 = new Team(1, "Team4", "Istanbul", "Stadium4", 1d, 1d);
//
//		Player player1 =
//				new Player("player1", "player1", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
//				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),1, 1d,
//				           1d, EPosition.ST);
//		Player player2 =
//				new Player("player2", "player2", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
//				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),1, 1d,
//				           1d, EPosition.CM);
//		Player player3 =
//				new Player("player3", "player3", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
//				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),1, 1d,
//				           1d, EPosition.LB);
//		Player player4 =
//				new Player("player4", "player4", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
//				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),1, 1d,
//				           1d, EPosition.RB);
//		Player player5 =
//				new Player("player5", "player5", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
//				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),1, 1d,
//				           1d, EPosition.RW);
//		Player player6 =
//				new Player("player6", "player6", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
//				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),1, 1d,
//				           1d, EPosition.LW);
//		Player player7 =
//				new Player("player7", "player7", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
//				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),1, 1d,
//				           1d, EPosition.CB);
//		Player player8 =
//				new Player("player8", "player8", 1, "Turkiye", new GKAttributes(10,10,10,10), 1, 1d,1d);
//
//		Player player9 =
//				new Player("player9", "player9", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
//				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),2, 1d,
//				           1d, EPosition.ST);
//		Player player10 =
//				new Player("player10", "player10", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
//				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),2, 1d,
//				           1d, EPosition.CM);
//		Player player11 =
//				new Player("player11", "player11", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
//				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),2, 1d,
//				           1d, EPosition.LB);
//		Player player12 =
//				new Player("player12", "player12", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
//				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),2, 1d,
//				           1d, EPosition.RB);
//		Player player13 =
//				new Player("player13", "player13", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
//				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),2, 1d,
//				           1d, EPosition.RW);
//		Player player14 =
//				new Player("player14", "player14", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
//				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),2, 1d,
//				           1d, EPosition.LW);
//		Player player15 =
//				new Player("player15", "player15", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
//				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),2, 1d,
//				           1d, EPosition.CB);
//		Player player16 =
//				new Player("player16", "player16", 1, "Turkiye", new GKAttributes(10,10,10,10), 2, 1d,1d);
//
//		Player player17 =
//				new Player("player17", "player17", 1, "Turkiye", new TechnicalAttributes(15, 15, 15, 15, 15),
//				           new MentalAttributes(15,15,15), new PhysicalAttributes(15,15,15,15,15),3, 1d,
//				           1d, EPosition.ST);
//		player17.getPlayerTechnicalAttributes().setCrossing(15);
//		player17.getPlayerTechnicalAttributes().setHeader(15);
//		player17.getPlayerTechnicalAttributes().setPositioning(15);
//		player17.getPlayerTechnicalAttributes().setFirstTouch(15);
//		Player player18 =
//				new Player("player18", "player18", 1, "Turkiye", new TechnicalAttributes(15, 15, 15, 15, 15),
//				           new MentalAttributes(15,15,15), new PhysicalAttributes(15,15,15,15,15),3, 1d,
//				           1d, EPosition.CM);
//		player18.getPlayerTechnicalAttributes().setCrossing(15);
//		player18.getPlayerTechnicalAttributes().setHeader(15);
//		player18.getPlayerTechnicalAttributes().setPositioning(15);
//		player18.getPlayerTechnicalAttributes().setFirstTouch(15);
//		Player player19 =
//				new Player("player19", "player19", 1, "Turkiye", new TechnicalAttributes(15, 15, 15, 15, 15),
//				           new MentalAttributes(15,15,15), new PhysicalAttributes(15,15,15,15,15),3, 1d,
//				           1d, EPosition.LB);
//		player19.getPlayerTechnicalAttributes().setCrossing(15);
//		player19.getPlayerTechnicalAttributes().setHeader(15);
//		player19.getPlayerTechnicalAttributes().setPositioning(15);
//		player19.getPlayerTechnicalAttributes().setFirstTouch(15);
//		Player player20 =
//				new Player("player20", "player20", 1, "Turkiye", new TechnicalAttributes(15, 15, 15, 15, 15),
//				           new MentalAttributes(15,15,15), new PhysicalAttributes(15,15,15,15,15),3, 1d,
//				           1d, EPosition.RB);
//		player20.getPlayerTechnicalAttributes().setCrossing(15);
//		player20.getPlayerTechnicalAttributes().setHeader(15);
//		player20.getPlayerTechnicalAttributes().setPositioning(15);
//		player20.getPlayerTechnicalAttributes().setFirstTouch(15);
//		Player player21 =
//				new Player("player21", "player21", 1, "Turkiye", new TechnicalAttributes(15, 15, 15, 15, 15),
//				           new MentalAttributes(15,15,15), new PhysicalAttributes(15,15,15,15,15),3, 1d,
//				           1d, EPosition.RW);
//		player21.getPlayerTechnicalAttributes().setCrossing(15);
//		player21.getPlayerTechnicalAttributes().setHeader(15);
//		player21.getPlayerTechnicalAttributes().setPositioning(15);
//		player21.getPlayerTechnicalAttributes().setFirstTouch(15);
//		Player player22 =
//				new Player("player22", "player22", 1, "Turkiye", new TechnicalAttributes(15, 15, 15, 15, 15),
//				           new MentalAttributes(15,15,15), new PhysicalAttributes(15,15,15,15,15),3, 1d,
//				           1d, EPosition.LW);
//		player22.getPlayerTechnicalAttributes().setCrossing(15);
//		player22.getPlayerTechnicalAttributes().setHeader(15);
//		player22.getPlayerTechnicalAttributes().setPositioning(15);
//		player22.getPlayerTechnicalAttributes().setFirstTouch(15);
//		Player player23 =
//				new Player("player23", "player23", 1, "Turkiye", new TechnicalAttributes(15, 15, 15, 15, 15),
//				           new MentalAttributes(15,15,15), new PhysicalAttributes(15,15,15,15,15),3, 1d,
//				           1d, EPosition.CB);
//		player23.getPlayerTechnicalAttributes().setCrossing(15);
//		player23.getPlayerTechnicalAttributes().setHeader(15);
//		player23.getPlayerTechnicalAttributes().setPositioning(15);
//		player23.getPlayerTechnicalAttributes().setFirstTouch(15);
//
//		Player player24 =
//				new Player("player24", "player24", 1, "Turkiye", new GKAttributes(15,15,15,15), 3, 1d,1d);
//		Player player25 =
//				new Player("player25", "player25", 1, "Turkiye", new TechnicalAttributes(20, 20, 20, 20, 20),
//				           new MentalAttributes(20,20,20), new PhysicalAttributes(20,20,20,20,20),4, 1d,
//				           1d, EPosition.ST);
//		player25.getPlayerTechnicalAttributes().setCrossing(20);
//		player25.getPlayerTechnicalAttributes().setHeader(20);
//		player25.getPlayerTechnicalAttributes().setPositioning(20);
//		player25.getPlayerTechnicalAttributes().setFirstTouch(20);
//
//
//		Player player26 =
//				new Player("player26", "player26", 1, "Turkiye", new TechnicalAttributes(20, 20, 20, 20, 20),
//				           new MentalAttributes(20,20,20), new PhysicalAttributes(20,20,20,20,20),4, 1d,
//				           1d, EPosition.CM);
//
//		player26.getPlayerTechnicalAttributes().setCrossing(20);
//		player26.getPlayerTechnicalAttributes().setHeader(20);
//		player26.getPlayerTechnicalAttributes().setPositioning(20);
//		player26.getPlayerTechnicalAttributes().setFirstTouch(20);
//		Player player27 =
//				new Player("player27", "player27", 1, "Turkiye", new TechnicalAttributes(20, 20, 20, 20, 20),
//				           new MentalAttributes(20,20,20), new PhysicalAttributes(20,20,20,20,20),4, 1d,
//				           1d, EPosition.LB);
//		player27.getPlayerTechnicalAttributes().setCrossing(20);
//		player27.getPlayerTechnicalAttributes().setHeader(20);
//		player27.getPlayerTechnicalAttributes().setPositioning(20);
//		player27.getPlayerTechnicalAttributes().setFirstTouch(20);
//		Player player28 =
//				new Player("player28", "player28", 1, "Turkiye", new TechnicalAttributes(20, 20, 20, 20, 20),
//				           new MentalAttributes(20,20,20), new PhysicalAttributes(20,20,20,20,20),4, 1d,
//				           1d, EPosition.RB);
//		player28.getPlayerTechnicalAttributes().setCrossing(20);
//		player28.getPlayerTechnicalAttributes().setHeader(20);
//		player28.getPlayerTechnicalAttributes().setPositioning(20);
//		player28.getPlayerTechnicalAttributes().setFirstTouch(20);
//		Player player29 =
//				new Player("player29", "player29", 1, "Turkiye", new TechnicalAttributes(20, 20, 20, 20, 20),
//				           new MentalAttributes(20,20,20), new PhysicalAttributes(20,20,20,20,20),4, 1d,
//				           1d, EPosition.RW);
//		player29.getPlayerTechnicalAttributes().setCrossing(20);
//		player29.getPlayerTechnicalAttributes().setHeader(20);
//		player29.getPlayerTechnicalAttributes().setPositioning(20);
//		player29.getPlayerTechnicalAttributes().setFirstTouch(20);
//		Player player30 =
//				new Player("player30", "player30", 1, "Turkiye", new TechnicalAttributes(20, 20, 20, 20, 20),
//				           new MentalAttributes(20,20,20), new PhysicalAttributes(20,20,20,20,20),4, 1d,
//				           1d, EPosition.LW);
//		player30.getPlayerTechnicalAttributes().setCrossing(20);
//		player30.getPlayerTechnicalAttributes().setHeader(20);
//		player30.getPlayerTechnicalAttributes().setPositioning(20);
//		player30.getPlayerTechnicalAttributes().setFirstTouch(20);
//		Player player31 =
//				new Player("player31", "player31", 1, "Turkiye", new TechnicalAttributes(20, 20, 20, 20, 20),
//				           new MentalAttributes(20,20,20), new PhysicalAttributes(20,20,20,20,20),4, 1d,
//				           1d, EPosition.CB);
//		player31.getPlayerTechnicalAttributes().setCrossing(20);
//		player31.getPlayerTechnicalAttributes().setHeader(20);
//		player31.getPlayerTechnicalAttributes().setPositioning(20);
//		player31.getPlayerTechnicalAttributes().setFirstTouch(20);
//		Player player32 =
//				new Player("player32", "player32", 1, "Turkiye", new GKAttributes(20,20,20,20), 4, 1d,1d);
//		league1.getLeagueTeamIDList().add(team1.getId());
//		league1.getLeagueTeamIDList().add(team2.getId());
//		league1.getLeagueTeamIDList().add(team3.getId());
//
//
//	}
}