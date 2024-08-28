package FootballApp.entities;

import FootballApp.utility.AverageCalculator;
import FootballApp.utility.ChanceConstantCalculator;

import java.util.Random;

public class CrossEvent {
	
	public boolean crossEvent20m(Ball ball, Player ballReceiver, Player backPlayer, Player defender, Player goalkeeper, Match match) {
		// Orta açan oyuncunun yetenekleri
		Integer crossing = ball.getPlayerWithBall().getPlayerTechnicalAttributes().getCrossing();
		Integer vision = ball.getPlayerWithBall().getPlayerMentalAttributes().getVision();
		Integer decisionMaking = ball.getPlayerWithBall().getPlayerMentalAttributes().getDecisionMaking();
		
		// Orta yapma yeteneği (crossing + vizyon + karar verme)
		int[] crossStats={crossing, vision, decisionMaking};
		double avgCross = AverageCalculator.calculateAverage(crossStats);
		double chance = ChanceConstantCalculator.chanceConstant(avgCross);
		
		//Bek özellikleri
		Integer positioningBack = backPlayer.getPlayerTechnicalAttributes().getPositioning();
		Integer tackleBack = backPlayer.getPlayerTechnicalAttributes().getTackle();
		int[] backStats={positioningBack, tackleBack};
		double avgBack = AverageCalculator.calculateAverage(backStats);
		
		
		if (ball.getPosition() <= 30 || ball.getPosition() >= -30) {
			if (avgCross*chance > avgBack) {
				if (Math.random() * 20 < ballReceiver.getPlayerTechnicalAttributes().getFirstTouch()) {
					ball.setPosition(ball.getPosition() + 20*crossDirection(ball,match)); // Top 20 metre ilerler
					System.out.println("Cross succeeded! Ball is now with " + ballReceiver.getName() + " at position " + ball.getPosition());
					
					// Top savunmacıya gider ve kafa mücadelesi başlar
					return handleHeaderDuel(ball, ballReceiver, defender, goalkeeper);
				} else {
					ball.setPlayerWithBall(defender);
					ball.setPosition(ball.getPosition() + 20);
					System.out.println("Cross succeeded! But " + ballReceiver.getName() + " couldn't control the ball..." + " Ball is now with " + defender.getName() + " at position " + ball.getPosition() + ".");
					return false;
				}
			} else {
				ball.setPlayerWithBall(backPlayer);
				System.out.println("Cross intercepted by " + backPlayer.getName() + "! Ball is now with " + backPlayer.getName() + " at position " + ball.getPosition() + ".");
				return false;
			}
		}
		return false;
	}
	
	public boolean crossEvent30m(Ball ball, Player ballReceiver, Player backPlayer, Player defender, Player goalkeeper, Match match) {
		// Orta açan oyuncunun yetenekleri
		Integer crossing = ball.getPlayerWithBall().getPlayerTechnicalAttributes().getCrossing();
		Integer vision = ball.getPlayerWithBall().getPlayerMentalAttributes().getVision();
		Integer decisionMaking = ball.getPlayerWithBall().getPlayerMentalAttributes().getDecisionMaking();
		
		// Orta yapma yeteneği (crossing + vizyon + karar verme)
		int[] crossStats={crossing, vision, decisionMaking};
		double avgCross = AverageCalculator.calculateAverage(crossStats);
		double chance = ChanceConstantCalculator.chanceConstant(avgCross);
		
		//bek özellikleri
		Integer positioningBack = backPlayer.getPlayerTechnicalAttributes().getPositioning();
		Integer tackleBack = backPlayer.getPlayerTechnicalAttributes().getTackle();
		int[] backStats={positioningBack, tackleBack};
		double avgBack = AverageCalculator.calculateAverage(backStats);
		
		if (ball.getPosition() <= 20 || ball.getPosition() >= -20) {
			if (avgCross*chance > avgBack) {
				if (Math.random() * 25 < ballReceiver.getPlayerTechnicalAttributes().getFirstTouch()) {
					ball.setPosition(ball.getPosition() + 30*crossDirection(ball,match)); // Top 30 metre ilerler
					System.out.println("Cross succeeded! Ball is now with " + ballReceiver.getName() + " at position " + ball.getPosition());
					return handleHeaderDuel(ball, ballReceiver, defender, goalkeeper);
				} else {
					ball.setPlayerWithBall(defender);
					ball.setPosition(ball.getPosition()+30);
					System.out.println("Cross succeeded! But " + ballReceiver.getName() + " couldn't control the ball..." + " Ball is now with " + defender.getName() + " at position " + ball.getPosition() + ".");
					return false;
				}
			} else {
				ball.setPlayerWithBall(backPlayer);
				System.out.println("Cross intercepted by " + backPlayer.getName() + "! Ball is now with " + backPlayer.getName() + " at position " + ball.getPosition() + ".");
				return false;
			}
		}
		return false;
	}
	
	private int crossDirection(Ball ball, Match match) {
		if(ball.getPlayerWithBall().getCurrentTeamID() == match.getHomeTeamId()) {
			return -1;
		}
		else {
		    return 1;
		}
	}
	
	
	
	private boolean handleHeaderDuel(Ball ball, Player attacker, Player defender, Player goalkeeper) {
		// Hava topu mücadelesi
		Integer attackerHeader = attacker.getPlayerTechnicalAttributes().getHeader();
		Integer attackerJumping = attacker.getPlayerPhysicalAttributes().getJumping();
		int[] attackStats = {attackerHeader, attackerJumping};
		double avgAttack = AverageCalculator.calculateAverage(attackStats);
		double chance = ChanceConstantCalculator.chanceConstant(avgAttack);
		
		Integer defenderHeader = defender.getPlayerTechnicalAttributes().getHeader();
		Integer defenderJumping = defender.getPlayerPhysicalAttributes().getJumping();
		int[] defendStats = {defenderHeader, defenderJumping};
		double avgDefend = AverageCalculator.calculateAverage(defendStats);
		
		if (avgAttack*chance > avgDefend) {
			ball.setPlayerWithBall(attacker);
			System.out.println(attacker.getName() + " wins the header and directs the ball towards the goal!");
			return handleShotOnGoal(ball, attacker, goalkeeper);
		} else {
			ball.setPlayerWithBall(defender);
			System.out.println(defender.getName() + " wins the header and clears the ball away!");
			return false;
		}
	}
	
	private boolean handleShotOnGoal(Ball ball, Player attacker, Player goalkeeper) {
		// Kaleye şut
		Integer finishing = attacker.getPlayerTechnicalAttributes().getFinishing();
		Integer shotPower = attacker.getPlayerTechnicalAttributes().getShotPower();
		int[] shotStats = {finishing, shotPower};
		double avgShot = AverageCalculator.calculateAverage(shotStats);
		double chance = ChanceConstantCalculator.chanceConstant(avgShot);
		
		Integer gkHandling = goalkeeper.getGkAttributes().getDiving();
		Integer gkReflexes = goalkeeper.getGkAttributes().getReflexes();
		Integer positioning = goalkeeper.getGkAttributes().getPositioning();
		int[] saveStats = {gkHandling, gkReflexes, positioning};
		double avgSave = AverageCalculator.calculateAverage(saveStats);
		
		
		if (avgShot*chance > avgSave) {
			System.out.println("Goal! " + attacker.getName() + " scores a header!");
			ball.setPosition(50);
			return true;
		} else {
			System.out.println(goalkeeper.getName() + " makes a great save against a header!");
			ball.setPosition(0);
			ball.setPlayerWithBall(goalkeeper);
			return false;
		}
	}
}