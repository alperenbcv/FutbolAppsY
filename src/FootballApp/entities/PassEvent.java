package FootballApp.entities;

import FootballApp.utility.AverageCalculator;
import FootballApp.utility.ChanceConstantCalculator;

import java.util.Random;

public class PassEvent {

	
	public boolean passEvent10m(Ball ball, Player ballReceiver, Player defender, Match match) {
		// Pas yapan oyuncunun yetenekleri
		Integer pass = ball.getPlayerWithBall().getPlayerTechnicalAttributes().getPass();
		Integer vision = ball.getPlayerWithBall().getPlayerMentalAttributes().getVision();
		Integer decisionMaking = ball.getPlayerWithBall().getPlayerMentalAttributes().getDecisionMaking();
		
		// Savunma oyuncusunun yetenekleri
		Integer tackle = defender.getPlayerTechnicalAttributes().getTackle();
		Integer positioning = defender.getPlayerTechnicalAttributes().getPositioning();
		
		// Pası alan oyuncunun yetenekleri
		Integer firstTouch = ballReceiver.getPlayerTechnicalAttributes().getFirstTouch();
		
		// Pas yapma yeteneği (pas + vizyon + karar verme)
		int[] passAbility = {pass, vision, decisionMaking};
		double passAverage = AverageCalculator.calculateAverage(passAbility);
		double chance = ChanceConstantCalculator.chanceConstant(passAverage);
		
		
		// Savunma yapma yeteneği (tackle + positioning)
		int[] defenseAbility = {tackle, positioning};
		double defAverage = AverageCalculator.calculateAverage(defenseAbility);
		
		Double randomFactor = (Math.random() * 10);
		
		
		if (ball.getPosition() <= 40 ||ball.getPosition() >= -40) {
			if (passAverage*chance > defAverage) {
				if (firstTouch > randomFactor) {
					ball.setPlayerWithBall(ballReceiver);
					ball.setPosition(ball.getPosition() + 20*passDirection(ball,match));
					System.out.println("Pass succeeded! Ball is now with " + ballReceiver.getName() + " at position " + ball.getPosition());
					return true;
				}
				else {
					ball.setPlayerWithBall(defender);
					System.out.println("Pass failed! " + ballReceiver.getName() + " couldn't control the ball..." + " Ball is" + " " + "now with the" + defender.getName() + " at position " + ball.getPosition() + ".");
					return false;
				}
			}
			else {
				ball.setPlayerWithBall(defender);
				System.out.println("Pass intercepted by " + defender.getName() + "! Ball is now with the" + defender.getName() + " at position " + ball.getPosition() + ".");
				return false;
			}
		}
		return false;
	}
	
	public boolean passEvent20m(Ball ball, Player ballReceiver, Player defender, Match match) {
		// Pas yapan oyuncunun yetenekleri
		Integer pass = ball.getPlayerWithBall().getPlayerTechnicalAttributes().getPass();
		Integer vision = ball.getPlayerWithBall().getPlayerMentalAttributes().getVision();
		Integer decisionMaking = ball.getPlayerWithBall().getPlayerMentalAttributes().getDecisionMaking();
		
		// Savunma oyuncusunun yetenekleri
		Integer tackle = defender.getPlayerTechnicalAttributes().getTackle();
		Integer positioning = defender.getPlayerTechnicalAttributes().getPositioning();
		
		// Pası alan oyuncunun yetenekleri
		Integer firstTouch = ballReceiver.getPlayerTechnicalAttributes().getFirstTouch();
		
		// Pas yapma yeteneği (pas + vizyon + karar verme)
		int[] passAbility = {pass, vision, decisionMaking};
		double passAverage = AverageCalculator.calculateAverage(passAbility);
		double chance = ChanceConstantCalculator.chanceConstant(passAverage);
		
		
		// Savunma yapma yeteneği (tackle + positioning)
		int[] defenseAbility = {tackle, positioning};
		double defAverage = AverageCalculator.calculateAverage(defenseAbility);
		
		Double randomFactor = (Math.random() * 16);
		
		
		if (ball.getPosition() <= 30 ||ball.getPosition() >= -30) {
			if (passAverage*chance > defAverage) {
				if (firstTouch > randomFactor) {
					ball.setPlayerWithBall(ballReceiver);
					ball.setPosition(ball.getPosition() + 20*passDirection(ball,match));
					//Top 20 metre ilerler
					System.out.println("Pass succeeded! Ball is now with " + ballReceiver.getName() + " at position " + ball.getPosition());
					return true;
				}
				else {
					ball.setPlayerWithBall(defender);
					System.out.println("Pass failed! " + ballReceiver.getName() + " couldn't control the ball..." + " Ball is" + " " + "now with the" + defender.getName() + " at position " + ball.getPosition() + ".");
					return false;
				}
			}
			else {
				ball.setPlayerWithBall(defender);
				System.out.println("Pass intercepted by " + defender.getName() + "! Ball is now with the" + defender.getName() + " at position " + ball.getPosition() + ".");
				return false;
			}
		}
		return false;
	}
	
	private Integer passDirection(Ball ball, Match match) {
		Random random = new Random();
		if(ball.getPlayerWithBall().getCurrentTeamID() == match.getHomeTeamId()){
			random.nextInt(1,11);
			if(random.nextInt(1,11)>6){
				return -1;
			}
			else{
				return 1;
			}
		}
		if (ball.getPlayerWithBall().getCurrentTeamID() == match.getAwayTeamId()){
			random.nextInt(1,11);
			if(random.nextInt(1,11)>6){
				return 1;
			}
			else{
				return -1;
			}
		}
		return null;
	}
	
}