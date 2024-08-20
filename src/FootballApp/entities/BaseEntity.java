package FootballApp.entities;

public class BaseEntity {
	protected final Integer id;
	
	public BaseEntity(Integer id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	
}