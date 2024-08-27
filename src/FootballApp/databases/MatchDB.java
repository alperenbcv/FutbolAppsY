package FootballApp.databases;

import FootballApp.entities.Manager;
import FootballApp.entities.Match;
import FootballApp.entities.Player;
import FootballApp.models.DatabaseModels;
import FootballApp.utility.DatabaseManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatchDB extends DatabaseManager<Match> {
	
	public Optional<Match> findByMatchID(Integer matchID) {
		for (Match match : veriListesi) {
			if (match.getId()==matchID) {
				return Optional.of(match);
			}
		}
		return Optional.empty();
	}
	
	public List<Match> listAll() {
		return veriListesi;
	}
	
	public List<Match> findByLeagueID(Integer leagueID) {
		List<Match> matchList = new ArrayList<>();
        for (Match match : veriListesi) {
            if (match.getLeagueId() == leagueID) {
                matchList.add(match);
            }
        }
        return matchList;
	}
	
	public List<Match> findByTeamID(Integer teamID) {
		List<Match> matchList = new ArrayList<>();
        for (Match match : veriListesi) {
            if (match.getHomeTeamId() == teamID || match.getAwayTeamId() == teamID) {
                matchList.add(match);
            }
        }
        return matchList;
	}
	
	public List<Match> findByTeamName(String teamName) {
		List<Match> matchList = new ArrayList<>();
		for (Match match : veriListesi) {
			if (match.getHomeTeamId() == DatabaseModels.teamDB.findByName(teamName).get().getId() || match.getAwayTeamId() == DatabaseModels.teamDB.findByName(teamName).get().getId()) {
				matchList.add(match);
			}
		}
		return matchList;
	}
	
	
}