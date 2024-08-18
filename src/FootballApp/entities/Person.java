package FootballApp.entities;

public class Person extends BaseEntity {
	
	
	private String personName;
	private String personSurname;
	private Integer personAge;
	private String personNationality;
	
	
	public Person(Integer id) {
		super(id);
    }
	
	public Person(Integer id, String name, String surName, Integer age, String nationality) {
		super(id);
		this.personName = name;
        this.personSurname = surName;
        this.personAge = age;
		this.personNationality = nationality;
	}

	public String getSurName() {
		return personSurname;
	}

	public void setSurName(String surName) {
		this.personSurname = surName;
	}

	public String getName() {
		return personName;
	}
	
	public void setName(String name) {
		this.personName = name;
	}
	
	public Integer getAge() {
		return personAge;
	}
	
	public void setAge(int age) {
		this.personAge = age;
	}
	
	public String getNationality() {
		return personNationality;
	}
	
	public void setNationality(String nationality) {
		this.personNationality = nationality;
	}
	
}