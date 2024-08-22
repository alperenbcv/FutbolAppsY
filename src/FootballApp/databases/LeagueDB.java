package FootballApp.databases;

import FootballApp.entities.League;
import FootballApp.entities.Team;
import FootballApp.utility.DatabaseManager;

import java.util.ArrayList;
import java.util.List;
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
			if (league.getLeagueName().equalsIgnoreCase(leagueName)) {
				return Optional.of(league);
			}
		}
		return Optional.empty();
	}

	public List<League> findByPartialLeagueName(String leagueName) {
		List<League> foundLeagues = new ArrayList<>();
		for (League league : veriListesi) {
			if (league.getLeagueName().toLowerCase().contains(leagueName.toLowerCase())) {
				foundLeagues.add(league);

			}
		}
		return foundLeagues;
	}

	public List<League> listAll() {
		if(veriListesi.isEmpty()){
			return null;
		}
		else{
			return veriListesi;
		}
	}
}