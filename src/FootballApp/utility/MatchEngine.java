package FootballApp.utility;

import FootballApp.entities.Match;
import FootballApp.entities.Player;
import FootballApp.entities.Team;
import FootballApp.enums.EPosition;
import FootballApp.models.DatabaseModels;

import java.util.List;

public class MatchEngine {
	
	public void simulateMatch(Match match) {
		match.getHomeTeamId();
		match.getAwayTeamId();
		Team homeTeam = DatabaseModels.teamDB.findByID(match.getHomeTeamId()).get();
		Team awayTeam = DatabaseModels.teamDB.findByID(match.getAwayTeamId()).get();
		int homeTeamScore=0;
		int awayTeamScore=0;
		
		int homeStrength=calculateTeamStrength(homeTeam);
		int awayStrength=calculateTeamStrength(awayTeam);
		
		for(int minute=1; minute<=90; minute++) {
			if(createChance(homeStrength,awayStrength)){
				if(scoreGoal(homeTeam, awayTeam)) {
					match.setHomeTeamScore(homeTeamScore+=1);
				}
				else if (createChance(awayStrength, homeStrength)) {
					if(scoreGoal(awayTeam, homeTeam)) {
						match.setAwayTeamScore(awayTeamScore+=1);
					}
				}
			}
			
		}
	}
	
	private boolean createChance(int homeStr, int awayStr) {
		return Math.random() < (homeStr /(double) (homeStr + awayStr));
	}
	
	private int calculateTeamStrength(Team team) {
		List<Player> playersByTeamID = DatabaseModels.teamDB.findPlayersByTeamID(team.getId());
		int teamStrength =
				playersByTeamID.stream().mapToInt(Player::getPlayerOverallRating).sum()/playersByTeamID.size();
		return teamStrength;
	}
	
	private boolean passTheBall(Team homeTeam, Team awayTeam) {
		List<Player> homePlayersByTeamID = DatabaseModels.teamDB.findPlayersByTeamID(homeTeam.getId());
		List<Player> homeMidfielders =
				homePlayersByTeamID.stream().filter(player -> player.getPlayersPosition().equals(EPosition.CM)).toList();
		List<Player> homeAttackers =
				homePlayersByTeamID.stream().filter(player -> player.getPlayersPosition().equals(EPosition.ST)).toList();
		Player homeMidfielder = homeMidfielders.get((int) (Math.random() * homeMidfielders.size()));
		Player homeAttacker = homeAttackers.get((int) (Math.random() * homeAttackers.size()));
		
		List<Player> awayPlayersByTeamID = DatabaseModels.teamDB.findPlayersByTeamID(awayTeam.getId());
		List<Player> awayMidfielders =
				awayPlayersByTeamID.stream().filter(player -> player.getPlayersPosition().equals(EPosition.CM)).toList();
		List<Player> awayDefenders =
				awayPlayersByTeamID.stream().filter(player -> player.getPlayersPosition().equals(EPosition.CB)).toList();
		List<Player> awayAttackers =
				awayPlayersByTeamID.stream().filter(player -> player.getPlayersPosition().equals(EPosition.ST)).toList();
		Player awayMidfielder = awayMidfielders.get((int) (Math.random() * awayMidfielders.size()));
		Player awayDefender = awayDefenders.get((int) (Math.random() * awayDefenders.size()));
		
		
		if(Math.random()>0.1){
			if(homeAttacker.getPlayerOverallRating()>awayDefender.getPlayerOverallRating()){
				if(homeMidfielder.getPlayerTechnicalAttributes().getPass()*Math.random()>awayMidfielder.getPlayerTechnicalAttributes().getTackle()){
					//	System.out.println(homeMidfielder.getName() + " passes the ball to " + homeAttacker.getName());
					return true;
				}
			}
		}
		//	System.out.println(awayMidfielder.getName() + " tackles the ball.");
        return false;
	}
	
	private boolean scoreGoal(Team team1, Team team2) {
		if(passTheBall(team1,team2)){
			List<Player> homePlayersByTeamID = DatabaseModels.teamDB.findPlayersByTeamID(team1.getId());
            List<Player> homeAttackers =
                    homePlayersByTeamID.stream().filter(player -> player.getPlayersPosition().equals(EPosition.ST)).toList();
            Player homeAttacker = homeAttackers.get((int) (Math.random() * homeAttackers.size()));
			
			List<Player> awayPlayersByTeamID = DatabaseModels.teamDB.findPlayersByTeamID(team2.getId());
			List<Player> awayGKs =
					awayPlayersByTeamID.stream().filter(player -> player.getPlayersPosition().equals(EPosition.GK)).toList();
			Player awayGK = awayGKs.get((int) (Math.random() * awayGKs.size()));
			
			if(Math.random()<0.1&&homeAttacker.getPlayerTechnicalAttributes().getFinishing()>awayGK.getPlayerTechnicalAttributes().getTackle()){
			//	System.out.println(homeAttacker.getName() + " finishes the shot and scores a goal!");
                return true;
			}
			else {
			//	System.out.println(awayGK.getName() + " saved the ball.");
                return false;
			}
		}
		return false;
	}
	
}