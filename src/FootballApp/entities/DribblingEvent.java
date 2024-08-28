package FootballApp.entities;

public class DribblingEvent {
	
	public boolean dribblingEvent(Ball ball, Player defender) {
		// Topu süren oyuncunun yetenekleri
		Integer dribbling = ball.getPlayerWithBall().getPlayerTechnicalAttributes().getDribbling();
		Integer speed = ball.getPlayerWithBall().getPlayerPhysicalAttributes().getSpeed();
		Integer decisionMaking = ball.getPlayerWithBall().getPlayerMentalAttributes().getDecisionMaking();
		
		// Savunma oyuncusunun yetenekleri
		Integer tackle = defender.getPlayerTechnicalAttributes().getTackle();
		Integer positioning = defender.getPlayerTechnicalAttributes().getPositioning();
		Integer strength = defender.getPlayerPhysicalAttributes().getStrength();
		
		// Dribbling yapma yeteneği (dribbling + hız + karar verme)
		int dribblingAbility = dribbling + speed + decisionMaking;
		
		// Savunma yapma yeteneği (tackle + positioning)
		int defenseAbility = tackle + positioning + strength;
		
		if(dribblingAbility / 3 > defenseAbility / 3) {
			// Dribbling başarılı
			ball.setPosition(ball.getPosition() + 10); // Top 10 metre ilerler
			System.out.println("Dribble succeeded! Ball is now at position " + ball.getPosition());
			return true;
		} else {
			// Dribbling başarısız, top savunma oyuncusuna geçer
			ball.setPlayerWithBall(defender);
			System.out.println("Dribble failed! Ball intercepted by " + defender.getName() + " at position " + ball.getPosition());
			return false;
		}
	}
}