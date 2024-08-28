package FootballApp.entities.attributes;

public class GKAttributes implements PlayerAttributes {
	private Integer reflexes;
	private Integer positioning;
	private Integer diving;
	private Integer oneOnOne;
	
	public GKAttributes(Integer reflexes, Integer positioning, Integer diving, Integer oneOnOne) {
		this.reflexes = reflexes;
		this.positioning = positioning;
		this.diving = diving;
		this.oneOnOne = oneOnOne;
	}
	
	public Integer getReflexes() {
		return reflexes;
	}
	
	public void setReflexes(Integer reflexes) {
		this.reflexes = reflexes;
	}
	
	public Integer getPositioning() {
		return positioning;
	}
	
	public void setPositioning(Integer positioning) {
		this.positioning = positioning;
	}
	
	public Integer getDiving() {
		return diving;
	}
	
	public void setDiving(Integer diving) {
		this.diving = diving;
	}
	
	public Integer getOneOnOne() {
		return oneOnOne;
	}
	
	public void setOneOnOne(Integer oneOnOne) {
		this.oneOnOne = oneOnOne;
	}
}