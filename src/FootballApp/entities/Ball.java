package FootballApp.entities;

public class Ball {
	private Integer position; // Position on the field, 0 to 100
	private Player playerWithBall; // The player who currently has the ball
	private Boolean reference; // True always home team
	
	public Ball() {
		this.position = 0; // Start at midfield
		this.playerWithBall = null; // No player has possession at the start
	}
	
	public Integer getPosition() {
		return position;
	}
	
	
	public void setPosition(Integer position) {
		this.position = position;
	}
	
	public Player getPlayerWithBall() {
		return playerWithBall;
	}
	
	public void setPlayerWithBall(Player playerWithBall) {
		this.playerWithBall = playerWithBall;
	}
	
	@Override
	public String toString() {
		return "Ball [position=" + position + ", currentPlayer=" + (playerWithBall != null ? playerWithBall.getName() : "None") + "]";
	}
}