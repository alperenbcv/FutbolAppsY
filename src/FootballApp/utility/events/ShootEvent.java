package FootballApp.utility.events;

import FootballApp.entities.Ball;
import FootballApp.entities.Match;
import FootballApp.entities.Player;
import FootballApp.utility.AverageCalculator;
import FootballApp.utility.ChanceConstantCalculator;

public class ShootEvent extends Event {
	public boolean shootEvent(Ball ball, Player defender, Player goalkeeper, Match match) {
		// Şut çeken oyuncunun yetenekleri
		Integer finishing = ball.getPlayerWithBall().getPlayerTechnicalAttributes().getFinishing();
		Integer shotPower = ball.getPlayerWithBall().getPlayerTechnicalAttributes().getShotPower();
		Integer decisionMaking = ball.getPlayerWithBall().getPlayerMentalAttributes().getDecisionMaking();
		int[] shootingValues = { finishing, shotPower, decisionMaking };
		double avgShooting = AverageCalculator.calculateAverage(shootingValues);
		double chance = ChanceConstantCalculator.chanceConstant(avgShooting);
		
		// Savunma oyuncusunun yetenekleri
		Integer positioning = defender.getPlayerTechnicalAttributes().getPositioning();
		Integer composure = defender.getPlayerMentalAttributes().getComposure();
		int[] defenseValues = { positioning, composure };
		double avgDefense = AverageCalculator.calculateAverage(defenseValues);

		// Kaleci uzaktan sut yetenekleri
		Integer reflexes = goalkeeper.getGkAttributes().getReflexes();
		Integer diving = goalkeeper.getGkAttributes().getDiving();
		Integer gkpositioning = goalkeeper.getGkAttributes().getPositioning();
		Integer oneOnOne = goalkeeper.getGkAttributes().getOneOnOne();
		
		
		//Kaleci uzaktan sut yeteneği
		int[] distanceGKValues = { reflexes, diving, gkpositioning };
		double avgDistanceGK = AverageCalculator.calculateAverage(distanceGKValues);
		
		//kaleci 1e1 yeteneği
		int[] oneOnOneGKValues = { oneOnOne, gkpositioning, reflexes };
		double avgOneOnOneGK = AverageCalculator.calculateAverage(oneOnOneGKValues);
		
		
		//TOP 0 METREDE YANİ KALECİYLE KARSI KARSIYA
		if (ball.getPosition() == 50 || ball.getPosition() == -50) {
			if (ChanceConstantCalculator.oneOnOneOutChance(avgShooting)==0) {
				if (avgShooting*chance > avgOneOnOneGK) {
					//gol oldu
					ball.setPosition(0);
					System.out.println("Goal!" + " by " + ball.getPlayerWithBall().getName() + " on one on one.");
					return true;
				}
				else {
					if(ball.getPlayerWithBall().getCurrentTeamID()==match.getHomeTeamId()) {
						ball.setPosition(-50);
						ball.setPlayerWithBall(goalkeeper);
					}
					if(ball.getPlayerWithBall().getCurrentTeamID()==match.getAwayTeamId()){
						ball.setPosition(50);
						ball.setPlayerWithBall(goalkeeper);
					}
					System.out.println("Shot saved by " + goalkeeper.getName() + " on one on one.");
					return false;
				}
			}
			else {
				if(ball.getPlayerWithBall().getCurrentTeamID()==match.getHomeTeamId())
					ball.setPosition(-50);
				if(ball.getPlayerWithBall().getCurrentTeamID()==match.getAwayTeamId())
					ball.setPosition(50);
				System.out.println("Out of the box! Shot missed by " + ball.getPlayerWithBall()
				                                                           .getName() + " on one on one.");
				ball.setPlayerWithBall(goalkeeper);
				return false;
			}
		}
		//TOP 0-10 METRE ARASINDA
		else if (ball.getPosition() < 50 && ball.getPosition() >= 40 || ball.getPosition() <= -40 && ball.getPosition() > -50) {
			if (avgShooting*chance > avgDefense) {
				// şut defans oyuncusunu geçti
				if (ChanceConstantCalculator.distantShootOutChance(avgShooting)==0) {
					//top kaleye dogru gitti
					if (avgShooting > avgDistanceGK) {
						//gol oldu
						ball.setPosition(0);
						System.out.println("Goal!" + " by " + ball.getPlayerWithBall().getName() + ".");
						return true;
					}
					else {
						//kaleci kurtardi
						if(ball.getPlayerWithBall().getCurrentTeamID()==match.getHomeTeamId())
							ball.setPosition(-50);
						if(ball.getPlayerWithBall().getCurrentTeamID()==match.getAwayTeamId())
							ball.setPosition(50);
						ball.setPlayerWithBall(goalkeeper);
						System.out.println("Shot saved by " + goalkeeper.getName() + ".");
						return false;
					}
				}
				else {
					if(ball.getPlayerWithBall().getCurrentTeamID()==match.getHomeTeamId())
						ball.setPosition(-50);
					if(ball.getPlayerWithBall().getCurrentTeamID()==match.getAwayTeamId())
						ball.setPosition(50);
					System.out.println("Out of the box! Shot missed by " + ball.getPlayerWithBall().getName() + ".");
					ball.setPlayerWithBall(goalkeeper);

					return false;
				}
			}
			else {
				//defans topu kesti
				ball.setPlayerWithBall(defender);
				System.out.println("Ball blocked by " + defender.getName() + " at position " + ball.getPosition());
				return false;
			}
		}
		//TOP 10-20 METRE ARASINDA
		else if (ball.getPosition() < 40 && ball.getPosition() >= 30 || ball.getPosition() <= -30 && ball.getPosition() > -40) {
			if (avgShooting*chance > avgDefense) {
				// şut defans oyuncusunu geçti
				if (ChanceConstantCalculator.distantShootOutChance(avgShooting)==0) {
					//top kaleye dogru gitti
					if (avgShooting*chance > avgDistanceGK) {
						//gol oldu
						ball.setPosition(0);
						System.out.println("Goal!" + " by " + ball.getPlayerWithBall().getName() + " from long range.");
						return true;
					}
					else {
						//kaleci kurtardi
//                  BURAYA TAKIMA GORE SETPOSITION EKLENECEK -50 VEYA 50 SEKLINDE
						if(ball.getPlayerWithBall().getCurrentTeamID()==match.getHomeTeamId())
						ball.setPosition(-50);
						if(ball.getPlayerWithBall().getCurrentTeamID()==match.getAwayTeamId())
						ball.setPosition(50);
						ball.setPlayerWithBall(goalkeeper);
						System.out.println("Shot saved by " + goalkeeper.getName() + ".");
						return false;
					}
				}
				else {
					//disari vurdu
					if(ball.getPlayerWithBall().getCurrentTeamID()==match.getHomeTeamId())
						ball.setPosition(-50);
					if(ball.getPlayerWithBall().getCurrentTeamID()==match.getAwayTeamId())
						ball.setPosition(50);
					System.out.println("Out of the box! Shot missed by " + ball.getPlayerWithBall().getName() + ".");
					ball.setPlayerWithBall(goalkeeper);
					return false;
				}
			}
			else {
				//defans topu kesti
				ball.setPlayerWithBall(defender);
				System.out.println("Ball blocked by " + defender.getName() + " at position " + ball.getPosition());
				return false;
			}
		}
		return false;
	}
	
	public static double calculateShotOnTargetProbability(Player shooter) {
		double probability;
		Integer finishing = shooter.getPlayerTechnicalAttributes().getFinishing();
		Integer decisionMaking = shooter.getPlayerMentalAttributes().getDecisionMaking();
		Integer overall = finishing + decisionMaking;
		if (overall >= 30) {
			// 80-100 arasında %70 - %100 arası bir şans
			probability = 0.70 + 0.25 * (overall - 30) / 20;
		}
		else if (overall >= 20) {
			// 60-80 arasında %50 - %70 arası bir şans
			probability = 0.46 + 0.25 * (overall - 20) / 20;
		}
		else {
			// 60'dan düşükse %50'den düşük bir şans
			probability = 0.30 * overall / 40;
		}
		
		return probability;
	}
	
}