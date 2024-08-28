package FootballApp.entities.attributes;

import FootballApp.entities.BaseEntity;

public class MentalAttributes extends BaseEntity implements PlayerAttributes{
	private static Integer mentalCount=0;
	
	private Integer composure;
	private Integer vision;
	private Integer decisionMaking;

	
	public MentalAttributes(Integer composure, Integer vision, Integer decisionMaking) {
		super(++mentalCount);
		this.composure = composure;
		this.vision = vision;
		this.decisionMaking = decisionMaking;
	}
	
	public Integer getComposure() {
		return composure;
	}
	
	public void setComposure(Integer composure) {
		this.composure = composure;
	}
	
	public Integer getVision() {
		return vision;
	}
	
	public void setVision(Integer vision) {
		this.vision = vision;
	}
	
	public Integer getDecisionMaking() {
		return decisionMaking;
	}
	
	public void setDecisionMaking(Integer decisionMaking) {
		this.decisionMaking = decisionMaking;
	}
}