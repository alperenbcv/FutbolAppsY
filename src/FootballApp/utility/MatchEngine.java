package FootballApp.utility;

import FootballApp.entities.*;
import FootballApp.enums.EPosition;
import FootballApp.models.DatabaseModels;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatchEngine {
	Random random = new Random();
	public void simulateMatch(Match match) {
		Ball ball = new Ball();
		Team homeTeam = DatabaseModels.teamDB.findByID(match.getHomeTeamId()).get();
		Team awayTeam = DatabaseModels.teamDB.findByID(match.getAwayTeamId()).get();
		
		// Maç başlar, top orta sahada başlar
		ball.setPosition(0);
		ball.setPlayerWithBall(selectStartingPlayer(homeTeam, awayTeam));
		
		
		Team attackingTeam = homeTeam;
		Team defendingTeam = awayTeam;
		for (int minute = 1; minute <= 90; minute++) {
			
			int random = this.random.nextInt(1, 101);
			
			if (!ball.getPlayerWithBall().getCurrentTeamID().equals(attackingTeam.getId())) {
				Team temp = attackingTeam;
				attackingTeam = defendingTeam;
				defendingTeam = temp;
			}
			
			System.out.println("Minute: " + minute);
			
			//ILK 50M HUCUM EV SAHIBI
			if(attackingTeam==homeTeam) {
				if(ball.getPosition()==50){
					if(ball.getPlayerWithBall().getPlayersPosition()==EPosition.GK) {
						ball.setPlayerWithBall(selectRandomDefender(homeTeam));
						ball.setPosition(40);
						System.out.println("GK gives the ball to closest defender. "+ ball.getPlayerWithBall().getName() + " is at " + ball.getPosition());
					}
				}
				if (ball.getPosition() > 0 && ball.getPosition() <= 50) {
					first50m(match, random, ball, attackingTeam, defendingTeam);
					continue;
				}
			}
			//ILK 50M HUCUM DEPLASMAN
			if (attackingTeam==awayTeam){
				if(ball.getPosition()==-50){
                    if(ball.getPlayerWithBall().getPlayersPosition()==EPosition.GK) {
                        ball.setPlayerWithBall(selectRandomDefender(awayTeam));
						ball.setPosition(-40);
	                    System.out.println("GK gives the ball to closest defender. "+ ball.getPlayerWithBall().getName() + " is at " + ball.getPosition());
                    }
                }
				if (ball.getPosition() >= -50 && ball.getPosition() < 0) {
					first50m(match, random, ball, attackingTeam, defendingTeam);
					continue;
					
				}
			}
			
			
			// 50-70M HUCUM EV SAHIBI
			if(attackingTeam==homeTeam)
			if(ball.getPosition() <= 0 && ball.getPosition() >= -20) {
				between0and20(match, random, ball, attackingTeam, defendingTeam);
				continue;
			}
			// 50-70M HUCUM DEPLASMAN
			if(attackingTeam==awayTeam)
				if(ball.getPosition() >= 0 && ball.getPosition() <= 20) {
					between0and20(match, random, ball, attackingTeam, defendingTeam);
					continue;
				}
			
			
			//EV SAHIBI 80.M
			if(attackingTeam==homeTeam)
			if(ball.getPosition() == -30) {
				thirtyMeter(match, random, ball, attackingTeam, defendingTeam);
				continue;
				
			}
			
			//DEPLASMAN 80.M
			if(attackingTeam==awayTeam)
				if(ball.getPosition() == 30) {
					thirtyMeter(match, random, ball, attackingTeam, defendingTeam);
					continue;
				}
			
			
			//EV SAHIBI RAKIP KALEYE 10M
			if(attackingTeam==homeTeam)
			if(ball.getPosition()==-40){
				fortyMeter(match, random, ball, attackingTeam, defendingTeam);
				continue;
			}
			
			//DEPLASMAN RAKIP KALEYE 10M
			if(attackingTeam==awayTeam)
				if(ball.getPosition()==40){
					fortyMeter(match, random, ball, attackingTeam, defendingTeam);
					continue;
				}
			
			//EV SAHIBI RAKIP KALEDE
			if(attackingTeam==homeTeam)
			if(ball.getPosition()==-50){
				oneOnOne(match, ball, attackingTeam, defendingTeam);
				continue;
			}
			
			//DEPLASMAN RAKIP KALEDE
			if(attackingTeam==awayTeam)
				if(ball.getPosition()==50){
					oneOnOne(match, ball, attackingTeam, defendingTeam);
					continue;
				}
			
		}
		System.out.println("Match Ended. Final Score: Home " + match.getHomeTeamScore() + " - " + match.getAwayTeamScore()+ " Away");
	}
	
	
	private void oneOnOne (Match match, Ball ball, Team attackingTeam, Team defendingTeam) {
		ShootEvent shootEvent = new ShootEvent();
		if (shootEvent.shootEvent(ball, ball.getPlayerWithBall(), selectGoalkeeper(defendingTeam), match)) {
			if(ball.getPlayerWithBall().getCurrentTeamID()==match.getHomeTeamId()){
				match.setHomeTeamScore(match.getHomeTeamScore() + 1);
				ball.setPosition(0);
				ball.setPlayerWithBall(selectRandomAttacker(defendingTeam));
			}
			else{
				match.setAwayTeamScore(match.getAwayTeamScore() + 1);
				ball.setPosition(0);
				ball.setPlayerWithBall(selectRandomAttacker(defendingTeam));
			}
		}
	}
	private void fortyMeter(Match match, int random, Ball ball, Team attackingTeam, Team defendingTeam) {
		if (random <= 30) {
			PassEvent passEvent = new PassEvent();
			passEvent.passEvent10m(ball, selectRandomPlayerExceptCurrent(attackingTeam,ball),
			                       selectRandomDefender(defendingTeam), match);
		}
		else if (random > 30 && random <= 60) {
			DribblingEvent dribbleEvent = new DribblingEvent();
			dribbleEvent.dribblingEvent(ball, selectRandomDefender(defendingTeam), match);
		}
		else {
			ShootEvent shootEvent = new ShootEvent();
			if (shootEvent.shootEvent(ball, ball.getPlayerWithBall(), selectGoalkeeper(defendingTeam), match)) {
				if(ball.getPlayerWithBall().getCurrentTeamID()==match.getHomeTeamId()){
					match.setHomeTeamScore(match.getHomeTeamScore() + 1);
					ball.setPosition(0);
					ball.setPlayerWithBall(selectRandomAttacker(defendingTeam));
				}
				else{
					match.setAwayTeamScore(match.getAwayTeamScore() + 1);
					ball.setPosition(0);
					ball.setPlayerWithBall(selectRandomAttacker(defendingTeam));
				}
			}
		}
	}
	
	private void thirtyMeter(Match match, int random, Ball ball, Team attackingTeam, Team defendingTeam) {
		if (random <= 20) {
			PassEvent passEvent = new PassEvent();
			passEvent.passEvent10m(ball, selectRandomPlayerExceptCurrent(attackingTeam,ball),
			                       selectRandomDefender(defendingTeam), match);
		}
		else if (random > 20 && random <= 40) {
			DribblingEvent dribbleEvent = new DribblingEvent();
			dribbleEvent.dribblingEvent(ball, selectRandomDefender(defendingTeam), match);
		}
		else if (random > 40 && random <= 60) {
			PassEvent passEvent = new PassEvent();
			passEvent.passEvent20m(ball, selectRandomPlayerExceptCurrent(attackingTeam,ball),
			                       selectRandomDefender(defendingTeam), match);
			
		}
		else if (random > 60 && random <= 80) {
			CrossEvent crossEvent = new CrossEvent();
			if (crossEvent.crossEvent20m(ball, selectRandomSTPlayer(attackingTeam),
			                             selectRandomBackPlayer(defendingTeam), selectRandomCBPlayer(defendingTeam), selectGoalkeeper(defendingTeam), match)) {
				if(ball.getPlayerWithBall().getCurrentTeamID()==match.getHomeTeamId()){
					match.setHomeTeamScore(match.getHomeTeamScore() + 1);
					ball.setPosition(0);
					ball.setPlayerWithBall(selectRandomAttacker(defendingTeam));
				}
				else{
					match.setAwayTeamScore(match.getAwayTeamScore() + 1);
					ball.setPosition(0);
					ball.setPlayerWithBall(selectRandomAttacker(defendingTeam));
				}
				
				
				}
			}
		else{
			ShootEvent shootEvent = new ShootEvent();
			if (shootEvent.shootEvent(ball, ball.getPlayerWithBall(), selectGoalkeeper(defendingTeam), match)) {
				if(ball.getPlayerWithBall().getCurrentTeamID()==match.getHomeTeamId()){
					match.setHomeTeamScore(match.getHomeTeamScore() + 1);
					ball.setPosition(0);
					ball.setPlayerWithBall(selectRandomAttacker(defendingTeam));
				}
				else{
					match.setAwayTeamScore(match.getAwayTeamScore() + 1);
					ball.setPosition(0);
					ball.setPlayerWithBall(selectRandomAttacker(defendingTeam));
				}
			}
		}
	}
	
	private void between0and20(Match match, int random, Ball ball, Team attackingTeam, Team defendingTeam) {
		if (random <=30) {
			PassEvent passEvent = new PassEvent();
			passEvent.passEvent10m(ball, selectRandomPlayerExceptCurrent(attackingTeam,ball),
			                       selectRandomDefender(defendingTeam), match);
		}
		else if (random >30 && random <=50) {
			DribblingEvent dribbleEvent = new DribblingEvent();
			dribbleEvent.dribblingEvent(ball, selectRandomDefender(defendingTeam), match);
		}
		else if (random >50 && random <=70) {
			PassEvent passEvent = new PassEvent();
			passEvent.passEvent20m(ball, selectRandomPlayerExceptCurrent(attackingTeam,ball),
			                       selectRandomDefender(defendingTeam), match);
		}
		else if (random >70 && random <=90) {
			CrossEvent crossEvent = new CrossEvent();
			if(crossEvent.crossEvent20m(ball, selectRandomSTPlayer(attackingTeam),
			                            selectRandomBackPlayer(defendingTeam), selectRandomCBPlayer(defendingTeam)
					, selectGoalkeeper(defendingTeam), match)) {
				if(ball.getPlayerWithBall().getCurrentTeamID()==match.getHomeTeamId()){
					match.setHomeTeamScore(match.getHomeTeamScore() + 1);
					ball.setPosition(0);
					ball.setPlayerWithBall(selectRandomAttacker(defendingTeam));
				}
				else{
					match.setAwayTeamScore(match.getAwayTeamScore() + 1);
					ball.setPosition(0);
					ball.setPlayerWithBall(selectRandomAttacker(defendingTeam));
				}
			}
		}
		else {
			CrossEvent crossEvent = new CrossEvent();
			if(crossEvent.crossEvent30m(ball, selectRandomSTPlayer(attackingTeam),
			                            selectRandomBackPlayer(defendingTeam), selectRandomCBPlayer(defendingTeam)
					, selectGoalkeeper(defendingTeam), match)) {
				if(ball.getPlayerWithBall().getCurrentTeamID()==match.getHomeTeamId()){
					match.setHomeTeamScore(match.getHomeTeamScore() + 1);
					ball.setPosition(0);
					ball.setPlayerWithBall(selectRandomAttacker(defendingTeam));
				}
				else{
					match.setAwayTeamScore(match.getAwayTeamScore() + 1);
					ball.setPosition(0);
					ball.setPlayerWithBall(selectRandomAttacker(defendingTeam));
				}
			}
		}
	}
	
	private void first50m(Match match, int random, Ball ball, Team attackingTeam, Team defendingTeam) {
		if (random <= 40) {
			PassEvent passEvent = new PassEvent();
			passEvent.passEvent10m(ball, selectRandomPlayerExceptCurrent(attackingTeam,ball),
			                       selectRandomDefender(defendingTeam), match);
		}
		else if (random > 40 && random <= 70) {
			DribblingEvent dribbleEvent = new DribblingEvent();
			dribbleEvent.dribblingEvent(ball, selectRandomDefender(defendingTeam), match);
			
		}
		else{
			PassEvent passEvent = new PassEvent();
			passEvent.passEvent20m(ball, selectRandomPlayerExceptCurrent(attackingTeam,ball),
			                       selectRandomDefender(defendingTeam), match);
		}
	}
	
	private Player selectStartingPlayer(Team homeTeam, Team awayTeam) {
		return Math.random() < 0.5 ? selectRandomCMPlayer(homeTeam) : selectRandomCMPlayer(awayTeam);
	}
	
	private Player selectRandomPlayerExceptCurrent(Team team,Ball ball) {
		List<Player> players = DatabaseModels.teamDB.findPlayersByTeamID(team.getId());
		List<Player> playerexcGK = players.stream().filter(player -> player.getPlayersPosition() != EPosition.GK && !player.equals(ball.getPlayerWithBall())).toList();
		if (playerexcGK.isEmpty()) {
			return null; // Liste boşsa null döndür
		}
		Random random = new Random();
		int randomIndex = random.nextInt(playerexcGK.size()); // 0 ile players.size() - 1 arasında rastgele bir indeks seç
		return playerexcGK.get(randomIndex);
	}
	
	
	private Player selectRandomAttacker(Team team){
		List<Player> players = DatabaseModels.teamDB.findPlayersByTeamID(team.getId());
		List<Player> attackersList = players.stream()
		                                    .filter(player -> player.getPlayersPosition() == EPosition.ST
				                                    || player.getPlayersPosition() == EPosition.LW
				                                    || player.getPlayersPosition() == EPosition.RW
				                                    || player.getPlayersPosition() == EPosition.CM)
		                                    .toList();
		if (attackersList.isEmpty()) {
			return null; // Liste boşsa null döndür
		}
		Random random = new Random();
		int randomIndex = random.nextInt(attackersList.size());
		return attackersList.get(randomIndex);
	}
	
	private Player selectRandomDefender(Team team){
		List<Player> players = DatabaseModels.teamDB.findPlayersByTeamID(team.getId());
		List<Player> defendersList = players.stream()
		                                    .filter(player -> player.getPlayersPosition() == EPosition.LB
				                                    || player.getPlayersPosition() == EPosition.RB
				                                    || player.getPlayersPosition() == EPosition.CB
				                                    || player.getPlayersPosition() == EPosition.CM)
		                                    .toList();
		if (defendersList.isEmpty()) {
			return null; // Liste boşsa null döndür
		}
		Random random = new Random();
		int randomIndex = random.nextInt(defendersList.size());
		return defendersList.get(randomIndex);
	}
	
	private Player selectRandomCMPlayer(Team team) {
		List<Player> players = DatabaseModels.teamDB.findPlayersByTeamID(team.getId());
		List<Player> cmList = players.stream()
		                             .filter(player -> player.getPlayersPosition() == EPosition.CM)
		                             .toList();
		if (cmList.isEmpty()) {
			return null; // Liste boşsa null döndür
		}
		Random random = new Random();
		int randomIndex = random.nextInt(cmList.size());
		return cmList.get(randomIndex);
	}
	
	private Player selectRandomCBPlayer(Team team) {
		List<Player> players = DatabaseModels.teamDB.findPlayersByTeamID(team.getId());
		List<Player> cbList = players.stream()
		                             .filter(player -> player.getPlayersPosition() == EPosition.CB)
		                             .toList();
		if (cbList.isEmpty()) {
			return null; // Liste boşsa null döndür
		}
		Random random = new Random();
		int randomIndex = random.nextInt(cbList.size());
		return cbList.get(randomIndex);
	}
	
	private Player selectRandomWingPlayer(Team team) {
		List<Player> players = DatabaseModels.teamDB.findPlayersByTeamID(team.getId());
		List<Player> lwList = players.stream()
		                             .filter(player -> player.getPlayersPosition() == EPosition.LW)
		                             .toList();
		List<Player> rwList = players.stream()
		                             .filter(player -> player.getPlayersPosition() == EPosition.RW)
		                             .toList();
		List<Player> wingerList = new ArrayList<>();
		wingerList.addAll(lwList);
		wingerList.addAll(rwList);
		if (wingerList.isEmpty()) {
			return null; // Liste boşsa null döndür
		}
		Random random = new Random();
		int randomIndex = random.nextInt(wingerList.size());
		return wingerList.get(randomIndex);
	}
	
	private Player selectRandomBackPlayer(Team team) {
		List<Player> players = DatabaseModels.teamDB.findPlayersByTeamID(team.getId());
		List<Player> lbList = players.stream()
		                             .filter(player -> player.getPlayersPosition() == EPosition.LB)
		                             .toList();
		List<Player> rbList = players.stream()
		                             .filter(player -> player.getPlayersPosition() == EPosition.RB)
		                             .toList();
		List<Player> backList = new ArrayList<>();
		backList.addAll(lbList);
		backList.addAll(rbList);
		if (backList.isEmpty()) {
			return null; // Liste boşsa null döndür
		}
		Random random = new Random();
		int randomIndex = random.nextInt(backList.size());
		return backList.get(randomIndex);
	}
	
	private Player selectRandomSTPlayer(Team team) {
		List<Player> players = DatabaseModels.teamDB.findPlayersByTeamID(team.getId());
		List<Player> stList = players.stream()
		                             .filter(player -> player.getPlayersPosition() == EPosition.ST)
		                             .toList();
		if (stList.isEmpty()) {
			return null; // Liste boşsa null döndür
		}
		Random random = new Random();
		int randomIndex = random.nextInt(stList.size());
		return stList.get(randomIndex);
	}
	
	private Player selectGoalkeeper(Team team) {
		return DatabaseModels.teamDB.findPlayersByTeamID(team.getId()).stream()
		                            .filter(player -> player.getPlayersPosition() == EPosition.GK)
		                            .findFirst().orElse(null);
	}
}