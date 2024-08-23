package FootballApp.databases;


import FootballApp.entities.TeamStats;
import FootballApp.utility.DatabaseManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamStatDB extends DatabaseManager<TeamStats> {
	
	public Optional<TeamStats> findByID(Integer teamStatID) {
		for (TeamStats st : veriListesi) {
			if (st.getId()==teamStatID) {
				return Optional.of(st);
			}
		}
		return Optional.empty();
	}

	public List<TeamStats> findByLeagueID(Integer leagueID){
		List<TeamStats> statListByLeague=new ArrayList<>();
		for(TeamStats ts:veriListesi){
			if(ts.getTeamLeagueID().equals(leagueID)){
				statListByLeague.add(ts);
			}
		}
		return statListByLeague;
	}
}