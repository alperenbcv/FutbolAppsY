package FootballApp.entities;

public class ShootEvent {
	public boolean shootEvent(Ball ball, Player defender, Player goalkeeper) {
		// Şut çeken oyuncunun yetenekleri
		Integer finishing = ball.getPlayerWithBall().getPlayerTechnicalAttributes().getFinishing();
		Integer shotPower = ball.getPlayerWithBall().getPlayerTechnicalAttributes().getShotPower();
		Integer decisionMaking = ball.getPlayerWithBall().getPlayerMentalAttributes().getDecisionMaking();
		
		// Savunma oyuncusunun yetenekleri
		Integer positioning = defender.getPlayerTechnicalAttributes().getPositioning();
		Integer composure = defender.getPlayerMentalAttributes().getComposure();
		
		// Kaleci uzaktan sut yetenekleri
		Integer reflexes = goalkeeper.getGkAttributes().getReflexes();
		Integer diving = goalkeeper.getGkAttributes().getDiving();
		Integer gkpositioning = goalkeeper.getGkAttributes().getPositioning();
		Integer oneOnOne = goalkeeper.getGkAttributes().getOneOnOne();
		
		//Uzaktan sut yeteneği
		int shootingAbility = finishing + shotPower + decisionMaking;
		
		// Savunma yapma yeteneği
		int defenseAbility = positioning + composure;
		
		//Kaleci uzaktan sut yeteneği
		int goalkeeperDistanceShootAbility = reflexes + diving + gkpositioning;
		
		//kaleci 1e1 yeteneği
		int goalkeeperOneOnOneAbility = oneOnOne + gkpositioning + reflexes;
		
		
		//TOP 0 METREDE YANİ KALECİYLE KARSI KARSIYA
		if (ball.getPosition() == 100) {
			if (Math.random() > 0.05) {
				if (finishing > goalkeeperOneOnOneAbility / 3.2) {
					//gol oldu
					ball.setPosition(50);
					System.out.println("Goal!" + " by " + ball.getPlayerWithBall().getName() + " on one on one.");
					return true;
				}
				else {
					//kaleci kurtardi
					ball.setPosition(0);
					System.out.println("Shot saved by " + goalkeeper.getName() + " on one on one.");
					return false;
				}
			}
			else {
				//disari vurdu
				System.out.println("Out of the box! Shot missed by " + ball.getPlayerWithBall()
				                                                           .getName() + " on one on one.");
				ball.setPosition(0);
				return false;
			}
		}
		//TOP 0-10 METRE ARASINDA
		else if (ball.getPosition() < 100 && ball.getPosition() >= 90) {
			if (shootingAbility / 3 > defenseAbility / 2) {
				// şut defans oyuncusunu geçti
				double chance = calculateShotOnTargetProbability(ball.getPlayerWithBall());
				if (chance > Math.random()) {
					//top kaleye dogru gitti
					if (shootingAbility / 3 > goalkeeperDistanceShootAbility / 3) {
						//gol oldu
						ball.setPosition(50);
						System.out.println("Goal!" + " by " + ball.getPlayerWithBall().getName() + ".");
						return true;
					}
					else {
						//kaleci kurtardi
						ball.setPosition(0);
						ball.setPlayerWithBall(goalkeeper);
						System.out.println("Shot saved by " + goalkeeper.getName() + ".");
						return false;
					}
				}
				else {
					//disari vurdu
					System.out.println("Out of the box! Shot missed by " + ball.getPlayerWithBall().getName() + ".");
					ball.setPlayerWithBall(goalkeeper);
					ball.setPosition(0);
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
		else if (ball.getPosition() >= 80 && ball.getPosition() < 90) {
			if (shootingAbility / 3 > defenseAbility / 1.8) {
				// şut defans oyuncusunu geçti
				double chance = calculateShotOnTargetProbability(ball.getPlayerWithBall());
				if (chance > Math.random()) {
					//top kaleye dogru gitti
					if (shootingAbility / 3 > goalkeeperDistanceShootAbility / 2.8) {
						//gol oldu
						ball.setPosition(50);
						ball.setPlayerWithBall(goalkeeper);
						System.out.println("Goal!" + " by " + ball.getPlayerWithBall().getName() + " from long range.");
						return true;
					}
					else {
						//kaleci kurtardi
						ball.setPosition(0);
						ball.setPlayerWithBall(goalkeeper);
						System.out.println("Shot saved by " + goalkeeper.getName() + ".");
						return false;
					}
				}
				else {
					//disari vurdu
					System.out.println("Out of the box! Shot missed by " + ball.getPlayerWithBall().getName() + ".");
					ball.setPlayerWithBall(goalkeeper);
					ball.setPosition(0);
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