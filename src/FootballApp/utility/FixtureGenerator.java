package FootballApp.utility;

import FootballApp.entities.League;
import FootballApp.entities.Match;
import FootballApp.enums.EMatchStatus;
import FootballApp.models.DatabaseModels;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class FixtureGenerator {
	
	public void generateFixtures(Integer leagueID) {
		Optional<League> byID = DatabaseModels.leagueDB.findByID(leagueID);
		League league= byID.get();
		
		LocalDate matchDate = league.getSeasonStartDate();
		Integer home;
		
		List<Integer> leagueTeamIDList = league.getLeagueTeamIDList();
		int leagueSize = leagueTeamIDList.size();
		int rounds = ((leagueSize*2)-2);
		
		for (int round = 1; round <= rounds; round++) {
			LocalDate[] matchDates = getWeekMatchDates(matchDate);
			boolean reverseHomeAway = (round % 2 == 0);
			for (int i = 0; i < leagueSize / 2; i++) {
				home = leagueTeamIDList.get(i);
				int away = leagueTeamIDList.get(leagueSize - 1 - i);
				if (reverseHomeAway) {
					int temp = home;
					home = away;
					away = temp;
				}
				if(home == leagueTeamIDList.get(0)) {
					Random random = new Random();
					new Match(home, away, matchDates[random.nextInt(0, 4)], EMatchStatus.SCHEDULED,
					                        DatabaseModels.teamDB.findByID(home).get().getLeagueID());
				}
				if(home != leagueTeamIDList.get(0)) {
					new Match(home, away, matchDates[i % 4], EMatchStatus.SCHEDULED,DatabaseModels.teamDB.findByID(home).get().getLeagueID());
					
				}
			}
			Collections.rotate(leagueTeamIDList.subList(1, leagueSize), 1);
			matchDate = matchDate.plusWeeks(1);
		}
	}
	
	private LocalDate[] getWeekMatchDates(LocalDate startDate) {
		return new LocalDate[]{startDate, // Friday
				startDate.plusDays(1), // Saturday
				startDate.plusDays(2), // Sunday
				startDate.plusDays(3) // Monday
		};
	}
}