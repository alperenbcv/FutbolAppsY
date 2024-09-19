package FootballApp.entities.attributes;

import FootballApp.entities.BaseEntity;

public class TechnicalAttributes extends BaseEntity implements PlayerAttributes {
	private static Integer technicalCount=0;
	
	private Integer finishing;
	private Integer pass;
	private Integer dribbling;
	private Integer tackle;
	private Integer shotPower;
	private Integer crossing;
	private Integer header;
	private Integer positioning;
	private Integer firstTouch;
	
	
	public TechnicalAttributes(Integer finishing, Integer pass, Integer dribbling, Integer tackle, Integer shotPower, Integer crossing, Integer header, Integer positioning, Integer firstTouch) {
		super(++technicalCount);
		this.finishing = finishing;
		this.pass = pass;
		this.dribbling = dribbling;
		this.tackle = tackle;
		this.shotPower = shotPower;
		this.crossing = crossing;
		this.header = header;
		this.positioning = positioning;
		this.firstTouch= firstTouch;
	}
	
	public Integer getFirstTouch() {
		return firstTouch;
	}
	
	public void setFirstTouch(Integer firstTouch) {
		this.firstTouch = firstTouch;
	}
	
	public Integer getPositioning() {
		return positioning;
	}
	
	public void setPositioning(Integer positioning) {
		this.positioning = positioning;
	}
	
	public Integer getFinishing() {
		return finishing;
	}
	
	public Integer getPass() {
		return pass;
	}
	
	public Integer getDribbling() {
		return dribbling;
	}
	
	public Integer getTackle() {
		return tackle;
	}
	
	public Integer getShotPower() {
		return shotPower;
	}
	
	public void setFinishing(Integer finishing) {
		this.finishing = finishing;
	}
	
	public void setPass(Integer pass) {
		this.pass = pass;
	}
	
	public void setDribbling(Integer dribbling) {
		this.dribbling = dribbling;
	}
	
	public void setTackle(Integer tackle) {
		this.tackle = tackle;
	}
	
	public void setShotPower(Integer shotPower) {
		this.shotPower = shotPower;
	}
	
	public Integer getCrossing() {
		return crossing;
	}
	
	public void setCrossing(Integer crossing) {
		this.crossing = crossing;
	}
	
	public Integer getHeader() {
		return header;
	}
	
	public void setHeader(Integer header) {
		this.header = header;
	}
	
	@Override
	public String toString() {
		return "TechnicalAttributes: "
				+ "Finishing=" + getFinishing()
				+ ", Pass=" + getPass()
				+ ", Dribbling=" + getDribbling()
				+ ", Tackle=" + getTackle()
				+ ", ShotPower=" + getShotPower();
	}
}