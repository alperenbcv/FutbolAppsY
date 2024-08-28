
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
import FootballApp.utility.MatchEngine;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;


public class Main {
	
	public static void main(String[] args) {
	
	
	}
	
	public static void simulateGames() {
		Match match1=new Match(1,2,LocalDate.now(),EMatchStatus.SCHEDULED,1);
					MatchEngine matchEngine = new MatchEngine();
					matchEngine.simulateMatch(match1);
					match1.setStatus(EMatchStatus.PLAYED);
		
		Match match2=new Match(3,1,LocalDate.now(),EMatchStatus.SCHEDULED,1);
		matchEngine.simulateMatch(match2);
		match2.setStatus(EMatchStatus.PLAYED);
		
			}
	
			
	
	public void generator(){
		League league1 = new League("League1", 1, "2024-2025", 1, ERegion.PREMIER_LEAGUE);
		Team team1 = new Team(1, "Team1", "Istanbul", "Stadium1", 1d, 1d);
		Team team2 = new Team(1, "Team2", "Istanbul", "Stadium2", 1d, 1d);
		Team team3 = new Team(1, "Team3", "Istanbul", "Stadium3", 1d, 1d);
		Team team4 = new Team(1, "Team4", "Istanbul", "Stadium4", 1d, 1d);
		
		Player player1 =
				new Player("player1", "player1", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),1, 1d,
				           1d, EPosition.ST);
		Player player2 =
				new Player("player2", "player2", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),1, 1d,
				           1d, EPosition.CM);
		Player player3 =
				new Player("player3", "player3", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),1, 1d,
				           1d, EPosition.LB);
		Player player4 =
				new Player("player4", "player4", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),1, 1d,
				           1d, EPosition.RB);
		Player player5 =
				new Player("player5", "player5", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),1, 1d,
				           1d, EPosition.RW);
		Player player6 =
				new Player("player6", "player6", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),1, 1d,
				           1d, EPosition.LW);
		Player player7 =
				new Player("player7", "player7", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),1, 1d,
				           1d, EPosition.CB);
		Player player8 =
				new Player("player8", "player8", 1, "Turkiye", new GKAttributes(10,10,10,10), 1, 1d,1d);
		
		Player player9 =
				new Player("player9", "player9", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),2, 1d,
				           1d, EPosition.ST);
		Player player10 =
				new Player("player10", "player10", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),2, 1d,
				           1d, EPosition.CM);
		Player player11 =
				new Player("player11", "player11", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),2, 1d,
				           1d, EPosition.LB);
		Player player12 =
				new Player("player12", "player12", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),2, 1d,
				           1d, EPosition.RB);
		Player player13 =
				new Player("player13", "player13", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),2, 1d,
				           1d, EPosition.RW);
		Player player14 =
				new Player("player14", "player14", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),2, 1d,
				           1d, EPosition.LW);
		Player player15 =
				new Player("player15", "player15", 1, "Turkiye", new TechnicalAttributes(10, 10, 10, 10, 10),
				           new MentalAttributes(10,10,10), new PhysicalAttributes(10,10,10,10,10),2, 1d,
				           1d, EPosition.CB);
		Player player16 =
				new Player("player16", "player16", 1, "Turkiye", new GKAttributes(10,10,10,10), 2, 1d,1d);
	
		Player player17 =
				new Player("player9", "player9", 1, "Turkiye", new TechnicalAttributes(15, 15, 15, 15, 15),
				           new MentalAttributes(10,10,10), new PhysicalAttributes(15,15,15,15,15),3, 1d,
				           1d, EPosition.ST);
		Player player18 =
				new Player("player10", "player10", 1, "Turkiye", new TechnicalAttributes(15, 15, 15, 15, 15),
				           new MentalAttributes(10,10,10), new PhysicalAttributes(15,15,15,15,15),3, 1d,
				           1d, EPosition.CM);
		Player player19 =
				new Player("player11", "player11", 1, "Turkiye", new TechnicalAttributes(15, 15, 15, 15, 15),
				           new MentalAttributes(10,10,10), new PhysicalAttributes(15,15,15,15,15),3, 1d,
				           1d, EPosition.LB);
		Player player20 =
				new Player("player12", "player12", 1, "Turkiye", new TechnicalAttributes(15, 15, 15, 15, 15),
				           new MentalAttributes(10,10,10), new PhysicalAttributes(15,15,15,15,15),3, 1d,
				           1d, EPosition.RB);
		Player player21 =
				new Player("player13", "player13", 1, "Turkiye", new TechnicalAttributes(15, 15, 15, 15, 15),
				           new MentalAttributes(10,10,10), new PhysicalAttributes(15,15,15,15,15),3, 1d,
				           1d, EPosition.RW);
		Player player22 =
				new Player("player14", "player14", 1, "Turkiye", new TechnicalAttributes(15, 15, 15, 15, 15),
				           new MentalAttributes(10,10,10), new PhysicalAttributes(15,15,15,15,15),3, 1d,
				           1d, EPosition.LW);
		Player player23 =
				new Player("player15", "player15", 1, "Turkiye", new TechnicalAttributes(15, 15, 15, 15, 15),
				           new MentalAttributes(10,10,10), new PhysicalAttributes(15,15,15,15,15),3, 1d,
				           1d, EPosition.CB);
		Player player24 =
				new Player("player16", "player16", 1, "Turkiye", new GKAttributes(15,15,15,15), 3, 1d,1d);
		league1.getLeagueTeamIDList().add(team1.getId());
		league1.getLeagueTeamIDList().add(team2.getId());
		league1.getLeagueTeamIDList().add(team3.getId());
	}
}


//		DataIO.dataIOInitialize();
//		Menu.startMenu();