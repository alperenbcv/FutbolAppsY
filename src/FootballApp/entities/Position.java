package FootballApp.entities;

public class Position {
	private int length; // Horizontal position (width of the field)
	
	public Position(int length) {
		this.length = length;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
}