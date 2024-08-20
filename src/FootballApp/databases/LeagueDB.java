package FootballApp.databases;

import FootballApp.entities.League;
import FootballApp.entities.Team;
import FootballApp.utility.DatabaseManager;

import java.util.Optional;

public class LeagueDB extends DatabaseManager<League> {
	public Optional<League> findByID(Integer leagueID) {
		for (League league : veriListesi) {
			if (league.getId()==leagueID) {
				return Optional.of(league);
			}
		}
		return Optional.empty();
	}
	
	public Optional<League> findByName(String leagueName) {
		for (League league : veriListesi) {
			if (league.getLeagueName().equals(leagueName)) {
				return Optional.of(league);
			}
		}
		return Optional.empty();
	}
}