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
				if (ball.getPosition() > 0 && ball.getPosition() <= 50) {
					if (random <= 40) {
						PassEvent passEvent = new PassEvent();
						if (!passEvent.passEvent10m(ball, selectRandomAttacker(attackingTeam), selectRandomDefender(defendingTeam), match)) {
						}
					}
					if (random > 40 && random <= 70) {
						DribblingEvent dribbleEvent = new DribblingEvent();
						if (!dribbleEvent.dribblingEvent(ball, selectRandomDefender(defendingTeam))) {
						}
					}
					if (random > 70) {
						PassEvent passEvent = new PassEvent();
						if (!passEvent.passEvent20m(ball, selectRandomAttacker(attackingTeam), selectRandomDefender(defendingTeam), match)) {
						}
					}
				}
			}
			
			//ILK 50M HUCUM DEPLASMAN
			if (attackingTeam==awayTeam){
				if (ball.getPosition() >= -50 && ball.getPosition() < 0) {
					if (random<=40) {
						PassEvent passEvent = new PassEvent();
						if (!passEvent.passEvent10m(ball, selectRandomAttacker(attackingTeam),
						                            selectRandomDefender(defendingTeam),match)) {
						}
					}
					if (random>40 && random<=70) {
						DribblingEvent dribbleEvent = new DribblingEvent();
						if (!dribbleEvent.dribblingEvent(ball, selectRandomDefender(defendingTeam))) {
						}
					}
					if (random>70) {
						PassEvent passEvent = new PassEvent();
						if(!passEvent.passEvent20m(ball, selectRandomAttacker(attackingTeam),
						                           selectRandomDefender(defendingTeam), match)) {
						}
					}
				}
			}
			
			
			// 50-70M HUCUM EV SAHIBI
			if(attackingTeam==homeTeam)
			if(ball.getPosition() <= 0 && ball.getPosition() > -20) {
				if (random<=30) {
					PassEvent passEvent = new PassEvent();
					if (!passEvent.passEvent10m(ball, selectRandomAttacker(attackingTeam),
					                            selectRandomDefender(defendingTeam),match)) {
					}
				}
				if (random>30 && random<=50) {
					DribblingEvent dribbleEvent = new DribblingEvent();
					dribbleEvent.dribblingEvent(ball, selectRandomDefender(defendingTeam));
				}
				if (random>50 && random<=70) {
					PassEvent passEvent = new PassEvent();
					if (!passEvent.passEvent20m(ball, selectRandomAttacker(attackingTeam),
					                            selectRandomDefender(defendingTeam),match)) {
					}
				}
				if (random>70 && random<=90) {
					CrossEvent crossEvent = new CrossEvent();
					if(!crossEvent.crossEvent20m(ball, selectRandomSTPlayer(attackingTeam),
					                             selectRandomBackPlayer(defendingTeam), selectRandomCBPlayer(defendingTeam)
							,selectGoalkeeper(defendingTeam))) {
						
					}
				}
				if (random>90) {
					CrossEvent crossEvent = new CrossEvent();
					if(!crossEvent.crossEvent30m(ball, selectRandomSTPlayer(attackingTeam),
					                             selectRandomBackPlayer(defendingTeam), selectRandomCBPlayer(defendingTeam)
							,selectGoalkeeper(defendingTeam))) {
						
					}
				}
				}
			// 50-70M HUCUM DEPLASMAN
			if(attackingTeam==awayTeam)
				if(ball.getPosition() >= 0 && ball.getPosition() < 20) {
					if (random<=30) {
						PassEvent passEvent = new PassEvent();
						if (!passEvent.passEvent10m(ball, selectRandomAttacker(attackingTeam),
						                            selectRandomDefender(defendingTeam),match)) {
						}
					}
					if (random>30 && random<=50) {
						DribblingEvent dribbleEvent = new DribblingEvent();
						if (!dribbleEvent.dribblingEvent(ball, selectRandomDefender(defendingTeam))) {
						}
					}
					if (random>50 && random<=70) {
						PassEvent passEvent = new PassEvent();
						if (!passEvent.passEvent20m(ball, selectRandomAttacker(attackingTeam),
						                            selectRandomDefender(defendingTeam),match)) {
						}
					}
					if (random>70 && random<=90) {
						CrossEvent crossEvent = new CrossEvent();
						if(!crossEvent.crossEvent20m(ball, selectRandomSTPlayer(attackingTeam),
						                             selectRandomBackPlayer(defendingTeam), selectRandomCBPlayer(defendingTeam)
								,selectGoalkeeper(defendingTeam))) {
							
						}
					}
					if (random>90) {
						CrossEvent crossEvent = new CrossEvent();
						if(!crossEvent.crossEvent30m(ball, selectRandomSTPlayer(attackingTeam),
						                             selectRandomBackPlayer(defendingTeam), selectRandomCBPlayer(defendingTeam)
								,selectGoalkeeper(defendingTeam))) {
							
						}
					}
				}
			
			
			//EV SAHIBI 80.M
			if(attackingTeam==homeTeam)
			if(ball.getPosition() == -30) {
				if (random <= 20) {
					PassEvent passEvent = new PassEvent();
					if (!passEvent.passEvent10m(ball, selectRandomAttacker(attackingTeam),
					                            selectRandomDefender(defendingTeam),match)) {
					}
				}
				if (random > 20 && random <= 40) {
					DribblingEvent dribbleEvent = new DribblingEvent();
					if (!dribbleEvent.dribblingEvent(ball, selectRandomDefender(defendingTeam))) {
					}
				}
				if (random > 40 && random <= 60) {
					PassEvent passEvent = new PassEvent();
					if (!passEvent.passEvent20m(ball, selectRandomAttacker(attackingTeam),
					                            selectRandomDefender(defendingTeam),match)) {
					}
				}
				if (random > 60 && random <= 80) {
					CrossEvent crossEvent = new CrossEvent();
					if (!crossEvent.crossEvent20m(ball, selectRandomSTPlayer(attackingTeam),
					                              selectRandomBackPlayer(defendingTeam), selectRandomCBPlayer(defendingTeam), selectGoalkeeper(defendingTeam))) {
						}
					}
				if (random > 80) {
					ShootEvent shootEvent = new ShootEvent();
					if (!shootEvent.shootEvent(ball, ball.getPlayerWithBall(), selectGoalkeeper(defendingTeam))) {
					}
				}
				
			}
			
			//DEPLASMAN 80.M
			if(attackingTeam==homeTeam)
				if(ball.getPosition() == 30) {
					if (random <= 20) {
						PassEvent passEvent = new PassEvent();
						if (!passEvent.passEvent10m(ball, selectRandomAttacker(attackingTeam),
						                            selectRandomDefender(defendingTeam),match)) {
						}
					}
					if (random > 20 && random <= 40) {
						DribblingEvent dribbleEvent = new DribblingEvent();
						if (!dribbleEvent.dribblingEvent(ball, selectRandomDefender(defendingTeam))) {
						}
					}
					if (random > 40 && random <= 60) {
						PassEvent passEvent = new PassEvent();
						if (!passEvent.passEvent20m(ball, selectRandomAttacker(attackingTeam),
						                            selectRandomDefender(defendingTeam),match)) {
						}
					}
					if (random > 60 && random <= 80) {
						CrossEvent crossEvent = new CrossEvent();
						if (!crossEvent.crossEvent20m(ball, selectRandomSTPlayer(attackingTeam),
						                              selectRandomBackPlayer(defendingTeam), selectRandomCBPlayer(defendingTeam), selectGoalkeeper(defendingTeam))) {
						}
					}
					if (random > 80) {
						ShootEvent shootEvent = new ShootEvent();
						if (!shootEvent.shootEvent(ball, ball.getPlayerWithBall(), selectGoalkeeper(defendingTeam))) {
						}
					}
					
				}
			
			
			//EV SAHIBI RAKIP KALEYE 10M
			if(attackingTeam==homeTeam)
			if(ball.getPosition()==-40){
				if (random <= 30) {
					PassEvent passEvent = new PassEvent();
					if (!passEvent.passEvent10m(ball, selectRandomAttacker(attackingTeam),
					                            selectRandomDefender(defendingTeam),match)) {
					}
				}
				if (random > 30 && random <= 60) {
					DribblingEvent dribbleEvent = new DribblingEvent();
					if (!dribbleEvent.dribblingEvent(ball, selectRandomDefender(defendingTeam))) {
					}
				}
				if (random > 60) {
					ShootEvent shootEvent = new ShootEvent();
					if (!shootEvent.shootEvent(ball, ball.getPlayerWithBall(), selectGoalkeeper(defendingTeam))) {
					}
				}
			}
			
			//DEPLASMAN RAKIP KALEYE 10M
			if(attackingTeam==awayTeam)
				if(ball.getPosition()==40){
					if (random <= 30) {
						PassEvent passEvent = new PassEvent();
						if (!passEvent.passEvent10m(ball, selectRandomAttacker(attackingTeam),
						                            selectRandomDefender(defendingTeam),match)) {
						}
					}
					if (random > 30 && random <= 60) {
						DribblingEvent dribbleEvent = new DribblingEvent();
						if (!dribbleEvent.dribblingEvent(ball, selectRandomDefender(defendingTeam))) {
						}
					}
					if (random > 60) {
						ShootEvent shootEvent = new ShootEvent();
						if (!shootEvent.shootEvent(ball, ball.getPlayerWithBall(), selectGoalkeeper(defendingTeam))) {
						}
					}
				}
			
			//EV SAHIBI RAKIP KALEDE
			if(attackingTeam==homeTeam)
			if(ball.getPosition()==-50){
				ShootEvent shootEvent = new ShootEvent();
				if (!shootEvent.shootEvent(ball, ball.getPlayerWithBall(), selectGoalkeeper(defendingTeam))) {
				}
			}
			
			//DEPLASMAN RAKIP KALEDE
			if(attackingTeam==awayTeam)
				if(ball.getPosition()==50){
					ShootEvent shootEvent = new ShootEvent();
					if (!shootEvent.shootEvent(ball, ball.getPlayerWithBall(), selectGoalkeeper(defendingTeam))) {
					}
				}
			
		}
		System.out.println("Match Ended. Final Score: Home " + match.getHomeTeamScore() + " - " + match.getAwayTeamScore());
	}
	
	private Player selectStartingPlayer(Team homeTeam, Team awayTeam) {
		return Math.random() < 0.5 ? selectRandomCMPlayer(homeTeam) : selectRandomCMPlayer(awayTeam);
	}
	
	private Player selectRandomPlayer(Team team) {
		List<Player> players = DatabaseModels.teamDB.findPlayersByTeamID(team.getId());
		return players.get((int) (Math.random() * players.size()));
	}
	
	
	private Player selectRandomAttacker(Team team){
		List<Player> players = DatabaseModels.teamDB.findPlayersByTeamID(team.getId());
        List<Player> attackersList =
		        players.stream().filter(player -> player.getPlayersPosition() == EPosition.ST || player.getPlayersPosition() == EPosition.LW
				        || player.getPlayersPosition() == EPosition.RW ||player.getPlayersPosition() == EPosition.CM).toList();
        return attackersList.get((int) (Math.random() * players.size()));
	}
	
	private Player selectRandomDefender(Team team){
		List<Player> players = DatabaseModels.teamDB.findPlayersByTeamID(team.getId());
        List<Player> defendersList =
                players.stream().filter(player -> player.getPlayersPosition() == EPosition.LB || player.getPlayersPosition() == EPosition.RB ||
		                player.getPlayersPosition() == EPosition.CB ||player.getPlayersPosition() == EPosition.CM).toList();
        return defendersList.get((int) (Math.random() * players.size()));
		
	}
	private Player selectRandomCMPlayer(Team team) {
		List<Player> players = DatabaseModels.teamDB.findPlayersByTeamID(team.getId());
		List<Player> cmList = players.stream().filter(player -> player.getPlayersPosition() == EPosition.CM).toList();
		return cmList.get((int) (Math.random() * players.size()));
	}
	
	private Player selectRandomCBPlayer(Team team) {
		List<Player> players = DatabaseModels.teamDB.findPlayersByTeamID(team.getId());
		List<Player> cbList = players.stream().filter(player -> player.getPlayersPosition() == EPosition.CB).toList();
		return cbList.get((int) (Math.random() * players.size()));
	}
	
	private Player selectRandomWingPlayer(Team team) {
		List<Player> players = DatabaseModels.teamDB.findPlayersByTeamID(team.getId());
		List<Player> lwList =
				players.stream().filter(player -> player.getPlayersPosition() == EPosition.LW).toList();
		List<Player> rwList =
				players.stream().filter(player -> player.getPlayersPosition() == EPosition.RW).toList();
		List<Player> wingerList = new ArrayList<>();
		wingerList.addAll(lwList);
		wingerList.addAll(rwList);
		return wingerList.get((int) (Math.random() * players.size()));
	}
	
	private Player selectRandomBackPlayer(Team team) {
		List<Player> players = DatabaseModels.teamDB.findPlayersByTeamID(team.getId());
		List<Player> lbList =
				players.stream().filter(player -> player.getPlayersPosition() == EPosition.LB).toList();
		List<Player> rbList =
				players.stream().filter(player -> player.getPlayersPosition() == EPosition.RB).toList();
		List<Player> backList = new ArrayList<>();
		backList.addAll(lbList);
		backList.addAll(rbList);
		return backList.get((int) (Math.random() * players.size()));
	}
	
	private Player selectRandomSTPlayer(Team team) {
		List<Player> players = DatabaseModels.teamDB.findPlayersByTeamID(team.getId());
		List<Player> stList = players.stream().filter(player -> player.getPlayersPosition() == EPosition.ST).toList();
		return stList.get((int) (Math.random() * players.size()));
	}
	
	private Player selectGoalkeeper(Team team) {
		return DatabaseModels.teamDB.findPlayersByTeamID(team.getId()).stream()
		                            .filter(player -> player.getPlayersPosition() == EPosition.GK)
		                            .findFirst().orElse(null);
	}
}