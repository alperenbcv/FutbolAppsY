package FootballApp.entities.attributes;

import FootballApp.entities.BaseEntity;

public class PhysicalAttributes extends BaseEntity implements PlayerAttributes {
	private static Integer physicalCount=0;
	
	private Integer stamina;
	private Integer speed;
	private Integer strength;
	private Integer jumping;
	private Integer height;
	
	public PhysicalAttributes(Integer stamina, Integer speed, Integer strength, Integer jumping, Integer height) {
		super(++physicalCount);
		this.stamina = stamina;
		this.speed = speed;
		this.strength = strength;
		this.jumping = jumping;
		this.height = height;
	}
	
	public Integer getStamina() {
		return stamina;
	}
	
	public void setStamina(Integer stamina) {
		this.stamina = stamina;
	}
	
	public Integer getSpeed() {
		return speed;
	}
	
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	
	public Integer getStrength() {
		return strength;
	}
	
	public void setStrength(Integer strength) {
		this.strength = strength;
	}
	
	public Integer getJumping() {
		return jumping;
	}
	
	public void setJumping(Integer jumping) {
		this.jumping = jumping;
	}
	
	public Integer getHeight() {
		return height;
	}
	
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	@Override
	public String toString() {
		return "PhysicalAttributes{" + "stamina=" + getStamina() + ", speed=" + getSpeed() + ", strength=" + getStrength() + ", jumping=" + getJumping() + ", height=" + getHeight() + '}';
	}
}