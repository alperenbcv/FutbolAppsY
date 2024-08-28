package FootballApp.entities;

public class CrossEvent {
	
	public boolean crossEvent20m(Ball ball, Player ballReceiver, Player backPlayer, Player defender, Player goalkeeper) {
		// Orta açan oyuncunun yetenekleri
		Integer crossing = ball.getPlayerWithBall().getPlayerTechnicalAttributes().getCrossing();
		Integer vision = ball.getPlayerWithBall().getPlayerMentalAttributes().getVision();
		Integer decisionMaking = ball.getPlayerWithBall().getPlayerMentalAttributes().getDecisionMaking();
		
		// Savunma oyuncusunun yetenekleri
		Integer tackle = backPlayer.getPlayerTechnicalAttributes().getTackle();
		Integer positioning = backPlayer.getPlayerTechnicalAttributes().getPositioning();
		
		// Orta yapma yeteneği (crossing + vizyon + karar verme)
		int crossAbility = crossing + vision + decisionMaking;
		
		// Savunma yapma yeteneği (tackle + positioning)
		int defenseAbility = tackle + positioning;
		
		Integer positioningBack = backPlayer.getPlayerTechnicalAttributes().getPositioning();
		Integer tackleBack = backPlayer.getPlayerTechnicalAttributes().getTackle();
		
		//bek özellikleri
		int backAbility = positioningBack + tackleBack;
		
		if (ball.getPosition() <= 80) {
			if (crossAbility / 3 > backAbility / 1.5) {
				if (Math.random() * 20 < ballReceiver.getPlayerTechnicalAttributes().getFirstTouch()) {
					ball.setPosition(ball.getPosition() + 20); // Top 20 metre ilerler
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
	
	public boolean crossEvent30m(Ball ball, Player ballReceiver, Player backPlayer, Player defender, Player goalkeeper) {
		// Orta açan oyuncunun yetenekleri
		Integer crossing = ball.getPlayerWithBall().getPlayerTechnicalAttributes().getCrossing();
		Integer vision = ball.getPlayerWithBall().getPlayerMentalAttributes().getVision();
		Integer decisionMaking = ball.getPlayerWithBall().getPlayerMentalAttributes().getDecisionMaking();
		
		// Savunma oyuncusunun yetenekleri
		Integer tackle = defender.getPlayerTechnicalAttributes().getTackle();
		Integer positioning = defender.getPlayerTechnicalAttributes().getPositioning();
		
		// Orta yapma yeteneği (crossing + vizyon + karar verme)
		int crossAbility = crossing + vision + decisionMaking;
		
		// Savunma yapma yeteneği (tackle + positioning)
		int defenseAbility = tackle + positioning;
		
		Integer positioningBack = backPlayer.getPlayerTechnicalAttributes().getPositioning();
		Integer tackleBack = backPlayer.getPlayerTechnicalAttributes().getTackle();
		
		//bek özellikleri
		int backAbility = positioningBack + tackleBack;
		
		if (ball.getPosition() <= 70) {
			if (crossAbility / 3 > backAbility / 1.7) {
				if (Math.random() * 25 < ballReceiver.getPlayerTechnicalAttributes().getFirstTouch()) {
					ball.setPosition(ball.getPosition() + 30); // Top 30 metre ilerler
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
	
	
	private boolean handleHeaderDuel(Ball ball, Player attacker, Player defender, Player goalkeeper) {
		// Hava topu mücadelesi
		Integer attackerHeader = attacker.getPlayerTechnicalAttributes().getHeader();
		Integer attackerJumping = attacker.getPlayerPhysicalAttributes().getJumping();
		
		Integer defenderHeader = defender.getPlayerTechnicalAttributes().getHeader();
		Integer defenderJumping = defender.getPlayerPhysicalAttributes().getJumping();
		
		int attackerAbility = attackerHeader + attackerJumping;
		int defenderAbility = defenderHeader + defenderJumping;
		
		if (Math.random() * attackerAbility > Math.random() * (defenderAbility * 1.1)) {
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
		
		Integer gkHandling = goalkeeper.getGkAttributes().getDiving();
		Integer gkReflexes = goalkeeper.getGkAttributes().getReflexes();
		Integer positioning = goalkeeper.getGkAttributes().getPositioning();
		
		int shotAbility = finishing + shotPower;
		int saveAbility = gkHandling + gkReflexes + positioning;
		
		if (Math.random() * shotAbility > Math.random() * (saveAbility * 1.2)) {
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